package com.ExcelUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;


public class ReadExcelPerson {
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

    // 构造函数创建一个ExcelReader
    public ReadExcelPerson(String inputfile) throws IOException, Exception {
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
                            if (cell.get(0).indexOf("请在提交该表前仔细阅读填表说明") != -1) {
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

    // 用于读取文件的一行
    public String readLine() throws IOException {
        // excel文件通过poi读取文件
        if (filetype.equalsIgnoreCase("xls")) {
            // 根据currSheet值获得当前的sheet
            HSSFSheet sheet = workbook.getSheetAt(currSheet);

            // 判断当前行是否到但前Sheet的结尾
            if (currPosition > sheet.getLastRowNum()) {
                // 当前行位置清零
                currPosition = 0;
                // 判断是否还有Sheet
                while (currSheet < numOfSheets - 1) {
                    // 得到下一张Sheet
                    currSheet += 1;
                    sheet = workbook.getSheetAt(currSheet);

                    // 当前行数是否已经到达文件末尾
                    if (currPosition > sheet.getLastRowNum()) {
                        // 当前Sheet指向下一张Sheet
                        currSheet++;
                        continue;
                    } else {
                        // 获取当前行数
                        int row = currPosition;
                        currPosition++;
                        // 读取当前行数据
                        return getLine(sheet, row);
                    }
                }
                return null;
            }
            // 获取当前行数
            int row = currPosition;
            currPosition++;
            // 读取当前行数据
            return getLine(sheet, row);
        }
        return null;
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

            //判断是否是合并单元格
/*			boolean is = isMergedRegion(sheet,rowNum,1);
            if(is){
				//获取合并单元格值
				cellvalue = getMergedRegionValue(sheet, rowNum,1);
			}*/
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
//        buffer.substring(0, buffer.length() - 1);
        return buffer.toString();
    }

    // 返回Sheet的一行数据
    private String getLine(HSSFSheet sheet, int row) {
        // 根据行数取得Sheet的一行
        HSSFRow rowline = sheet.getRow(row);
        // 创建字符创缓冲区
        StringBuffer buffer = new StringBuffer();
        // 获取当前行的列数
        int filledColumns = rowline.getLastCellNum();
        HSSFCell cell = null;
        // 循环遍历所有列
        for (int i = 0; i < filledColumns; i++) {
            // 取得当前Cell
            cell = rowline.getCell((short) i);
            String cellvalue = null;
            if (cell != null) {
                // 判断当前Cell的Type
                switch (cell.getCellType()) {

                    // Cell的Type为NUMERIC
                    case HSSFCell.CELL_TYPE_NUMERIC: {
                        // 判断当前的cell是否为Date
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
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
                    // 默认的Cell值
                    default:
                        cellvalue = " ";
                }
            } else {
                cellvalue = "";
            }
            // 在每个字段之间插入分割符
            buffer.append(cellvalue).append(EXCEL_LINE_DELIMITER);
            // 去除最后一个分隔符
            buffer.substring(0, buffer.length() - 1);
        }
        return buffer.toString();
    }

    private boolean isMergedRegion(Sheet sheet, int row, int col) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstCol = range.getFirstColumn();
            int lastCol = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (col >= firstCol && col <= lastCol) {
                    return true;
                }
            }
//    		Region region = sheet.getMergedRegionAt(i);
//    		HSSFCell hCell = sheet.getRow(region.getRowFrom()).getCell(region.getColumnFrom());
//    		System.out.println("第"+(i+1)+"个合并单元格值为："+hCell.getStringCellValue().toString());
//    		if(hCell != null){
//    			return true;
//    		}
        }
        return false;

    }

    private String getMergedRegionValue(HSSFSheet sheet, int row, int col) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        System.out.println("合并的行数：" + sheetMergeCount);
        for (int i = 0; i < sheetMergeCount; i++) {
            /*CellRangeAddress range = sheet.getMergedRegion(i);
            int firstCol = range.getFirstColumn();
            int lastCol = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(col >= firstCol && col <= lastCol){
                    HSSFRow rRow = sheet.getRow(firstRow);
                    HSSFCell cCell = rRow.getCell(firstCol);
                    return getValue(cCell);
                }
            }*/
//    		Region region = sheet.getMergedRegionAt(i);
//    		HSSFCell hCell = sheet.getRow(region.getRowFrom()).getCell(region.getColumnFrom());
//    		System.out.println("第"+(i+1)+"-----个合并单元格值为："+hCell.getStringCellValue().toString());
//    		if(hCell != null){
//    			return getValue(sheet,row,hCell);
//    		}
        }
        return null;

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
            ReadExcelPerson er = new ReadExcelPerson("D://需求文档//待遇支付人员管理//缴费清单明细-带公共账户缴费的.xls");
            List<List<String>> row = er.readExcel();
            //得到excel数据后，写处理数据的代码就可以了，如：插入数据库等等
//            System.out.println(row);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
