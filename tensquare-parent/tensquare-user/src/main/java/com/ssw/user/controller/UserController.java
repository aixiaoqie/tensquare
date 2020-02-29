package com.ssw.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssw.entity.PageResult;
import com.ssw.entity.ResultModel;
import com.ssw.entity.StatusCode;
import com.ssw.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssw.user.pojo.User;
import com.ssw.user.service.UserService;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 发送短信验证码
     */
    @RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
    public ResultModel sendSms(@PathVariable String mobile) {
        userService.sendSms(mobile);
        return new ResultModel(true, StatusCode.OK, "发送成功");
    }

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
    public ResultModel regist(@PathVariable String code, @RequestBody User user) {
        //得到缓存中的验证码
        String checkcodeRedis = (String) redisTemplate.opsForValue().get("checkcode_" + user.getMobile());
        if (checkcodeRedis.isEmpty()) {
            return new ResultModel(false, StatusCode.ERROR, "请先获取手机验证码");
        }
        if (!checkcodeRedis.equals(code)) {
            return new ResultModel(false, StatusCode.ERROR, "请输入正确的验证码");
        }
        userService.add(user);
        return new ResultModel(true, StatusCode.OK, "注册成功");
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultModel login(@RequestBody User user) {
        user = userService.login(user.getMobile(), user.getPassword());
        if (user == null) {
            return new ResultModel(false, StatusCode.LOGINERROR, "登录失败");
        }
        String token = jwtUtil.createJWT(user.getId(), user.getMobile(), "user");
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("roles", "user");
        return new ResultModel(true, StatusCode.OK, "登录成功", map);
    }


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResultModel findAll() {
        return new ResultModel(true, StatusCode.OK, "查询成功", userService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultModel findById(@PathVariable String id) {
        return new ResultModel(true, StatusCode.OK, "查询成功", userService.findById(id));
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
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return new ResultModel(true, StatusCode.OK, "查询成功", new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResultModel findSearch(@RequestBody Map searchMap) {
        return new ResultModel(true, StatusCode.OK, "查询成功", userService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param user
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultModel add(@RequestBody User user) {
        userService.add(user);
        return new ResultModel(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultModel update(@RequestBody User user, @PathVariable String id) {
        user.setId(id);
        userService.update(user);
        return new ResultModel(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultModel delete(@PathVariable String id) {
        userService.deleteById(id);
        return new ResultModel(true, StatusCode.OK, "删除成功");
    }

}
