package top.baizhi.dao;

import org.springframework.stereotype.Repository;
import top.baizhi.entity.Video;
import top.baizhi.vo.FindLevels;

import java.util.List;

@Repository
public interface VideoDao {

    public Integer findAllPage();

    public List<Video> findAll(Integer page,Integer size);

    public List<FindLevels> findLevels(Integer levels);

    public List<FindLevels> findLevels2(Integer levels);

    public void add(Video video);

    public void deleteByVideo(String id);
}
