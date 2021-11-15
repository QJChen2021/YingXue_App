package top.baizhi.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import top.baizhi.util.RabbitMQUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RabbitWork {
    //生产消息
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();

        Channel channel = connection.createChannel();

        /**设置队列参数
         * @param queue 队列名称  如果这个队列不存在，将会被创建
         * @param durable 持久性：用来定义队列是否要持久化  true:持久化  false:不持久化
         * @param exclusive 是否只能由创建者使用，其他连接不能使用。 true:独占队列  false:不独占队列
         * @param autoDelete 是否自动删除（没有连接自动删除） true:自动删除   false:不自动删除
         * @param arguments 队列的其他属性(构造参数)
         */
        channel.queueDeclare("hello",true,false,false,null);

        /**消费者消费消息
         * @param queue 队列名称
         * @param autoAck 是否自动应答。false表示consumer在成功消费过后必须要手动回复一下服务器，如果不回复，服务器就将认为此条消息消费失败，继续分发给其他consumer。
         * @param callback 回调方法类，一般为自己的Consumer类
         */
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("","hello", MessageProperties.MINIMAL_PERSISTENT_BASIC,("work rabbitMQ"+i).getBytes());
        }
        RabbitMQUtil.close(channel,connection);
    }
}
