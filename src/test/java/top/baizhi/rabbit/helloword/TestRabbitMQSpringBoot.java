package top.baizhi.rabbit.helloword;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.baizhi.YingxCzmApplication;

@SpringBootTest(classes = YingxCzmApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQSpringBoot {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testWork(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","work 模型"+i);
        }
    }

    @Test
    public void testeHeLLO(){
//        发送消息 参数1: 消息队列名称  参数2:发送的内容
        rabbitTemplate.convertAndSend("hello","springboot  rabbitmq  helloword");
    }
}
