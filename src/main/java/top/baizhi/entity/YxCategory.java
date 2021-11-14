package top.baizhi.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class YxCategory implements Serializable {

  private String id;
  private String cateName;
  private int levels;
  private String parentId;

}
