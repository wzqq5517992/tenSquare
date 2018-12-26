package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**招聘信息dao层
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-20 19:08
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
    /**
     * 查询推荐职位
     * @param state
     * @return
     */
    public List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state); // where state=?  order by createtime

    /**
     * 查询最新职位
     * @param state
     * @return
     */
    public  List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state); //where state!=?  order by createtime
}
