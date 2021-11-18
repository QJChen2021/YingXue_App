package top.baizhi.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    //Controller方法执行前执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //token 键  对应值 sessionId  值
        String token = request.getHeader("token");
        System.out.println("token:"+token);
        if(redisTemplate.hasKey(token)){
            //redis中如果包含key就会返回true
            //将redis中这个健重新设置存活时间
            redisTemplate.expire(token,10, TimeUnit.MINUTES);
            return true;
        }else{
            response.setStatus(401);
            return false;
        }
    }

    @Override
    //Controller方法执行后执行

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    //响应页面完毕后执行  或则 Controller报错执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
