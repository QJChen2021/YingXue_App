package top.baizhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.baizhi.service.VideoService;
import top.baizhi.vo.FindLevels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping("findAll")
    public Map<String,Object> findAll(Integer page){
        videoService.findAll(page);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("page",page);
        map.put("pagenum",videoService.findAllPage());
        map.put("tableData", videoService.findAll(page));
        return map;
    }

    @RequestMapping("findLevels")
    public List<FindLevels> findLevels(Integer levels){
        return videoService.findLevels(levels);
    }

    @RequestMapping("findLevels2")
    public List<FindLevels> findLevels2(Integer levels) {
        return videoService.findLevels2(levels);
    }

    @RequestMapping("add")
    public void add(@RequestParam MultipartFile video,String title, String brief, String categoryId){
        videoService.add(video,title,brief,categoryId);
    }

    @RequestMapping("deleteByVideo")
    public void deleteByVideo(String id,String coverPath,String videoPath){
        videoService.deleteByVideo(id,coverPath,videoPath);
    }
}
