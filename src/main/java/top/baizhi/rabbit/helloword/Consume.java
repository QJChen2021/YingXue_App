package top.baizhi.rabbit.helloword;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello",autoDelete = "true"))
public class Consume { //consume

    @RabbitHandler
    public void testHelloword(String message){
        System.out.println(message);
    }
}

