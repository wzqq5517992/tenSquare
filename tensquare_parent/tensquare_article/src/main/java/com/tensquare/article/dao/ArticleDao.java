package com.tensquare.article.dao;

import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /**
     * 文章审核
     * @param id
     */
    @Modifying
    @Query(value = "UPDATE  tensquare_article.`tb_article` SET state=1 WHERE id=?" ,nativeQuery = true)
    public void updateState(String id);

    /**
     * 文章点赞
     * @param id
     */
    @Modifying
    @Query(value = "UPDATE  tensquare_article.`tb_article` SET thumbup=thumbup+1 WHERE id=?" ,nativeQuery = true)
    public void addThumbup(String id);
}
