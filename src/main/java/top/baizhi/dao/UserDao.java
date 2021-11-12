package top.baizhi.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.baizhi.entity.User;
import top.baizhi.vo.MonthAndCount;

import java.util.List;

@Repository
public interface UserDao {

    public Integer findAllPage();
    public List<User> findAll(Integer page,Integer size);

    public void add(User user);

    public String findHeadImg(String id);

    public void delete(String id);

    public void updateStatus(@Param("id") String id, @Param("status") String status);

    public List<MonthAndCount> queryByMonthCount(String sex);

}
