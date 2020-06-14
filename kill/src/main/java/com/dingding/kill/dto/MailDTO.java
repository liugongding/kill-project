package com.dingding.kill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author liudingding
 * @ClassName MailDto
 * @description
 * @date 2020/4/2 0:16
 * Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MailDTO implements Serializable {

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 接收人
     */
    private String[] tos;
}
