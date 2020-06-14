package com.dingding.kill.controller;

import com.dingding.kill.annotation.JwtIgnore;
import com.dingding.kill.enums.StatusCode;
import com.dingding.kill.response.BaseResponse;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liudingding
 * @ClassName BaseController
 * @description
 * @date 2020/3/30 23:20
 * Version 1.0
 */
@Controller
@Slf4j
public class BaseController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/response")
    @ResponseBody
    public BaseResponse response(String name){
        BaseResponse response = new BaseResponse(StatusCode.SUCCESS);
        if (!StringUtil.isBlank(name)){
            response.setData(name);
        }
        return response;
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String error(){
        return "error";
    }

    @RequestMapping(value = "/index")
    @JwtIgnore
    public String login(){
        return "login";
    }

}
