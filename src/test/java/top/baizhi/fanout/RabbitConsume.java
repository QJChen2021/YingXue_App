package top.baizhi.fanout;

import com.rabbitmq.client.*;
import top.baizhi.util.RabbitMQUtil;

import java.io.IOException;

public class RabbitConsume {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();

        //消费的交换机
        channel.exchangeDeclare("logs","fanout");

        //创建临时消息队列绑定给交换机  并返回这个消息队列的名字
        String queue = channel.queueDeclare().getQueue();

        //临时消息队列绑定给交换机
        channel.queueBind(queue,"logs","");

        //处理消息
        String s = channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String string = new String(body);
                System.out.println("消费者1："+string);
            }
        });
    }
}
