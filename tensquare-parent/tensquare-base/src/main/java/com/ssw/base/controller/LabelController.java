package com.ssw.base.controller;

import com.ssw.base.pojo.Label;
import com.ssw.base.service.LabelService;
import com.ssw.entity.PageResult;
import com.ssw.entity.ResultModel;
import com.ssw.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/label")
@RefreshScope
public class LabelController {

    @Value("${ip.address}")
    private String ipAddr;

    @Autowired
    private LabelService labelService;

    @RequestMapping(method = RequestMethod.GET)
    public ResultModel findAll() {
        System.out.println("测试自定义配置 消息总线更新" + ipAddr);
        List<Label> labelList = labelService.findAll();
        return new ResultModel(true, StatusCode.OK, "查询成功", labelList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResultModel save(@RequestBody Label label) {
        labelService.save(label);
        return new ResultModel(true, StatusCode.OK, "保存成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public ResultModel findById(@PathVariable("labelId") String labelId) {
        Label label = labelService.findById(labelId);
        return new ResultModel(true, StatusCode.OK, "查询成功", label);
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public ResultModel update(@PathVariable String labelId, @RequestBody Label label) {
        label.setId(labelId);
        labelService.update(label);
        return new ResultModel(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public ResultModel delete(@PathVariable("labelId") String labelId) {
        labelService.delete(labelId);
        return new ResultModel(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResultModel search(@RequestBody Label label) {
        List<Label> list = labelService.search(label);
        return new ResultModel(true, StatusCode.OK, "查询成功", list);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public ResultModel pageQuery(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody Label label) {
        Page<Label> pageData = labelService.pageQuery(page, size, label);
        return new ResultModel(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageData.getTotalElements(), pageData.getContent()));
    }

}
