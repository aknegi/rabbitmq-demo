package com.rabbitmqdemo.rabbitmq.utility;

import com.rabbitmqdemo.rabbitmq.Config.RabbitMqConfig;
import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class mailUtility {

    @Autowired
    JavaMailSender javaMailSender;

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void listener(JSONObject mailContent) {
        String to = (String) mailContent.get("to");
        String topic = (String) mailContent.get("topic");
        String body = (String) mailContent.get("body");
       sendMail(to, body, topic);
    }

    @Async
    public void sendMail(String to, String body, String topic) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("akshay.negi@tothenew.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(topic);
        simpleMailMessage.setText(body);
     //  javaMailSender.send(simpleMailMessage);
        System.out.println(body);
    }
}
