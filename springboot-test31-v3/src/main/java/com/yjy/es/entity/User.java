package com.yjy.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "user")//索引名称 建议与实体类一致
public class User {
    @Id
    private Integer id;
    @Field(type = FieldType.Auto)//自动检测类型
    private Integer age;
    @Field(type = FieldType.Keyword)//手动设置为keyword  但同时也就不能分词
    private String name;
    @Field(type = FieldType.Text,analyzer = "ik_smart",searchAnalyzer = "ik_max_word")//设置为text  可以分词
    private String info;
}
