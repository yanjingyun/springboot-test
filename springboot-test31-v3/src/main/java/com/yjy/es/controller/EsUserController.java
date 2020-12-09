package com.yjy.es.controller;

import com.yjy.es.entity.User;
import com.yjy.es.repository.EsUserRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/esuser")
public class EsUserController {

    @Autowired
    private EsUserRepository esUserRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    private String[] names = {"诸葛亮", "曹操", "李白", "韩信", "赵云", "小乔", "狄仁杰", "李四", "诸小明", "王五"};
    private String[] infos = {"我来自中国的一个小乡村，地处湖南省", "我来自中国的一个大城市，名叫上海，人们称作魔都", "我来自东北，家住大囤里，一口大碴子话"};

    // 初始化（新增数据）
    @PostMapping("/init")
    public Object save() {
        //添加索引mapping    索引会自动创建但mapping自只用默认的这会导致分词器不生效 所以这里我们手动导入mapping
        elasticsearchRestTemplate.putMapping(User.class);

        Random random = new Random();
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            User user = new User();
            user.setId(i);
            user.setName(names[random.nextInt(9)]);
            user.setAge(random.nextInt(40) + i);
            user.setInfo(infos[random.nextInt(2)]);
            users.add(user);
        }
        return esUserRepository.saveAll(users);
    }

    // 根据id查询
    @GetMapping("/{id}")
    public User get(@PathVariable("id") Integer id) {
        return esUserRepository.findById(id).orElse(new User());
    }

    // 分页
    @GetMapping("/page/{pageNumber}/{sizeNumber}")
    public Object getPage(@PathVariable("pageNumber") int pageNumber, @PathVariable("sizeNumber") int sizeNumber){
        Pageable pageable = PageRequest.of(pageNumber, sizeNumber, Sort.Direction.ASC,"id");
        return esUserRepository.findAll(pageable);
    }

    // 根据名字查询
    @GetMapping("/findByName")
    public List<User> findByName(String name){
        return esUserRepository.findByName(name);
    }

    // 根据名字和介绍查询
    @GetMapping("findByNameAndInfo")
    public List<User> findByNameAndInfo(String name, String info){
        //这里是查询两个字段取交集，即代表两个条件需要同时满足
        return esUserRepository.findByNameAndInfo(name,info);
    }

    // 查询高亮显示
    // elasticseachRepository也能查询出结果但高亮部分会被舍弃，再加上elasticseachRepository的search方法已经过时，不太推荐使用，复杂查询还是直接用elasticsearchTemplate比较好
    @GetMapping("/findHightByUser")
    public Object getHightByUser(String value) {
        //根据一个值查询多个字段  并高亮显示  这里的查询是取并集，即多个字段只需要有一个字段满足即可
        //需要查询的字段
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("info", value))
                .should(QueryBuilders.matchQuery("name", value));
        //构建高亮查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withHighlightFields(
                        new HighlightBuilder.Field("info"),
                        new HighlightBuilder.Field("name"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        //查询
        SearchHits<User> search = elasticsearchRestTemplate.search(searchQuery, User.class);
        //得到查询返回的内容
        List<SearchHit<User>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<User> users = new ArrayList<>();
        //遍历返回的内容进行处理
        for (SearchHit<User> searchHit : searchHits) {
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充到content中
            searchHit.getContent().setName(highlightFields.get("name") == null ? searchHit.getContent().getName() : highlightFields.get("name").get(0));
            searchHit.getContent().setInfo(highlightFields.get("info") == null ? searchHit.getContent().getInfo() : highlightFields.get("info").get(0));
            //放到实体类中
            users.add(searchHit.getContent());
        }
        return users;
    }
}
