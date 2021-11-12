package top.baizhi.vo;

import lombok.Data;
import top.baizhi.entity.YxCategory;

import java.util.List;

@Data
public class Category {
    private String id;
    private String cateName;
    private int levels;
    private String parentId;
    private List<Category> categoryList;
}
