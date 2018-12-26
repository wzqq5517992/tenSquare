package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-19 23:24
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;
    /**
     * 查询全部label对象
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
    List<Label> list = labelService.findAll();
    return new  Result (true,StatusCode.OK,"查询成功",list);
    }
    /**
     * 查询单个label对象
     * @param id
     * @return
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String id){
        return new  Result (true,StatusCode.OK,"查询成功",labelService.findById(id));
    }
    /**
     * 添加标签
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public  Result save(@RequestBody Label label){
        labelService.save(label);
        return new  Result (true,StatusCode.OK,"添加成功");
    }
    /**
     * 更新单个label对象
     * @param id
     * @param label
     * @return
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public  Result update(@PathVariable("labelId")String id,  @RequestBody Label label){
        label.setId(id);
        labelService.update(label);
        return new  Result (true,StatusCode.OK,"更新成功");
    }

    /**
     * 根据id删除单个label对象
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public  Result deleteById(@PathVariable String labelId){
        labelService.deleteById(labelId);
        return new  Result (true,StatusCode.OK,"删除成功");
    }

    /**
     * 条件查询
     * @param label
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public  Result findSearch(@RequestBody Label label){
        List<Label> search = labelService.findSearch(label);
        return new Result(true,StatusCode.OK,"条件查询成功",search);
    }

    /**
     * 条件+分页查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public  Result pageQuery(@RequestBody Label label,@PathVariable int page,@PathVariable int size){
        Page<Label> pageData = labelService.pageQuery(label,page,size);
        return new Result(true,StatusCode.OK,"分页查询成功",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }

}
