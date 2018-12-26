package com.tensquare.search.service;


import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.xml.bind.PrintConversionEvent;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-24 14:55
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
//    @Autowired
//    private IdWorker idWorker;

    /**
     * 保存到索引库
     * @param article
     */
    public void save(Article article){
        //article.setId(idWorker.nextId()+"");
        articleDao.save(article);
    }

    /**
     * 查找单个
     * @param key
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return articleDao.findByTitleOrContentLike(key, key, pageable);
    }

}
