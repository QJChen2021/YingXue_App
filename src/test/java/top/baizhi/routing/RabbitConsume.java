package top.baizhi.routing;

import com.rabbitmq.client.*;
import top.baizhi.util.RabbitMQUtil;

import java.io.IOException;

public class RabbitConsume {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare("logs_direct","direct");

        //创建临时队列绑定交换机
        String queue = channel.queueDeclare().getQueue();

        //参数3：rougingkey

        channel.queueBind(queue,"logs_direct","error");
        channel.queueBind(queue,"logs_direct","bb");

        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body);
                System.out.println("消费者1："+s);
            }
        });

        RabbitMQUtil.close(channel,connection);
    }
}
