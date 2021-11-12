package top.baizhi.dao;

import org.springframework.stereotype.Repository;
import top.baizhi.entity.Admin;

@Repository
public interface AdminDao {
    Admin queryByName(String username);
}
