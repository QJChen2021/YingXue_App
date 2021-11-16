package top.baizhi.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import top.baizhi.util.RabbitMQUtil;

import java.io.IOException;

public class RabbitTopic {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();

        Channel channel = connection.createChannel();

        String key = "user.AAAAA";

        channel.basicPublish("topics",key,null,("这是topic类型的消息"+key).getBytes());

        RabbitMQUtil.close(channel,connection);
    }
}
