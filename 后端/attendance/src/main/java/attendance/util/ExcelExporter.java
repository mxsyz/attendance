package attendance.util;
import attendance.constant.Reason;
import attendance.entity.LeaveAndField;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
public class ExcelExporter {
    public void writeUser(List<Object> dataList) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow head = sheet.createRow(0);
        String[] fields = getFiledName(dataList.get(0));
        for(int i=0;i<fields.length;i++)
        {
            head.createCell(i).setCellValue(fields[i]);
        }
        for(int i = 0 ;i<dataList.size();i++)
        {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < fields.length; j++)
            {
                System.out.println(getFieldValueByName(fields[j],dataList.get(i)));
               //row.createCell(j).setCellValue(getFieldValueByName(fields[j],dataList.get(i)).toString());
            }
        }
        /*try {
            FileOutputStream fos = new FileOutputStream("/excel/user.xls");
            workbook.write(fos);
            System.out.println("写入成功");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //将文件保存到指定的位置

        System.out.println("数据导出成功");
    }

    private String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }
    private String getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }
    public static void main(String[] args){
        ExcelExporter R = new ExcelExporter();
        LeaveAndField lf1 = new LeaveAndField();
        lf1.setJobNumber(1);
        lf1.setDescription("tse");
        lf1.setReason(Reason.valueOf("Field"));
        lf1.setId("0fzza1");
        List<Object> list=new ArrayList<Object>();
        list.add(lf1);
        R.writeUser(list);
    }
}
