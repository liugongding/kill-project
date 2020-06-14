package com.dingding.kill.service.mail;

import com.dingding.kill.dto.MailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * @author liudingding
 * @ClassName MailService
 * @description
 * @date 2020/4/3 14:11
 * Version 1.0
 */
@Slf4j
@Component
public class MailService {



    @Autowired
    MailService mailService;

    @Autowired
    private JavaMailSender mailSender;


    @Async
    public void sendSimpleEmail(final MailDTO mail){
        log.info("################:{}",mail.getTos()[0]);
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("1051533375@qq.com");
            simpleMailMessage.setTo(mail.getTos()[0]);
            simpleMailMessage.setSubject(mail.getSubject());
            simpleMailMessage.setText(mail.getContent());
            mailSender.send(simpleMailMessage);
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败：{}", e.getMessage());
        }
    }

    @Async
    public void sendHTMLEmail(final MailDTO mail) {
        log.info("################:{}",mail.getContent());
        log.info("################:{}",mail.getTos()[0]);
        log.info("################:{}",mail.getSubject());
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true,"utf-8");
            messageHelper.setFrom("1051533375@qq.com");
            messageHelper.setTo(mail.getTos()[0]);
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getContent(),true);
            mailSender.send(mimeMessage);
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败：{}", e.getMessage());
        }


    }

}
