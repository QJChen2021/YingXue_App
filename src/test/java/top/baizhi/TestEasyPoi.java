package top.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestEasyPoi {

    @Test
    public void tesetEasyPoi(){
        Student tom = new Student("1", "tom","C:\\Users\\saunt\\Desktop\\1.jpg", 100.0, new Date());
        Student tom1 = new Student("2", "tom1", "C:\\Users\\saunt\\Desktop\\1.jpg", 100.0, new Date());
        Student tom2 = new Student("3", "tom2", "C:\\Users\\saunt\\Desktop\\1.jpg", 100.0, new Date());
        Student tom3 = new Student("4", "tom3", "C:\\Users\\saunt\\Desktop\\1.jpg", 100.0, new Date());

//        List<Student> students = Arrays.asList(tom, tom1, tom2, tom3);

        List<Student> students = new ArrayList<>();
        students.add(tom);
        students.add(tom1);
        students.add(tom2);
        students.add(tom3);

        //将list集合导出
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("学生表","学生"),Student.class, students);
        try {
            workbook.write(new FileOutputStream(new File("D:/student.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testInsert(){
        //创建导入对象
        ImportParams params = new ImportParams();
        params.setTitleRows(1); //表格标题行数,默认0
        params.setHeadRows(1);  //表头行数,默认1

        //获取导入数据
        try {
            List<Student> list = ExcelImportUtil.importExcel(new FileInputStream(new File("D:/student.xls")), Student.class, params);
            for (Student student : list) {
                System.out.println(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
