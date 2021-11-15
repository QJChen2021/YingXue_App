package top.baizhi;

import com.rabbitmq.client.*;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TestRabbitMQ2 {
    //开发消费者

    @Test
    public void testRabbitMQ2(){
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

        //得到连接
        try{
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

            /**消费者消费消息
             * @param queue 队列名称
             * @param autoAck 是否自动应答。false表示consumer在成功消费过后必须要手动回复一下服务器，如果不回复，服务器就将认为此条消息消费失败，继续分发给其他consumer。
             * @param callback 回调方法类，一般为自己的Consumer类
             */
            channel.basicConsume("javamessage",true,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(java.lang.String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);
                    java.lang.String string = new java.lang.String(body);
                    System.out.println("消费者获取消息："+string);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}