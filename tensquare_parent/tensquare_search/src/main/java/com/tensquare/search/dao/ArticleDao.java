package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-24 14:52
 */
public interface ArticleDao extends ElasticsearchRepository<Article,String> {

    Page<Article> findByTitleOrContentLike(String key, String key1, Pageable pageable);
}
