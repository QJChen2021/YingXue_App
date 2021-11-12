package top.baizhi.service;

import org.springframework.web.multipart.MultipartFile;
import top.baizhi.entity.Video;
import top.baizhi.vo.FindLevels;

import java.util.List;

public interface VideoService {

    public Integer findAllPage();

    public List<Video> findAll(Integer page);

    public List<FindLevels> findLevels(Integer levels);

    public List<FindLevels> findLevels2(Integer levels);

    public void add(MultipartFile video,String title, String brief, String categoryId);

    public void deleteByVideo(String id,String coverPath,String videoPath);
}
