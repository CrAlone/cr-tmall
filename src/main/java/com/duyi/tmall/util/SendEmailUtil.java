package com.duyi.tmall.util;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;


/**
 * @author 发送邮件
 */

public class SendEmailUtil {
    private static final BeanFactory FACTORY;
    static {
        FACTORY = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    }
    public static boolean setEmailSend(String subject,String receiveEmail,String text) {
        try {
            JavaMailSenderImpl send = (JavaMailSenderImpl) FACTORY.getBean("send");
            MimeMessage message = send.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom("1071954519@qq.com");
            helper.setTo(receiveEmail);
            helper.setSubject(subject);
            helper.setText(text);
            send.send(message);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
