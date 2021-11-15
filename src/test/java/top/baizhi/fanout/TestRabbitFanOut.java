package top.baizhi.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import top.baizhi.util.RabbitMQUtil;

import java.io.IOException;

public class TestRabbitFanOut {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();

        Channel channel = connection.createChannel();

        //创建交换机
        channel.exchangeDeclare("logs","fanout");

        //发布消息
        //参数1：向哪个交换机发消息
        //参数2：key的配置 广播模式下没有任何作用所以不配置
        //参数3：发送消息的额外配置
        channel.basicPublish("logs","",null,"Hello FanOut".getBytes());

        RabbitMQUtil.close(channel,connection);
    }

}