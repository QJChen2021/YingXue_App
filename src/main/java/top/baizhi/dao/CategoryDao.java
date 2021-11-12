package top.baizhi.dao;

import org.springframework.stereotype.Repository;
import top.baizhi.entity.YxCategory;

import java.util.List;

@Repository
public interface CategoryDao {

    List<YxCategory> queryByLevels(int levels);

    List<YxCategory> queryByParent(String parentId);

    void insertLevels(YxCategory yxCategory);

    void insertCategory(YxCategory yxCategory);

    void deleteByParentId(String id);

    void updateByParentId(String cateName,String id);

    void deleteLevels(String id);

}
