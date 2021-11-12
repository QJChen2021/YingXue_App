package top.baizhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.baizhi.dao.AppDao;
import top.baizhi.vo.Category;
import top.baizhi.vo.Video;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AppServiceImpl implements AppService {

    @Autowired
    private AppDao appDao;
    @Override
    public List<Video> queryVideo() {
        return appDao.queryVideo();
    }

    @Override
    public List<Category> queryAllCategory() {
        List<Category> list = appDao.queryAllCategory(null,1);
        for (Category category : list) {
            List<Category> list1 = appDao.queryAllCategory(category.getId(), null);
            category.setCategoryList(list1);
        }
        return list;
    }
}
