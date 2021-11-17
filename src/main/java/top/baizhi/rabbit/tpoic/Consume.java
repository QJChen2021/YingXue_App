package top.baizhi.rabbit.tpoic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component("ee")
public class Consume {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topics2",type = "topic"),
                    key = {"user.#","*.order"}
            )
    )
    public void consume1(String message){
        System.out.println("消费者1："+message);
    }
    public void consume2(String message){
        System.out.println("消费者2："+message);
    }
}
