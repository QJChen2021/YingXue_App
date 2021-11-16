package top.baizhi.topic;

import com.rabbitmq.client.*;
import top.baizhi.util.RabbitMQUtil;

import java.io.IOException;

public class RabbitConsumeTopic2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics","topic");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,"topics","user.#");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body);
                System.out.println("消费者2："+s);
            }
        });
    }
}
