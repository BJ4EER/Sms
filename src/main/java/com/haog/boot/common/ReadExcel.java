package com.haog.boot.common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {
  public List<List> readExcel(MultipartFile file) throws IOException {
    // 用于转换单元格的日期数据
    ExcelUtil excelUtil = new ExcelUtil ( );
    // 获取工作簿
    XSSFWorkbook book = new XSSFWorkbook (file.getInputStream ( ));
    // 获取工作表
    XSSFSheet sheet = book.getSheetAt (0);
    // 列表用于存储每行的数据
    List<List> listRow = new ArrayList ( );
    // 普通for循环
    // 开始索引0 结束索引
    int lastRowNum = sheet.getLastRowNum ( );
//    System.out.println ("最后一行：" + lastRowNum);
    for (int i = 2; i <= lastRowNum; i++) {
      // 获取单元格
      XSSFRow row = sheet.getRow (i);
      List<String> listCell = new ArrayList<> ( );
      if (row != null && !row.equals ("")) {
        for (Cell cell : row) {
          if (cell != null && !cell.equals ("")) {
            // 此处是把单元格都转换成String类型
            String cellValue = excelUtil.importByExcelForDate (cell);
//            System.out.println (cellValue);
            listCell.add (cellValue);
          }
        }
      }
      listRow.add (listCell);
    }
    return listRow;
  }
}
