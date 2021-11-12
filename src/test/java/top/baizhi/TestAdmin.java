package top.baizhi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.baizhi.dao.AdminDao;
import top.baizhi.entity.Admin;

@SpringBootTest(classes = YingxCzmApplication.class)
@RunWith(SpringRunner.class)
public class TestAdmin {

    @Autowired
    private AdminDao adminDao;

    @Test
    public void testAdmin(){
        Admin admin = new Admin();
        admin.setUsername("admin");
        System.out.println(adminDao.queryByName(admin.getUsername()));

    }
}
