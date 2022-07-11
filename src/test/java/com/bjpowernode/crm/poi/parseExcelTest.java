package com.bjpowernode.crm.poi;

import com.bjpowernode.crm.commons.utils.HSSFUtils;
import jdk.internal.util.xml.impl.Input;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 使用apache-poi解析excel文件
 */
public class parseExcelTest {
    public static void main(String[] args) throws IOException {
        //根据excel文件生成HssFworkbook对象，封装了excel文件的所有信息
     InputStream fileInputStream = new FileInputStream("D:\\CRM客户管理系统\\excel\\student.xls");
        HSSFWorkbook wb = new HSSFWorkbook(fileInputStream);
        //根据wb获取HSSFSheet对象封装了一页的所有信息
        HSSFSheet sheet = wb.getSheetAt(0);//页的下标,下标从0开始,依次增加
        //根据sheet获取HSSFRow对象，封装了一行的所有信息
        HSSFRow row = null;
        HSSFCell  cell=null;
        for(int i=0;i<sheet.getLastRowNum();i++){//sheet.getlastRowNum();最后一行的下标
            row=sheet.getRow(i);//行的下标，从0开始依次增加
            //根据row获取HssFCell对象,封装了一列所有信息
            for(int j=0;j<row.getLastCellNum();j++){//row.getlastcellnum；最后一列的下标+1
                cell=row.getCell(j);//列的下标从零开始 依次增加
                //获取列中的数据
                System.out.print(HSSFUtils.getCellValueForStr(cell)+" ");
            }
            //换行
            System.out.println();

        }

    }
    //从指定的HsFCell对象中获取列的值

}
