package top.baizhi.service;

import org.springframework.web.multipart.MultipartFile;
import top.baizhi.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    Integer findAllPage();
    public List<User> findAll(Integer page);

    public void add(MultipartFile photo, HttpServletRequest request, User user);

    public void delete(String id);

    public void updateStatus(String id,String status);

    public Map<String,Object> queryByMonthCount();
}
