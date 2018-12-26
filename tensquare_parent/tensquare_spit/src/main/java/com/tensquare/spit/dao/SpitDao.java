package com.tensquare.spit.dao;

import com.mongodb.MongoClient;
import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-23 16:55
 */
public interface SpitDao extends MongoRepository<Spit,String> {
    /**
     * 根据父级ID查询吐槽记录
     * @param parentid
     * @param pageable
     * @return
     */
    public Page<Spit> findByParentid(String parentid, Pageable pageable);

}
