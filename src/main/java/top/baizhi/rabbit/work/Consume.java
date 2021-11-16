package top.baizhi.rabbit.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component("cc")
@RabbitListener(queuesToDeclare = @Queue("work"))
public class Consume {


    @RabbitHandler
    public void work(String message){
        System.out.println("消费者1："+message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    public void work2(String message){
        System.out.println("消费者2："+message);
    }
}
