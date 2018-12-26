package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.validation.constraints.Max;
import java.util.Date;
import java.util.List;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-23 16:56
 */
@Service
@Transactional
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     * @return
     */
    public List<Spit>  findAll(){
        return  spitDao.findAll();
    }

    /**
     * 查询单个对象
     * @param id
     * @return
     */
    public  Spit findById(String id){
        return  spitDao.findById(id).get();
    }

    /**
     * 增加吐槽记录
     * @param spit
     */
    public  void  save(Spit spit){
        String idworker = idWorker.nextId()+"";
        spit.set_id(idworker);
        //初始化数据完善
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        //判断当前吐槽是否有父节点
        if(spit.getParentid()!=null && !"".equals(spit.getParentid())){
            //给父节点吐槽的回复数加一
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitDao.save(spit);
    }

    /**
     * 修改指定Id的信息
     * @param spit
     */
    public void update(Spit spit){
        spitDao.save(spit);
    }

    /**
     * 删除指定id的吐槽对象
     * @param id
     */
    public  void  deleteById(String id){
      spitDao.deleteById(id);
    }

    /**
     * 根据父级ID查询吐槽数据
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentid(String parentid, int page, int size ){
        PageRequest pageable = PageRequest.of(page-1,size);
         return spitDao.findByParentid(parentid,pageable);
    }

    @Autowired
    private MongoTemplate mongoTemplate;
    public void  updateThumbup(String id){
        //方式一  效率较低
//        Spit spit = spitDao.findById(id).get();
//        spit.setThumbup(spit.getThumbup()==null ? 0:spit.getThumbup()+1);
//        spitDao.save(spit);
        //方式二使用 MongoTemplate db.spit.update({"_id","1"},{$inc:{thumbup:NumberInt(1)}})
        Query query = new Query();//指定条件
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();//自增数量
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");

    }
}
