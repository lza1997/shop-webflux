package com.lza.listeer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues="sms")
public class smsLister {
     @RabbitHandler
    public  void exect(Map<String,String> stringMap){
         System.out.println("手机号"+stringMap.get("iphone"));
         System.out.println("验证码"+stringMap.get("s"));
     }


}
