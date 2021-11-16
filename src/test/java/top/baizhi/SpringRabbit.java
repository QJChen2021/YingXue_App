package top.baizhi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = YingxCzmApplication.class)
@RunWith(SpringRunner.class)
public class SpringRabbit {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHello(){
        //发送消息 参数1：消息队列名称   参数2：发送的内容
        rabbitTemplate.convertAndSend("hello","spring rabbitMQ Hello!");
    }
}
