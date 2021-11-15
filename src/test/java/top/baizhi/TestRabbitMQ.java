package top.baizhi;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TestRabbitMQ {
    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //配置服务器地址
        factory.setHost("192.168.154.129");
        //rabitMQ端口
        factory.setPort(5672);
        //操作主机
        factory.setVirtualHost("/YingXue");
        factory.setHandshakeTimeout(5000);
        //用户名
        factory.setUsername("admin");
        //密码
        factory.setPassword("admin");

        try{
            //获取链接
            Connection connection = factory.newConnection();
            //获取通道
            Channel channel = connection.createChannel();

            /**设置队列参数
             * @param queue 队列名称  如果这个队列不存在，将会被创建
             * @param durable 持久性：用来定义队列是否要持久化  true:持久化  false:不持久化
             * @param exclusive 是否只能由创建者使用，其他连接不能使用。 true:独占队列  false:不独占队列
             * @param autoDelete 是否自动删除（没有连接自动删除） true:自动删除   false:不自动删除
             * @param arguments 队列的其他属性(构造参数)
             */

            channel.queueDeclare("javamessage",false,false,false,null);

            //发送消息到队列中
            channel.basicPublish("","javamessage",null,"Hello World!".getBytes());

            //关闭通道
            channel.close();
            //关闭链接
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
