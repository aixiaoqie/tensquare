package com.ssw.base.controller;

import com.ssw.entity.ResultModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResultModel findAll(){

        return new ResultModel();
    }


}
