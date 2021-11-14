package top.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Video implements Serializable {

  private String id;
  private String title;
  private String brief;
  private String coverPath;
  private String videoPath;
  @JsonFormat(pattern = "yyyy年MM月dd日")
  private Date createDate;
  private YxCategory categoryId;
  private User userId;
  private String groupId;

}
