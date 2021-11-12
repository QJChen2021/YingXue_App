package top.baizhi.service;

import top.baizhi.entity.Admin;

import java.util.Map;

public interface AdminService {
    Map<String,Object> queryByName(Admin admin);
}
