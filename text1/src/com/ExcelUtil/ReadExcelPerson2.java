package com.ExcelUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.rowset.internal.CachedRowSetWriter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;


public class ReadExcelPerson2 {
    // 创建文件输入流
    private BufferedReader reader = null;
    // 文件类型
    private String filetype;
    // 文件二进制输入流
    private InputStream is = null;
    // 当前的Sheet
    private int currSheet;
    // 当前位置
    private int currPosition;
    // Sheet数量
    private int numOfSheets;
    // HSSFWorkbook
    HSSFWorkbook workbook = null;
    // 设置Cell之间以空格分割
    private static String EXCEL_LINE_DELIMITER = ",";
    //处理公式
    HSSFFormulaEvaluator evaluator;

    // 构造函数创建一个ExcelReader
    public ReadExcelPerson2(String inputfile) throws IOException, Exception {
        // 判断参数是否为空或没有意义
        if (inputfile == null || inputfile.trim().equals("")) {
            throw new IOException("NO input file!!!");
        }
        // 取得文件名的后缀名赋值给filetype
        filetype = inputfile.substring(inputfile.lastIndexOf(".") + 1);

        // 设置开始行为0
        currPosition = 0;
        // 设置当前位置为0
        currSheet = 0;
        // 创建文件输入流
        is = new FileInputStream(inputfile);

        // 判断文件格式
        if (filetype.equalsIgnoreCase("xls")) {
            // 如果是Excel文件则创建HSSFWorkbook读取
            workbook = new HSSFWorkbook(is);
            // 设置Sheet数
            numOfSheets = workbook.getNumberOfSheets();
        } else {
            throw new Exception("File Type incorrrect!");
        }

    }

    /**
     * 读取excel
     *
     * @return
     */
    public List<List<String>> readExcel() {
        List<List<String>> row = new ArrayList<List<String>>();
        try {
            for (currSheet = 0; currSheet < 1; currSheet++) {
//                for(currSheet=0;currSheet< workbook.getNumberOfSheets(); currSheet++){
                if (filetype.equalsIgnoreCase("xls")) {
                    HSSFSheet sheet = workbook.getSheetAt(currSheet);//获得指定的表
                    if (sheet == null) {
                        continue;
                    }
                    //循环row
                    for (int rowNum = 1; rowNum < sheet.getDefaultRowHeight(); rowNum++) {
                        HSSFRow hssfRow = sheet.getRow(rowNum);//获得行对象
                        if (hssfRow == null) {
                            continue;
                        }
                        //循环cell
                        List<String> cell = new ArrayList<String>();
                        //获得本行中单元格的个数
                        for (short cellNum = 0; cellNum < hssfRow.getLastCellNum(); cellNum++) {
                            HSSFCell hssfCell = hssfRow.getCell(cellNum);
                            if (hssfCell == null || hssfCell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                continue;
                            }
                            cell.add(getValue(hssfCell));
                        }
                        if (!cell.isEmpty()) {
                            if (cell.get(0).indexOf("合计") != -1 || cell.get(0).indexOf("打印日期") != -1) {
                                break;
                            }
                            System.out.println(cell);
                            row.add(cell);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    /**
     * 获取一个单元格的值
     *
     * @param cell
     * @return
     */
    private String getValue(HSSFCell cell) {
        // 创建字符创缓冲区
        StringBuffer buffer = new StringBuffer();
        String cellvalue = null;
        if (cell != null) {
            //如果是最后的说明则表示读取已经结束，则停止读取
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted((HSSFCell) cell)) {
                        // 如果是Date类型则，取得该Cell的Date值
                        Date date = cell.getDateCellValue();
                        // 把Date转换成本地格式的字符串
                        cellvalue = cell.getDateCellValue().toLocaleString();
                    } else { // 如果是纯数字
                        // 取得当前Cell的数值,强制转换为int
                        Integer num = new Integer(
                                (int) cell.getNumericCellValue());
                        cellvalue = String.valueOf(num);
                    }
                    break;
                }
                // Cell的Type为STRING
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getStringCellValue().replaceAll("'", "''");
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                    break;
                // 默认的Cell值
                default:
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
//         在每个字段之间插入分割符
        buffer.append(cellvalue);
        // 去除最后一个分隔符
        return buffer.toString();
    }

    // 关闭资源
    public void closeResource() {
        try {
            if (is != null)
                is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 测试case
    public static void main(String[] args) {
        try {
            ReadExcelPerson2 er = new ReadExcelPerson2("D://需求文档//待遇支付人员管理//缴费清单（汇总表）_南京金城塑胶有限公司企业计划.xls");
            List<List<String>> row = er.readExcel();
            //得到excel数据后，写处理数据的代码就可以了，如：插入数据库等等
//            System.out.println(row);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
