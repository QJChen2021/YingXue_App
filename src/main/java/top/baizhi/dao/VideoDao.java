package top.baizhi.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.baizhi.entity.Video;
import top.baizhi.vo.FindLevels;

import java.util.List;
import java.util.Map;

@Repository
public interface VideoDao {

    public Integer findAllPage();

    public List<Video> findAll(Integer page,Integer size);

    public List<FindLevels> findLevels(Integer levels);

    public List<FindLevels> findLevels2(Integer levels);

    public void add(Video video);

    public void deleteByVideo(String id);
}
