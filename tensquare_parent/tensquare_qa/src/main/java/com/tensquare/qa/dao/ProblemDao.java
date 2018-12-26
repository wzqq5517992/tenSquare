package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**问题数据接口访问dao层
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-20 18:54
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    /**
     * 最新问题列表
     * @param labelid
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_problem ,tb_pl  where id=problemid  and labelid = ? ORDER BY replytime",nativeQuery = true)
    public Page<Problem> newList(String labelid, Pageable pageable);

    /**
     * 最热问题列表
     * @return
     */
    @Query(value = "select * from tb_problem ,tb_pl  where id=problemid  and labelid = ? ORDER BY reply",nativeQuery = true)
    public Page<Problem> hotList(String labelid, Pageable pageable);

    /**
     * 等待回答问题列表
     * @return
     */
    @Query(value = "select * from tb_problem ,tb_pl  where id=problemid  and labelid = ? and reply=0 ORDER BY createtime",nativeQuery = true)
    public Page<Problem> waitList(String labelid, Pageable pageable);

}
