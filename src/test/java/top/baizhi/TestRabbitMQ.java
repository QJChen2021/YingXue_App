package top.baizhi;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import top.baizhi.util.RabbitMQUtil;

public class TestRabbitMQ {
    public static void main(String[] args) {

        Connection connection = RabbitMQUtil.getConnection();

        try{
            //获取通道
            Channel channel = connection.createChannel();

            /**设置队列参数
             * @param queue 队列名称  如果这个队列不存在，将会被创建
             * @param durable 持久性：用来定义队列是否要持久化  true:持久化  false:不持久化
             * @param exclusive 是否只能由创建者使用，其他连接不能使用。 true:独占队列  false:不独占队列
             * @param autoDelete 是否自动删除（没有连接自动删除） true:自动删除   false:不自动删除
             * @param arguments 队列的其他属性(构造参数)
             */

            channel.queueDeclare("javamessage",true,false,true,null);

            //发送消息到队列中
            channel.basicPublish("","javamessage", MessageProperties.PERSISTENT_TEXT_PLAIN,"Hello World!".getBytes());

            RabbitMQUtil.close(channel,connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
