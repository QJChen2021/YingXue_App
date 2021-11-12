package top.baizhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.baizhi.entity.YxCategory;
import top.baizhi.service.CategoryService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("queryByLevels")
    public List<YxCategory> queryByLevels(Integer levels){
        return categoryService.queryByLevels(levels);
    }

    @RequestMapping("queryByParent")
    public List<YxCategory> queryByParent(String parentId){
        return categoryService.queryByParent(parentId);
    }

    @RequestMapping("insertLevels")
    public void insertLevels(@RequestBody YxCategory yxCategory){
        categoryService.insertLevels(yxCategory);
    }

    @RequestMapping("insertCategory")
    public void insertCategory(@RequestBody YxCategory yxCategory){
        categoryService.insertCategory(yxCategory);
    }

    @RequestMapping("deleteByParentId")
    public void deleteByParentId(String id){
        categoryService.deleteByParentId(id);
    }

    @RequestMapping("updateByParentId")
    public void updateByParentId(String cateName,String id){
        categoryService.updateByParentId(cateName,id);
    }

    @RequestMapping("deleteLevels")
    public void deleteLevels(String id){
        categoryService.deleteLevels(id);
    }

}
