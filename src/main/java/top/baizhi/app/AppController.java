package top.baizhi.app;

import com.aliyun.oss.OSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.baizhi.common.ResultCommon;
import top.baizhi.service.AppService;
import top.baizhi.util.Oss;
import top.baizhi.vo.Video;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("yingx/app")
public class AppController {

    @Autowired
    private AppService appService;

    Oss oss = new Oss();

//    //驗證碼
//    @RequestMapping("getPhoneCode")
//    public ResultCommon getPhoneCode(String phone){
//        String code = SecurityCode.getSecurityCode();
//        Map<String,String> sms = oss.sms(phone);
//
//        if(sms==null){
//            ResultCommon r = ResultCommon.error();
//            r.setData(phone);
//            r.setMessage("驗證碼發送失败");
//            return r;
//        }else{
//
//        }
//    }

    @RequestMapping("queryByReleaseTime")
    public ResultCommon queryByReleaseTime(){
        List<Video> video = appService.queryVideo();
        return ResultCommon.ok().setMessage("查询成功").setData(video);
    }

    @RequestMapping("queryAllCategory")
    public ResultCommon queryAllCategory(){
        return ResultCommon.ok().setMessage("查询成功").setData(appService.queryAllCategory());
    }
}
