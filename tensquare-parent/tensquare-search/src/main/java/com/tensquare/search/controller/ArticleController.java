package com.tensquare.search.controller;

import com.ssw.entity.PageResult;
import com.ssw.entity.ResultModel;
import com.ssw.entity.StatusCode;
import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.POST)
    public ResultModel save(Article article) {
        articleService.save(article);
        return new ResultModel(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value = "/{key}/{page}/{size}", method = RequestMethod.GET)
    public ResultModel findByKey(@PathVariable("key") String key, @PathVariable int page, @PathVariable int size) {
        Page<Article> pageData = articleService.findByKey(key, page, size);
        return new ResultModel(true, StatusCode.OK, "添加成功", new PageResult<Article>(pageData.getTotalElements(), pageData.getContent()));
    }

}
