package top.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class User {

  @Excel(name = "id")
  private String id;
  @Excel(name = "电话")
  private String phone;
  @Excel(name = "用户名")
  private String username;
  @Excel(name = "头像",type = 2)
  private String head_img;
  @Excel(name = "简介")
  private String brief;
  @Excel(name = "微信")
  private String wechat;
  @Excel(name = "创建时间",exportFormat = "yyyy年MM月dd日")
  @JsonFormat(pattern = "yyyy年MM月dd日")
  private Date create_date;
  @Excel(name = "状态")
  private Integer status;
}
