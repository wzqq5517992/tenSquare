package com.tensquare.test;

import com.tensquare.rabbit.RabbitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2018-12-25 17:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class ProductTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直接模式
     */
    @Test
    public void  sendMsg1(){
        rabbitTemplate.convertAndSend("Test", "直接模式测试");
    }

    /**
     * 分裂模式
     */
    @Test
    public void  sendMsg2(){
        rabbitTemplate.convertAndSend("division","","分裂模式测试");
    }


    /**
     * 主题模式
     */
    @Test
    public void  sendMsg3(){
        rabbitTemplate.convertAndSend("topic","good.log","主题模式测试");
    }
}
