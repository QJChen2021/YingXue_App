package top.baizhi.rabbit.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component("bbc")
public class Consume {


    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "logs_fanout")
            )
    )

    public void consume1(String message){
        System.out.println("消费者1："+message);
    }

    public void consume2(String message){
        System.out.println("消费者2："+message);
    }
}
