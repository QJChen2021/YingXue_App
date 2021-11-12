package top.baizhi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Excel(name = "编号")
    private String id;
    @Excel(name = "姓名")
    private String username;
    @Excel(name = "头像",type = 2)
    private String headpath;
    @Excel(name = "成绩")
    private Double score;
    @Excel(name = "生日",exportFormat = "yyyy年MM月dd日",importFormat = "yyyy年MM月dd日")
    private Date bir;
}
