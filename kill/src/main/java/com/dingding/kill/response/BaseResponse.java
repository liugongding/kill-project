package com.dingding.kill.response;

import com.dingding.kill.enums.StatusCode;
import lombok.Data;

/**
 * @author liudingding
 * @ClassName BaseResponse
 * @description
 * @date 2020/3/31 12:02
 * Version 1.0
 */
@Data
public class BaseResponse<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回实体
     */
    private T data;

    public BaseResponse(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(StatusCode statusCode){
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public BaseResponse(StatusCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }
}
