package top.baizhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.baizhi.dao.VideoDao;
import top.baizhi.entity.User;
import top.baizhi.entity.Video;
import top.baizhi.entity.YxCategory;
import top.baizhi.util.Oss;
import top.baizhi.vo.FindLevels;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    private Integer size = 1;

    Oss oss = new Oss();

    @Override
    public Integer findAllPage() {
        return videoDao.findAllPage()%size==0? videoDao.findAllPage()/size : videoDao.findAllPage()/size+1;
    }

    @Override
    public List<Video> findAll(Integer page) {
        Integer start = (page - 1) * size;
        Integer allPage = videoDao.findAllPage();
        return videoDao.findAll(start, size);
    }

    @Override
    public List<FindLevels> findLevels(Integer levels) {
        return videoDao.findLevels(levels);
    }

    @Override
    public List<FindLevels> findLevels2(Integer levels) {
        return videoDao.findLevels2(levels);
    }

    @Override
    public void add(MultipartFile video,String title, String brief, String categoryId) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = simpleDateFormat.format(new Date());
        String filename = format+ UUID.randomUUID().toString().replace("-", "")+(video.getOriginalFilename()).substring((video.getOriginalFilename()).lastIndexOf("."));

        String coverpath = filename.split(".mp4")[0];

        YxCategory yxCategory = new YxCategory();
        yxCategory.setId(categoryId);

        User user = new User();
        user.setId("1");

        Video video1 = new Video();
        video1.setId(UUID.randomUUID().toString());
        video1.setTitle(title);
        video1.setBrief(brief);
        video1.setCoverPath("http://yingxue-czm.oss-cn-beijing.aliyuncs.com/videoFPS/"+coverpath+".jpg");
        video1.setVideoPath("http://yingxue-czm.oss-cn-beijing.aliyuncs.com/headImg/"+filename);
        video1.setCreateDate(new Date());
        video1.setCategoryId(yxCategory);
        video1.setUserId(user);
        video1.setGroupId(null);

        try{
            oss.update(video,filename);
            String url = oss.cutVideoFPS("headImg/" + filename);
            oss.updateWeb(url,coverpath);
            videoDao.add(video1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByVideo(String id,String coverPath,String videoPath) {
        videoDao.deleteByVideo(id);
        oss.delete(coverPath.split("http://yingxue-czm.oss-cn-beijing.aliyuncs.com/")[1]);
        oss.delete(videoPath.split("http://yingxue-czm.oss-cn-beijing.aliyuncs.com/")[1]);
    }


}
