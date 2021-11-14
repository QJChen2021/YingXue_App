package top.baizhi.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MonthAndCount implements Serializable {
    private String month;
    private Integer count;
}
