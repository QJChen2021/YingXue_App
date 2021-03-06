package top.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import io.goeasy.GoEasy;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.baizhi.entity.User;
import top.baizhi.service.UserService;
import top.baizhi.util.Oss;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    Oss oss = new Oss();

    @RequestMapping("findAll")
    public Map<String,Object> findAll(Integer page) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("page",page);
        map.put("pagenum",userService.findAllPage());
        map.put("tableData",userService.findAll(page));
        return map;
    }

    @RequestMapping("add")
    public void add(@RequestParam MultipartFile photo, HttpServletRequest request, User user){
        userService.add(photo,request,user);
        Map<String, Object> map = userService.queryByMonthCount();
        String json = JSON.toJSONString(map);
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-3caba4bc937d40679055db34fab563b1");
        goEasy.publish("my_channel", json);
    }

    @RequestMapping("delete")
    public void delete(String id){
        userService.delete(id);
        Map<String, Object> map = userService.queryByMonthCount();

        String json = JSON.toJSONString(map);
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-3caba4bc937d40679055db34fab563b1");
        goEasy.publish("my_channel", json);
    }

    @RequestMapping("updateStatus")
    public void updateStatus(String id,String number){
        userService.updateStatus(id,number);
    }

    @RequestMapping("expportData")
    public void expportData(int page){
        List<User> list = userService.findAll(page);
        for (User user : list) {
            String headImg = user.getHead_img().split("http://yingxue-czm.oss-cn-beijing.aliyuncs.com/headImg/")[1];
            System.out.println("????????????"+headImg);
            oss.download(headImg);
            user.setHead_img("D:\\temp\\OSSDownload\\"+headImg);
        }
        //???list????????????
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("?????????????????????","??????"),User.class, list);
        try {
            workbook.write(new FileOutputStream(new File("D:/yxuser.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("userEcharts")
    public Map<String,Object> userEcharts(){
        return userService.queryByMonthCount();
    }
}
