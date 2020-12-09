package com.yjy.es.repository;

import com.yjy.es.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EsUserRepository extends ElasticsearchRepository<User,Integer> {

    //根据name查询
    List<User> findByName(String name);

    //根据name和info查询
    List<User> findByNameAndInfo(String name,String info);
}
