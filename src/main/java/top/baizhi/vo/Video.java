package top.baizhi.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Video implements Serializable {
    private String id;
    private String videoTitle;
    private String cover;
    private String path;
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date uploadTime;
    private String description;
    private Integer likeCount;
    private String cateName;
    private String userPhoto;
}
