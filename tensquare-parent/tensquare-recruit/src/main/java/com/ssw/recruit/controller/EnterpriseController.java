package com.ssw.recruit.controller;

import java.util.List;
import java.util.Map;

import com.ssw.entity.PageResult;
import com.ssw.entity.ResultModel;
import com.ssw.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssw.recruit.pojo.Enterprise;
import com.ssw.recruit.service.EnterpriseService;


/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 查询热点企业列表
     *
     * @return
     */
    @RequestMapping(value = "/search/hotlist")
    public ResultModel getHotCityList() {
        return enterpriseService.getHotCityList("1");
    }


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultModel findAll() {
        return new ResultModel(true, StatusCode.OK, "查询成功", enterpriseService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultModel findById(@PathVariable String id) {
        return new ResultModel(true, StatusCode.OK, "查询成功", enterpriseService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public ResultModel findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Enterprise> pageList = enterpriseService.findSearch(searchMap, page, size);
        return new ResultModel(true, StatusCode.OK, "查询成功", new PageResult<Enterprise>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResultModel findSearch(@RequestBody Map searchMap) {
        return new ResultModel(true, StatusCode.OK, "查询成功", enterpriseService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param enterprise
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultModel add(@RequestBody Enterprise enterprise) {
        enterpriseService.add(enterprise);
        return new ResultModel(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param enterprise
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultModel update(@RequestBody Enterprise enterprise, @PathVariable String id) {
        enterprise.setId(id);
        enterpriseService.update(enterprise);
        return new ResultModel(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultModel delete(@PathVariable String id) {
        enterpriseService.deleteById(id);
        return new ResultModel(true, StatusCode.OK, "删除成功");
    }

}
