package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**吐槽控制层
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-23 17:10
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
@Slf4j
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 查询全部吐槽记录
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Spit> list = spitService.findAll();
        log.info("查询到的总数量："+list.size());
        return  new Result(true,StatusCode.OK,"查询全部成功",list);
    }

    /**
     * 查询指定ID的对象信息
     * @param spitId
     * @return
     */
    @RequestMapping(value = "{spitId}",method = RequestMethod.GET)
    public  Result findById(@PathVariable  String spitId){
        Spit spit = spitService.findById(spitId);
        return  new Result(true,StatusCode.OK,"查询findById成功",spit);
    }

    /**
     * 添加吐槽记录
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit){
        spitService.save(spit);
        return  new Result(true,StatusCode.OK,"添加成功");
    }

    /**
     * 修改指定ID的吐槽记录
     * @param spitId
     * @param spit
     * @return
     */
    @RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
    public Result update(@PathVariable String spitId, @RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除指定ID的吐槽对象
     * @param spitId
     * @return
     */
    @RequestMapping(value = "{spitId}",method = RequestMethod.DELETE)
    public  Result deleteById(@PathVariable String spitId){
         spitService.deleteById(spitId);
         return  new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 根据父级ID查询吐槽记录
     * @param parentId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/comment/{parentId}/{page}/{size}",method = RequestMethod.GET)
    public  Result  findByParentid(@PathVariable String parentId,@PathVariable int page,@PathVariable int size ){
        Page<Spit> pageData = spitService.findByParentid(parentId, page, size);
        return new Result(true,StatusCode.OK,"父级id查询成功",new PageResult<Spit>(pageData.getTotalElements(),pageData.getContent()));
    }
    @RequestMapping(value = "/thumbup/{spitId}",method =RequestMethod.PUT )
    public  Result updateThumbup(@PathVariable String  spitId){
        //获取userid
        String userid="111";
        if (redisTemplate.opsForValue().get("thumbup_"+userid)!=null){
            return  new Result(false,StatusCode.ERROR,"该用户已点过赞！");
        }
        spitService.updateThumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_"+userid,"1");
        return  new Result(true,StatusCode.OK,"点赞成功");
    }
}
