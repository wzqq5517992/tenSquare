package com.tensquare.rabbit.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-25 17:48
 */
@Component
@RabbitListener(queues = "divisio2n")
public class Customer1 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("divisio2n："+msg);
    }

}
