package top.baizhi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Aspect
public class RedisAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @After("execution(* top.baizhi..service.*Impl.*(..)) && !execution(* top.baizhi.service.*Impl.query*(..)) && !execution(* top.baizhi.service.*Impl.find*(..))")
    public void deleteCache(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getName();
        //执行用户增删改 会只执行UserServiceImpl className = top.baizhi.service.UserSERVICEiMPL
        //得到redis中所有的key 然后遍历 判断每个key前半段 如果和className一样 那么删除这个key
        redisTemplate.setKeySerializer(new StringRedisSerializer()); //取消序列化
        redisTemplate.delete(className);
//        Set keys = redisTemplate.keys("*"); //获取所有键
//        for (Object key : keys) {
//            if(((String)key).startsWith(className)){
//                redisTemplate.delete(key);
//            }
//        }
    }

    @Around("execution(* top.baizhi.service.*Impl.*(..))")
    public Object addCache(ProceedingJoinPoint proceedingJoinPoint){
        //判断redis中是否有缓存数据
        //有，直接从缓存中读取数据 否则从数据库中读取数据

        //清除缓存
        //如果执行了增删改 成功之后执行reids清楚
        //后置 业务方法执行完毕

        StringBuilder sb = new StringBuilder();

        //获取类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
//        System.out.println("类名:"+className);
//        sb.append(className);

        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
//        System.out.println("方法名:"+methodName);
        sb.append(methodName);

        //获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
//            System.out.println("实参:"+arg);
            sb.append(arg);
        }

        //使用redis之前取消键的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        String key = sb.toString();
        Boolean flag = redisTemplate.hasKey(key);
        //操作redis值是字符串类型的操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (flag){
            //reids中有数据缓存  有直接从缓存中获取
            return hashOperations.get(className,key);//将数据返回到controller
        }else {
            //否则从数据库获取  放行请求
            try {
                Object proceed = proceedingJoinPoint.proceed();//放行走数据库获取数据
                hashOperations.put(className,key,proceed);
                return proceed;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        Object proceed = null;
        try {
            proceed = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
