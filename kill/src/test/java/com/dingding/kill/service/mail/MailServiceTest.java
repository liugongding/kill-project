package com.dingding.kill.service.mail;

import com.dingding.kill.dto.MailDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@SpringBootTest
class MailServiceTest {

    private  String subject = "商品抢购成功";
    private  String content =
            "您好，您已成功抢购到商品: <strong style='color: red'>%s</strong> ，" +
                    "复制该链接并在浏览器采用新的页面打开，即可查看抢购详情：" +
                    "<a href='http:localhost:8092/kill/detail/%s'>http:localhost:8092/kill/detail/%s</a>" +
                    "，并请您在1个小时内完成订单的支付，" +
                    "超时将失效该订单哦！祝你生活愉快！\n";

    @Autowired
    MailService mailService;

    @Autowired
    JavaMailSender mailSender;

    @Test
    void sendSimpleEmail() {
        MailDTO mail = new MailDTO("你好", "nihao", new String[]{"3486171474@qq.com"});
        mailService.sendSimpleEmail(mail);
    }

    @Test
    void test1(){
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom("1051533375@qq.com");
        //收件人
        message.setTo("3486171474@qq.com");
        message.setSubject("1");
        message.setText("1");
        mailSender.send(message);
    }

    @Test
    void sendHTMLEmail() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
        messageHelper.setFrom("1051533375@qq.com");
        messageHelper.setTo("3486171474@qq.com");
        messageHelper.setSubject("!");
        messageHelper.setText("!");
        mailSender.send(message);

//        String itemName = "java";
//        String killId = "1";
//        String contents = String.format(this.content, itemName, killId, killId);
//        MailDTO mail = new MailDTO(this.subject, contents, new String[]{"3486171474@qq.com"});
//        mailService.sendHTMLEmail(mail);
    }
}