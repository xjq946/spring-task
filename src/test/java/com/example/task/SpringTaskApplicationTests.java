package com.example.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

   //发送简单邮件
    @Test
    public void contextLoads() {
        SimpleMailMessage message=new SimpleMailMessage();
        //邮件设置
        message.setSubject("通知-今晚开会");
        message.setText("今晚7:30开会");
        message.setTo("xjq111006@163.com");
        message.setFrom("2366562298@qq.com");
        mailSender.send(message);
    }

    //发送复杂邮件
    @Test
    public void test02() throws Exception {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        //邮件设置
        helper.setSubject("通知-今晚开会");
        helper.setText("<b style='color:red'>今天 7:30 开会</b>",true);
        helper.setTo("xjq111006@163.com");
        helper.setFrom("2366562298@qq.com");

        //上传文件
        helper.addAttachment("1.jpg",new File("C:\\Users\\Administrator\\Desktop\\白冰.jpg"));
        helper.addAttachment("2.jpg",new File("C:\\Users\\Administrator\\Desktop\\薰衣草.jpg"));

        mailSender.send(mimeMessage);
    }
}
