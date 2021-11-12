package top.baizhi.dao;

import org.springframework.stereotype.Repository;
import top.baizhi.entity.YxCategory;
import top.baizhi.vo.Category;
import top.baizhi.vo.Video;

import java.util.List;

@Repository
public interface AppDao {
    public List<Video> queryVideo();

    public List<Category> queryAllCategory(String parentId,Integer levels);
}
