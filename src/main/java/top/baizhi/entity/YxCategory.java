package top.baizhi.entity;

import lombok.Data;

@Data
public class YxCategory {

  private String id;
  private String cateName;
  private int levels;
  private String parentId;

}
