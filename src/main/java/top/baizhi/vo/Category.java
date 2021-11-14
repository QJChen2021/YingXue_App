package top.baizhi.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Category implements Serializable {
    private String id;
    private String cateName;
    private int levels;
    private String parentId;
    private List<Category> categoryList;
}
