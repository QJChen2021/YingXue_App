package top.baizhi.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import top.baizhi.util.RabbitMQUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RabbitRouting {
    public static void main(String[] args) throws IOException {
        //通过连接工厂得到连接对象
        Connection connection = RabbitMQUtil.getConnection();

        //连接对象得到通道对象
        Channel channel = connection.createChannel();

        //通道对象得到交换机对象  参数1：交换机名称 参数2：交换机类型
        channel.exchangeDeclare("logs_direct","direct");

        //routingkey
        String key = "";

        //发消息 参数1：往哪个交换机中发送消息 参数2：指定rougingkey 参数3：消息的额外设置
        channel.basicPublish("logs",key,null,("routing kry类型的消息"+key).getBytes(StandardCharsets.UTF_8));
    }
}
