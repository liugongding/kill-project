package com.dingding.kill.exception;


import com.dingding.kill.enums.StatusCode;

import java.text.MessageFormat;

/**
 * 自定义异常类型
 * @author pyy
 **/
public class CustomException extends RuntimeException {

    //错误代码
    StatusCode statusCode;

    public CustomException(StatusCode statusCode){
        this.statusCode = statusCode;
    }

    public CustomException(StatusCode statusCode, Object... args){
        String message = MessageFormat.format(statusCode.getMsg(), args);
        statusCode.setMsg(message);
        this.statusCode = statusCode;
    }

    public StatusCode getResultCode(){
        return statusCode;
    }

}
