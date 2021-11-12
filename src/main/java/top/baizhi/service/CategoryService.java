package top.baizhi.service;

import top.baizhi.entity.YxCategory;

import java.util.List;

public interface CategoryService {

    List<YxCategory> queryByLevels(int levels);

    List<YxCategory> queryByParent(String parentId);

    void insertLevels(YxCategory yxCategory);

    void insertCategory(YxCategory yxCategory);

    void deleteByParentId(String id);

    void updateByParentId(String cateName,String id);

    void deleteLevels(String id);
}
