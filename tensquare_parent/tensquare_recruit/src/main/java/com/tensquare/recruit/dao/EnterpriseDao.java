package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Enterprise;

import java.util.List;

/**企业dao层
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-20 19:08
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{

    public List<Enterprise>  findByIshot(String ishot);  //where ishot=?   查询热门企业
}
