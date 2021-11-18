package top.baizhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.baizhi.dao.AdminDao;
import top.baizhi.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private RedisTemplate redisTemplate;
    //StringRedisTemplate 在存储redis中值的时候 存储的是java中的string类型
    //RedisTemplate 在存储redis中值的时候   存储的是java中的对象类型

    @Override
    public Map<String,Object> queryByName(Admin admin, HttpServletRequest httpServletRequest) {
        HashMap<String,Object> map = new HashMap<>();
        Admin login = adminDao.queryByName(admin.getUsername());
        if(login!=null){
            if(admin.getPassword().equals(login.getPassword())){
                map.put("state","success");
                map.put("username",login.getUsername());

                //取消序列化
                redisTemplate.setKeySerializer(new StringRedisSerializer());
                String id = httpServletRequest.getSession(true).getId();
                ValueOperations valueOperations = redisTemplate.opsForValue();
                valueOperations.set(id,admin,10,TimeUnit.MINUTES);
                map.put("token",id);
            }else{
                map.put("state","false");
                map.put("msg","密码错误");
            }
        }else{
            map.put("state","false");
            map.put("msg","用户名不存在");
        }
        return map;
    }
}
