package com.ssw.base.controller;

import com.ssw.base.pojo.Label;
import com.ssw.base.service.LabelService;
import com.ssw.entity.ResultModel;
import com.ssw.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping(method = RequestMethod.GET)
    public ResultModel findAll(){
        List<Label> labelList = labelService.findAll();
        return new ResultModel(true, StatusCode.OK,"查询成功",labelList);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResultModel save(@RequestBody Label label){
        labelService.save(label);
        return new ResultModel(true,StatusCode.OK,"保存成功");
    }
    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public ResultModel findById(@PathVariable("labelId") String labelId){
        Label label = labelService.findById(labelId);
        return new ResultModel(true, StatusCode.OK,"查询成功",label);
    }
    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public ResultModel update(@PathVariable String labelId, @RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new ResultModel(true, StatusCode.OK,"修改成功");
    }
    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public ResultModel delete(@PathVariable("labelId") String labelId){
        labelService.delete(labelId);
        return new ResultModel(true, StatusCode.OK,"删除成功");
    }



}
