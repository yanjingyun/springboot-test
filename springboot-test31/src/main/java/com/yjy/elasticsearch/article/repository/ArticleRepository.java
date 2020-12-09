package com.yjy.elasticsearch.article.repository;

import com.yjy.elasticsearch.article.document.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, Long>, QuerydslPredicateExecutor<Article> {

    List<Article> findByTitle(String title);

    List<Article> findByTitleLike(String title);

    Page<Article> findByTitleOrCategory(String title, String category, Pageable pageable);
}
