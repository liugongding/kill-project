package com.dingding.kill.controller;

import com.dingding.kill.dto.KillDto;
import com.dingding.kill.enums.StatusCode;
import com.dingding.kill.response.BaseResponse;
import com.dingding.kill.service.base.impl.KillServiceImpl;
import com.dingding.kill.service.base.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liudingding
 * @ClassName KillController
 * @description 执行秒杀
 * @date 2020/4/2 0:11
 * Version 1.0
 */
@Controller
@Slf4j
public class KillController {

    @Autowired
    private KillServiceImpl killService;

    @Autowired
    private LoginServiceImpl loginService;

//    @RequestMapping(value = "/execute")
//    @ResponseBody
//    public BaseResponse execute(@RequestBody @Validated KillDto killDto,
//                                BindingResult result,
//                                @CookieValue(value = "username", required = false) String username){
//        if (result.hasErrors() || killDto.getKillId() <= 0){
//            return new BaseResponse(StatusCode.INVALIDPARAMS);
//        }
//        int userId = loginService.selectByUserName(username).getId();
//        int killId = killDto.getKillId();
//        BaseResponse response = new BaseResponse(StatusCode.SUCCESS);
//        try {
//            //是否成功秒杀
//            boolean res = killService.killItem(killId, userId);
//            if (!res) {
//                return new BaseResponse(StatusCode.FAIL.getCode(),"商品抢购完毕或不在抢购时间段");
//            }
//        } catch (Exception e) {
//            log.error("抢购失败：{}", e.getMessage());
//            response = new BaseResponse(StatusCode.FAIL.getCode(), e.getMessage());
//        }
//        return response;
//    }

    @RequestMapping(value = "/execute/success")
    public String executeSuccess(){
        return "executeSuccess";
    }

    @RequestMapping(value = "/execute/lock")
    @ResponseBody
    public BaseResponse execute(@RequestBody @Validated KillDto killDto,
                                BindingResult result,
                                @CookieValue(value = "username", required = false) String username){
        if (result.hasErrors() || killDto.getKillId() <= 0){
            return new BaseResponse(StatusCode.INVALIDPARAMS);
        }
        int userId = loginService.selectByUserName(username).getId();
        int killId = killDto.getKillId();
        BaseResponse response = new BaseResponse(StatusCode.SUCCESS);
        try {
            //是否成功秒杀
            boolean res = killService.killItem(killId, userId);
            if (!res) {
                return new BaseResponse(StatusCode.FAIL.getCode(),"商品抢购完毕或不在抢购时间段");
            }
        } catch (Exception e) {
            log.error("抢购失败：{}", e.getMessage());
            response = new BaseResponse(StatusCode.FAIL.getCode(), e.getMessage());
        }
        return response;
    }

}
