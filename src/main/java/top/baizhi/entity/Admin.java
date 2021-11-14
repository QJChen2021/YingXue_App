package top.baizhi.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {

  private String id;
  private String username;
  private String password;
}
