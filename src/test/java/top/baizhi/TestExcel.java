//package top.baizhi;
//
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//public class TestExcel {
//    @Test
//    public void testPoi(){
//        //创建Excel文档
//        Workbook workbook = new HSSFWorkbook();
//
//        //创建工作表   参数：工作表名称(sheet1,sheet2...)
//        //如果不指定工作表 默认按照：sheet1,sheet2...命名
//        Sheet sheet1 = workbook.createSheet("1学生信息表");
//        Sheet sheet2 = workbook.createSheet("2学生成绩表");
//
//        //创建一行   参数：行下标（下标从0开始）
//        Row row = sheet1.createRow(0);
//
//        //通过行来创建单元格   参数:单元格下标（下标从0开始）
//        Cell cell = row.createCell(0);
//
//        //给单元格设置内容
//        cell.setCellValue("这是第一行第0个单元格");
//
//        try {
//            //导出Excel文档
//            workbook.write(new FileOutputStream(new File("D://190Poi.xls")));
//
//            //释放资源
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
////    public void testPoi2(){
////        Student tom = new Student("1", "tom", 100.0, new Date());
////        Student tom1 = new Student("2", "tom1", 100.0, new Date());
////        Student tom2 = new Student("3", "tom2", 100.0, new Date());
////        Student tom3 = new Student("4", "tom3", 100.0, new Date());
////
////        List<Student> students = Arrays.asList(tom, tom1, tom2, tom3);
////
////        //创建Excel表格
////        HSSFWorkbook sheets = new HSSFWorkbook();
////
////        //创建工作区
////        HSSFSheet sheet1 = sheets.createSheet("学生管理");
////        HSSFSheet sheet2 = sheets.createSheet("部门管理");
////
////        //创建样式
////        HSSFCellStyle cellStyle = sheets.createCellStyle();
////        //创建日期样式
////        HSSFDataFormat dataFormat = sheets.createDataFormat();
////        //将日期样式设置给样式对象
////        cellStyle.setDataFormat(dataFormat.getFormat("yyyy年MM月dd日"));
////
////        sheet1.setColumnWidth(3,32*256);
////        //设置行高
////        HSSFRow row2 = sheet1.createRow(0);
////        //1/20
////        row2.setHeight((short)(20*20));
////
////        //设置字体格式
////        //构建字体
////        HSSFFont font = sheets.createFont();
////        font.setColor(Font.COLOR_RED); //设置红色
////        font.setBold(true);    //加粗
////        font.setFontHeightInPoints((short)24);  //字号
////
////        //创建样式对象
////        HSSFCellStyle cellStyle1 = sheets.createCellStyle();
////        cellStyle1.setFont(font);//将字体样式引入
////        cellStyle1.setAlignment(HorizontalAlignment.CENTER);//文字剧中
////
////        //合并列
////        HSSFCell cell2 = row2.createCell(0);
////        CellRangeAddress cellAddresses = new CellRangeAddress(0, 0, 0, 3);
////        sheet1.addMergedRegion(cellAddresses);
////        cell2.setCellStyle(cellStyle1);
////        cell2.setCellValue("用户数据");
////
////        String[] str = {"id","姓名","分数","生日"};
////        //使用学生管理对象 创建行和列
////        HSSFRow row = sheet1.createRow(1);
////        for (int i = 0; i < str.length; i++) {
////            HSSFCell cell = row.createCell(i);
////            cell.setCellValue(str[i]);
////        }
////
////        //将集合中的数据进行遍历 每一个对象占据一行
////        for (int i = 0; i < students.size(); i++) {
////            HSSFRow row1 = sheet1.createRow(i + 2);
////            //当前行的第一个单元格防止id的值  第二个单元格放置名字
////            HSSFCell cell = row1.createCell(0);
////            cell.setCellValue(students.get(i).getId());
////            row1.createCell(1).setCellValue(students.get(i).getUsername());
////            row1.createCell(2).setCellValue(students.get(i).getScore());
//////            row1.createCell(3).setCellValue(students.get(i).getBir());
////            //设置单元格日期样式
////            HSSFCell cell1 = row1.createCell(3);
////            cell1.setCellStyle(cellStyle);
////            cell1.setCellValue(students.get(i).getBir());
////
////            //创建样式对象
////        }
////
////        try{
////            sheets.write(new FileOutputStream(new File("D://students.xls")));
////        }catch (Exception e){
////            e.printStackTrace();
////        }finally {
////           try {
////               sheets.close();
////           }catch (Exception e1){
////               e1.printStackTrace();
////           }
////        }
////    }
//
//    @Test
//    public void testInsertExcel(){
//        HSSFWorkbook sheets = null;
//        try{
//            //读取xls文件
//            sheets = new HSSFWorkbook(new FileInputStream("D://students.xls"));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        //指定读取文件中的那个工作区
//        HSSFSheet sheet = sheets.getSheet("学生管理");
//        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
//            HSSFRow row = sheet.getRow(i);//获取当前行
//
//            HSSFCell cell = row.getCell(0);//id
//            String id = cell.getStringCellValue();
//
//            HSSFCell cell1 = row.getCell(1);//姓名
//            String name = cell1.getStringCellValue();
//
//            HSSFCell cell2 = row.getCell(2);//分数
//            double score = cell2.getNumericCellValue();
//
//            HSSFCell cell3 = row.getCell(3);//日期
//            Date bir = cell3.getDateCellValue();
//
//            Student student = new Student(id, name, score, bir);
//            System.out.println(student);
//
//        }
//    }
//}
