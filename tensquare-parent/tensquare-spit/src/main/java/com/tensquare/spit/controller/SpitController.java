package com.tensquare.spit.controller;

import com.ssw.entity.PageResult;
import com.ssw.entity.ResultModel;
import com.ssw.entity.StatusCode;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @RequestMapping(method = RequestMethod.GET)
    public ResultModel findAll() {
        List<Spit> list = spitService.findAll();
        return new ResultModel(true, StatusCode.OK, "查询成功", list);
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.GET)
    public ResultModel findById(@PathVariable("spitId") String spitId) {
        return new ResultModel(true, StatusCode.OK, "查询成功", spitService.findById(spitId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResultModel save(@RequestBody Spit spit) {
        spitService.save(spit);
        return new ResultModel(true, StatusCode.OK, "保存成功");
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
    public ResultModel update(@PathVariable("spitId") String spitId, @RequestBody Spit spit) {
        spit.set_id(spitId);
        spitService.update(spit);
        return new ResultModel(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
    public ResultModel deleteById(@PathVariable("spitId") String spitId) {
        spitService.deleteById(spitId);
        return new ResultModel(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public ResultModel findByParentId(@PathVariable("parentid") String parentid, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Spit> pageData = spitService.findByParentId(parentid, page, size);
        return new ResultModel(true, StatusCode.OK, "查询成功", new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
    }

    @RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
    public ResultModel thumbup(@PathVariable("spitId") String spitId) {
        spitService.thumbup(spitId);
        return new ResultModel(true, StatusCode.OK, "点赞成功");
    }
}
