package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**标签service层
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-20 19:08
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return  labelDao.findAll();
    }

    public  Label findById(String id){
        return  labelDao.findById(id).get();
    }
    public  void  save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }


    public  void  update(Label label){
        labelDao.save(label);
    }
    public  void  deleteById(String id){
        labelDao.deleteById(id);
    }


    public  List<Label> findSearch(Label label){
        return  labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 根对象,就是要把 《条件》封装到哪个对象中，如where 类名=label.getid
             * @param query 封装的都是查询关键字 如group by  order by
             * @param criteriaBuilder 用来封装条件对象的值，如果返回null，表示不需要任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //new 一个list来来存放所有条件
                List<Predicate> list = new ArrayList<Predicate>();
                if (label.getLabelname()!=null&&"".equals(label.getLabelname())){
                    //where labelname like "%小明%"
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class),
                            "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if(label.getState()!=null && !"".equals(label.getState())){
                    //where state="1"
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                //new一个数组作为返回值的条件
                Predicate[]  arr = new Predicate[list.size()];
                //将list转化为数组
                arr=list.toArray(arr);
                return criteriaBuilder.and(arr); //where  labelname like "%小明%" and  state="1"
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size){
        //封装了一个分页对象，在springdatajpa中想要实现分页，直接传一个分页对象即可
        Pageable pageable = PageRequest.of(page-1, size);
        return labelDao.findAll(new Specification<Label>(){
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if(label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if(label.getState()!=null && !"".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                Predicate[] parr = new Predicate[list.size()];
                //把集合中的属性复制到数组中
                parr = list.toArray(parr);
                return cb.and(parr);
            }
        }, pageable);
    }
}
