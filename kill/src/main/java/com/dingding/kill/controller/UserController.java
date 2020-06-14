package com.dingding.kill.controller;

import com.dingding.kill.annotation.JwtIgnore;
import com.dingding.kill.entity.Audience;
import com.dingding.kill.entity.User;
import com.dingding.kill.response.BaseResponse;
import com.dingding.kill.service.base.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author liudingding
 * @ClassName UserController
 * @description
 * @date 2020/3/31 21:03
 * Version 1.0
 */
@Controller
@Slf4j
public class UserController {

    @Autowired
    private Audience audience;
    @Autowired
    private LoginServiceImpl loginService;

    @RequestMapping(value = "/login")
    @ResponseBody
    @JwtIgnore
    public BaseResponse adminLogin(HttpServletResponse response, @RequestBody User user){


        return loginService.login(response, user);
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    @JwtIgnore
    public void register(@RequestBody User user){
        user.setCreateTime(new Date());
        loginService.register(user);
    }

    @ResponseBody
    @RequestMapping(value = "/test")
    @JwtIgnore
    public User test(String username){
        return loginService.selectByUserName(username);
    }


//    @ResponseBody
//    @RequestMapping(value = "/token")
//    @JwtIgnore
//    public BaseResponse token(HttpServletResponse response, String username, String password){
//        //直接模拟登陆成功
//        String userId = UUID.randomUUID().toString();
//        String role = "admin"+ password;
//
//        log.info("username:{}",username);
//        log.info("password:{}",password);
//        //创建token
//        String token = JwtTokenUtil.createJWT(userId, username, role, audience);
//        log.info("##登陆成功, token:{}", token);
//
//        //将token放在响应头
//        response.setHeader(JwtTokenUtil.AUTO_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX+token);
//        //返回给客户端
//        JSONObject result = new JSONObject();
//        result.put("token", token);
//        return new BaseResponse(StatusCode.SUCCESS, result);
//    }
}
