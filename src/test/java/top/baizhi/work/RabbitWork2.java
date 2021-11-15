package top.baizhi.work;

import com.rabbitmq.client.*;
import top.baizhi.util.RabbitMQUtil;

import java.io.IOException;

public class RabbitWork2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();

        //获取通道
        Channel channel = connection.createChannel();

        channel.queueDeclare("hello",true,false,true,null);

        channel.basicQos(1);

        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String string = new String(body);
                System.out.println(string);

                //消费完后告知 消息队列 可以删除该消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }
}
