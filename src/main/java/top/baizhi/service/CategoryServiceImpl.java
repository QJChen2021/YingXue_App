package top.baizhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.baizhi.dao.CategoryDao;
import top.baizhi.entity.YxCategory;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<YxCategory> queryByLevels(int levels) {
        return categoryDao.queryByLevels(levels);
    }

    @Override
    public List<YxCategory> queryByParent(String parentId) {
        return categoryDao.queryByParent(parentId);
    }

    @Override
    public void insertLevels(YxCategory yxCategory) {
        YxCategory yxCategory1 = new YxCategory();
        yxCategory1.setId(UUID.randomUUID().toString());
        yxCategory1.setCateName(yxCategory.getCateName());
        yxCategory1.setLevels(yxCategory.getLevels());
        yxCategory1.setParentId(null);
        categoryDao.insertLevels(yxCategory1);
    }

    @Override
    public void insertCategory(YxCategory yxCategory) {
        YxCategory yxCategory1 = new YxCategory();
        yxCategory1.setId(UUID.randomUUID().toString());
        yxCategory1.setCateName(yxCategory.getCateName());
        yxCategory1.setLevels(yxCategory.getLevels());
        yxCategory1.setParentId(yxCategory.getParentId());
        System.out.println(yxCategory1);
        categoryDao.insertCategory(yxCategory1);
    }

    @Override
    public void deleteByParentId(String id) {
        categoryDao.deleteByParentId(id);
    }

    @Override
    public void updateByParentId(String cateName, String id) {
        categoryDao.updateByParentId(cateName,id);
    }

    @Override
    public void deleteLevels(String id) {
        categoryDao.deleteLevels(id);
    }
}
