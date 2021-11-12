package top.baizhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.baizhi.dao.AdminDao;
import top.baizhi.entity.Admin;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Map<String,Object> queryByName(Admin admin) {
        HashMap<String,Object> map = new HashMap<>();
        Admin login = adminDao.queryByName(admin.getUsername());
        if(login!=null){
            if(admin.getPassword().equals(login.getPassword())){
                map.put("state","success");
                map.put("username",login.getUsername());
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
