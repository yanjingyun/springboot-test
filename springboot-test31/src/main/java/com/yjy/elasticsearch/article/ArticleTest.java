package com.yjy.elasticsearch.article;


import com.yjy.elasticsearch.article.document.Article;
import com.yjy.elasticsearch.article.repository.ArticleRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTest {

    // 规范跟jpa一样
    @Autowired
    private ArticleRepository articleRepository;

    // 保存(新增/更新)
    @Test
    public void testSave() {
        Article article = new Article();
        for(int i = 0; i < 20; i++) {
            article.setId((long) i);
            article.setTitle("title" + i);
            article.setCategory("category");
            article.setPrice((double) (i + i));
            article.setImages("images images images images");
            article.setCreateDate(new Date(System.currentTimeMillis()));
            articleRepository.save(article);
        }
    }

    // 批量保存(新增/更新)
    @Test
    public void testBatchSave() {
        List<Article> list = new ArrayList<>();
        for (int i = 20; i < 30; i++) {
            Article article = new Article();
            article.setId((long) i);
            article.setTitle("title" + i);
            article.setCategory("category2");
            article.setPrice((double) (i + i));
            article.setImages("images images images images");
            article.setCreateDate(new Date(System.currentTimeMillis()));
            list.add(article);
        }
        articleRepository.saveAll(list);
    }

    // 查询所有
    @Test
    public void testFindAll() {
//        Iterable<Article> houses = articleRepository.findAll();
        Iterable<Article> houses = articleRepository.findAll(Sort.by("price").descending());
        houses.forEach(item -> {
            System.out.println(item);
        });
    }

    // 根据title查询(spring data 规范)
    @Test
    public void testFindByTitle() {
//        List<Article> list = articleRepository.findByTitle("title5");
        List<Article> list = articleRepository.findByTitleLike("%15"); // 只会返回一条记录？？
        list.forEach(item -> {
            System.out.println(item);
        });
    }

    // 自定义查询
    @Test
    public void testQuery() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("category","category2"));
        Page<Article> page = articleRepository.search(queryBuilder.build());
        // 获取结果
        long totalElements = page.getTotalElements();
        System.out.println("totalElements: " + totalElements);
        for (Article item : page){
            System.out.println(item);
        }
    }

    // 自定义查询(分页)
    @Test
    public void testQueryPage() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("category","category2"));
        int page = 0;
        int size = 2;
        queryBuilder.withPageable(PageRequest.of(page,size));

        Page<Article> articlePage = articleRepository.search(queryBuilder.build());
        // 获取结果
        System.out.println("总条数: " + articlePage.getTotalElements());
        System.out.println("总页数: " + articlePage.getTotalPages());
        System.out.println("当前页: " + articlePage.getNumber());
        System.out.println("每页大小: " + articlePage.getSize());
        for (Article item : articlePage){
            System.out.println(item);
        }
    }


}
