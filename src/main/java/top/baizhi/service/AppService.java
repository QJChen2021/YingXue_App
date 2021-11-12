package top.baizhi.service;

import top.baizhi.vo.Category;
import top.baizhi.vo.Video;

import java.util.List;

public interface AppService {
    public List<Video> queryVideo();

    public List<Category> queryAllCategory();
}
