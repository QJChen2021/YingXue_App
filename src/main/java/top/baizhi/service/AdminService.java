package top.baizhi.service;

import top.baizhi.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AdminService {
    Map<String,Object> queryByName(Admin admin, HttpServletRequest httpServletRequest);
}
