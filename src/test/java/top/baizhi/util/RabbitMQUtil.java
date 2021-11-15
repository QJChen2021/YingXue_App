package top.baizhi.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtil {
    private static ConnectionFactory factory = null;
    static {
        //创建连接工厂
        factory = new ConnectionFactory();

        //配置服务器地址
        factory.setHost("192.168.154.129");
        //rabitMQ端口
        factory.setPort(5672);
        //操作主机
        factory.setVirtualHost("/YingXue");
        factory.setHandshakeTimeout(5000);
        //用户名
        factory.setUsername("qjc");
        //密码
        factory.setPassword("admin");
    }

    public static Connection getConnection(){
        //获取连接
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Channel channel,Connection connection){
        try{
            //关闭通道
            if (channel!=null)channel.close();
            //关闭链接
            if(connection!=null)connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
