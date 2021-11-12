package top.baizhi.util;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Map;

public class Oss {

    // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    String accessKeyId = "LTAI5tNFkvyeiGq5XSGa21bd";
    String accessKeySecret = "LBBkLskNwaGmZ4O3Ld8ClfezgK06ek";

    //上传文件
    public void update(MultipartFile file,String filename) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        // 依次填写Bucket名称（例如examplebucket）、Object完整路径（例如exampledir/exampleobject.txt）和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        try {
            ossClient.putObject("yingxue-czm", "headImg/"+filename, new ByteArrayInputStream(file.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }


        // 关闭OSSClient。
        ossClient.shutdown();

    }

    //删除文件
    public void delete(String filename){
        // 填写Bucket名称。
        String bucketName = "yingxue-czm";
        // 填写文件完整路径。文件完整路径中不能包含Bucket名称。
        String objectName = filename;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件或目录。如果要删除目录，目录必须为空。
        ossClient.deleteObject(bucketName, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();

    }

    //视频截帧
    public String cutVideoFPS(String videopath) throws IOException {
        // 填写视频文件所在的Bucket名称。
        String bucketName = "yingxue-czm";
        // 填写视频文件的完整路径。若视频文件不在Bucket根目录，需携带文件访问路径，例如examplefolder/videotest.mp4。
        String objectName = videopath;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 使用精确时间模式截取视频50s处的内容，输出为JPG格式的图片，宽度为800，高度为600。
        String style = "video/snapshot,t_50000,f_jpg,w_800,h_600";
        // 指定过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        System.out.println("连接地址："+signedUrl);
        // 关闭OSSClient。
        ossClient.shutdown();
        return signedUrl.toString();
    }

    //上传网络流
    public void updateWeb(String url,String filename) throws IOException {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写网络流地址。
        InputStream inputStream = new URL(url.toString()).openStream();
        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject("yingxue-czm", "videoFPS/"+filename+".jpg", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    //發送驗證碼
    public Map<String, String> sms(String phone){
        DefaultProfile profile = DefaultProfile.getProfile("cn-beijing", "LTAI5tCMFiZt6CTDVDEbSt52", "GteSa8JrOL8SWD4GdcEaqd507H4mz8");//自己账号的AccessKey信息
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");//短信服务的服务接入地址
        request.setSysVersion("2017-05-25");//API的版本号
        request.setSysAction("SendSms");//API的名称
        request.putQueryParameter("PhoneNumbers", phone);//接收短信的手机号码
        request.putQueryParameter("SignName", "登录验证");//短信签名名称
        request.putQueryParameter("TemplateCode", "SMS_402064");//短信模板ID
        request.putQueryParameter("TemplateParam", "{\"code\":\"1111\",\"product\":\"【应学】\"}");//短信模板变量对应的实际值
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            Object parse = JSON.parse(response.getData());
            System.out.println(parse);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    //下载
    public void download(String fileName){
// 填写Bucket名称。
        String bucketName = "yingxue-czm";
// 填写不包含Bucket名称在内的Object完整路径，例如testfolder/exampleobject.txt。
        String objectName = "headImg/"+fileName;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 下载Object到本地文件，并保存到指定的本地路径中。如果指定的本地文件存在会覆盖，不存在则新建。
// 如果未指定本地路径，则下载后的文件默认保存到示例程序所属项目对应本地路径中。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File("D:\\temp\\OSSDownload\\"+fileName));

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
