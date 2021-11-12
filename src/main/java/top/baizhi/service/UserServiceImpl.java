package top.baizhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.baizhi.dao.UserDao;
import top.baizhi.entity.User;
import top.baizhi.util.Oss;
import top.baizhi.vo.MonthAndCount;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private Integer size = 2;

    Oss oss = new Oss();

    @Override
    public Integer findAllPage() {
        return userDao.findAllPage()%size==0? userDao.findAllPage()/size : userDao.findAllPage()/size+1;
    }

    @Override
    public List<User> findAll(Integer page) {
        Integer start = (page - 1) * size;
        List<User> list = userDao.findAll(start, size);
        return list;
    }

    @Override
    public void add(MultipartFile photo, HttpServletRequest request, User user) {
        String head_img = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = simpleDateFormat.format(new Date());
        String filename = format+UUID.randomUUID().toString().replace("-", "")+(photo.getOriginalFilename()).substring((photo.getOriginalFilename()).lastIndexOf("."));
        try{
            oss.update(photo,filename);
            head_img = "http://yingxue-czm.oss-cn-beijing.aliyuncs.com/headImg/"+filename;
        }catch (Exception e){
            e.printStackTrace();
        }
        User user1 = new User();
        user1.setId(UUID.randomUUID().toString());
        user1.setPhone(user.getPhone());
        user1.setUsername(user.getUsername());
        user1.setHead_img(head_img);
        user1.setBrief(user.getBrief());
        user1.setWechat(user.getPhone());
        user1.setCreate_date(new Date());
        user1.setStatus(0);
        userDao.add(user1);
    }

    @Override
    public void delete(String id) {
        String headImg = userDao.findHeadImg(id).split("http://yingxue-czm.oss-cn-beijing.aliyuncs.com/")[1];
        oss.delete(headImg);
        userDao.delete(id);
    }

    @Override
    public void updateStatus(String id, String status) {
        userDao.updateStatus(id,status);
    }

    @Override
    public Map<String, Object> queryByMonthCount() {
        //定义月份数据
        Map<String,Object> map = new HashMap<>();
        List<String> months = Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月");
        List<MonthAndCount> manCount = userDao.queryByMonthCount("男");
        List<MonthAndCount> womenCount = userDao.queryByMonthCount("女");

        List<Integer> man = new ArrayList<>();
        List<Integer> women = new ArrayList<>();
        //遍历月份集合 遍历12次 每次遍历代表一个月份
        for (int i = 0; i <= 12; i++) {
            boolean flag = false;
            for (MonthAndCount monthAndCount : manCount) {
                if(monthAndCount.getMonth().equals(i+1+"")){
                    man.add(monthAndCount.getCount());
                    flag = true;
                }
            }
            if(flag == false){
                man.add(0);
            }

            boolean flag2 = false;
            for (MonthAndCount monthAndCount : womenCount) {
                if(monthAndCount.getMonth().equals(i+1+"")){
                    women .add(monthAndCount.getCount());
                    flag2 = true;
                }
            }
            if(flag2 == false){
                women.add(0);
            }
        }
        map.put("month",months);
        map.put("manCount",man);
        map.put("womenCount",women);
        return map;
    }
}
