package top.baizhi.rabbit.routing;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component("consume")
public class Consume {

    @RabbitListener(
     bindings = @QueueBinding(
             value = @Queue,
             exchange = @Exchange(name = "routing",type = "direct"),
             key = {"error","info"}
     )
    )

    public void consume1(String message){
        System.out.println("消费者1："+message);
    }

    public void consume2(String message){
        System.out.println("消费者2："+message);
    }
}
