package com.ExcelUtil;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * 导出Excel方法重构
 * <p>Title:PubExcleUtil</p>
 * <p>Description:</p>
 * <p>Company:</p>
 * @author:chenjx
 * @date:2016年8月16日
 */
public class PubExcleUtil {
	private OutputStream outx = null;
	/**
	 * 新建工作表
	 * @param wb
	 * @param sheetNum
	 */
	public void sheetStyle(SXSSFWorkbook wb,int sheetNum){
		Sheet sheet = wb.createSheet();
		wb.setSheetName(sheetNum, "工作表"+sheetNum);
		sheet.setDefaultColumnWidth((short) 14);
	}
	/**
	 * 新建标题，样式
	 * @param wb 
	 * @param sheet
	 * @param i 设定开始行数
	 * @param j 设定开始列数
	 * @param title1 标题
	 * @param strCol 标题数组 
	 * @return
	 */
	public boolean ExcelTitle(SXSSFWorkbook wb,Sheet sheet,int i,int j,String title1,String[] strCol){
		// 样式1 黑体字 写标题用
		Font font1 = wb.createFont();
		font1.setColor(Font.COLOR_NORMAL);
		font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font1.setFontName("黑体");
		font1.setFontHeightInPoints((short) 18);
		CellStyle title = wb.createCellStyle();
		title.setFont(font1);
		title.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		title.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		title.setBorderBottom(CellStyle.BORDER_THIN);// 下边框
		title.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
		title.setBorderRight(CellStyle.BORDER_THIN);// 右边框
		title.setBorderTop(CellStyle.BORDER_THIN);// 上边框
		try {
			// 写标题并且合并单元格
			Row row = sheet.createRow(i);// 建立新行
			Cell cell = row.createCell(j);// 建立新cell
			cell.setCellStyle(title);
			cell.setCellValue(title1);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, strCol.length-1));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 新建标题
	 * @param wb
	 * @param sheet
	 * @param i 设定开始行数
	 * @param j 设定开始列数
	 * @param strCol 列的集合
	 * @return
	 */
	public boolean ExcelCol(SXSSFWorkbook wb,Sheet sheet,int i,int j,String[] strCol){
		// 样式2 宋体 写正文时用
		Font font2 = wb.createFont();
		font2.setColor(Font.COLOR_NORMAL);
		font2.setFontName("宋体");
		font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font2.setFontHeightInPoints((short) 10);
		CellStyle normal = wb.createCellStyle();
		normal.setFont(font2);
		normal.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		normal.setVerticalAlignment(CellStyle.VERTICAL_TOP);// 垂直居中
		normal.setBorderBottom(CellStyle.BORDER_THIN);// 下边框
		normal.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
		normal.setBorderRight(CellStyle.BORDER_THIN);// 右边框
		normal.setBorderTop(CellStyle.BORDER_THIN);// 上边框
		Row row = sheet.createRow(i);// 建立新行
		try {
			for (int tmpi = 1; tmpi <= strCol.length; tmpi++) {
				Cell cell = row.createCell(j); // 建立新cell
				cell.setCellStyle(normal);
				cell.setCellValue(strCol[tmpi-1]);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				j++;
			}
		} catch (Exception e) {
//			this.state = "写表头出错，请检查正确的列数应该是 " + col + " 列";
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 新建正文
	 * @param wb
	 * @param sheet
	 * @param colNum 
	 * @param i
	 * @param j
	 * @param str
	 * @return
	 */
	public boolean ExcelCreate(SXSSFWorkbook wb,Sheet sheet,int colNum,int i,int j,String[] str){
		// 样式2 宋体左对齐 写正文时用
		Font fontleft = wb.createFont();
		fontleft.setColor(Font.COLOR_NORMAL);
		fontleft.setFontName("宋体");
		fontleft.setFontHeightInPoints((short) 10);
		CellStyle normal_left = wb.createCellStyle();
		normal_left.setFont(fontleft);
		normal_left.setAlignment(CellStyle.ALIGN_LEFT);// 水平左齐
		normal_left.setVerticalAlignment(CellStyle.VERTICAL_TOP);// 垂直居中
		normal_left.setBorderBottom(CellStyle.BORDER_THIN);// 下边框
		normal_left.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
		normal_left.setBorderRight(CellStyle.BORDER_THIN);// 右边框
		normal_left.setBorderTop(CellStyle.BORDER_THIN);// 上边框
		try {
			for (int maintmpi = 1; maintmpi <= colNum; maintmpi++) {
				Row row = sheet.createRow(i);
				j = 0;
				for (int tmpj = 1; tmpj <= str.length; tmpj++) {
					Cell cell = row.createCell(j); // 建立新cell
					cell.setCellStyle(normal_left);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(str[tmpj - 1]); // 设置中西文结合字符串
					j++;
				}
				i++;
				// 当超过10000时强制刷新入磁盘，定义成公共变量！！！！！！
				if (maintmpi % 10000 == 0) {
					((SXSSFSheet) sheet).flushRows(10000);
				}
			}
			for (int tmpj = 1; tmpj <= 6; tmpj++) {
				int colWidth = str[tmpj - 1].length();
				if (colWidth > 14) {
					sheet.autoSizeColumn(tmpj - 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/*public void ExcelStyle(SXSSFWorkbook wb){
		*//********************* 样式定义 ******************************************//*
		// 样式2 宋体左对齐 写正文时用
		Font fontleft = wb.createFont();
		fontleft.setColor(Font.COLOR_NORMAL);
		fontleft.setFontName("宋体");
		fontleft.setFontHeightInPoints((short) 10);
		CellStyle normal_left = wb.createCellStyle();
		normal_left.setFont(fontleft);
		normal_left.setAlignment(CellStyle.ALIGN_LEFT);// 水平左齐
		normal_left.setVerticalAlignment(CellStyle.VERTICAL_TOP);// 垂直居中
		normal_left.setBorderBottom(CellStyle.BORDER_THIN);// 下边框
		normal_left.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
		normal_left.setBorderRight(CellStyle.BORDER_THIN);// 右边框
		normal_left.setBorderTop(CellStyle.BORDER_THIN);// 上边框

		// 样式2 宋体右对齐 写正文时用
		Font fontright = wb.createFont();
		fontright.setColor(Font.COLOR_NORMAL);
		fontright.setFontName("宋体");
		fontright.setFontHeightInPoints((short) 12);
		CellStyle normal_right = wb.createCellStyle();
		normal_right.setFont(fontright);
		normal_right.setAlignment(CellStyle.ALIGN_LEFT);// 水平左齐
		normal_right.setVerticalAlignment(CellStyle.VERTICAL_TOP);// 垂直居中
		normal_right.setBorderBottom(CellStyle.BORDER_THIN);// 下边框
		normal_right.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
		normal_right.setBorderRight(CellStyle.BORDER_THIN);// 右边框
		normal_right.setBorderTop(CellStyle.BORDER_THIN);// 上边框

		// 样式3 宋体 写正文用 有边框
		Font font3 = wb.createFont();
		font3.setColor(Font.COLOR_NORMAL);
		font3.setFontName("宋体");
		font3.setFontHeightInPoints((short) 12);
		CellStyle border = wb.createCellStyle();
		border.setFont(font3);
		border.setAlignment(CellStyle.ALIGN_LEFT);// 水平居中
		border.setVerticalAlignment(CellStyle.VERTICAL_TOP);// 垂直居中
		border.setBorderBottom(CellStyle.BORDER_THIN);// 下边框
		border.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
		border.setBorderRight(CellStyle.BORDER_THIN);// 右边框
		border.setBorderTop(CellStyle.BORDER_THIN);// 上边框
	}*/

	public boolean excelUtil(String[] strCol,String[] str,String title1,int rowNum,String fileType){
		// 声明变量，开始为标题和内容写样式
		SXSSFWorkbook wb = new SXSSFWorkbook();
		Sheet sheet;
		if(rowNum<=65000){
			System.out.println(1);
			int i = 0;// 行
			int j = 0;// 列
			int num = (rowNum>65000)?65000:rowNum;
			sheet = wb.createSheet();
			ExcelTitle(wb, sheet, i, j, title1, strCol);//写标题
			i++;
			ExcelCol(wb, sheet, i, j, strCol);//写列名
			i++;
			ExcelCreate(wb, sheet, num, i, j, str);//内容
		}else{
			System.out.println(2);
			for(int k =0;k<=(rowNum/65000);k++){
				int i = 0;// 行
				int j = 0;// 列
				int num = (rowNum-65000*k>65000)?65000:rowNum-65000*k;
				sheet = wb.createSheet();
				ExcelTitle(wb, sheet, i, j, title1, strCol);//写标题
				i++;
				ExcelCol(wb, sheet, i, j, strCol);//写列名
				i++;
				ExcelCreate(wb, sheet, num, i, j, str);//内容
			}
		}
		try {
			if (this.outx != null) {
				wb.write(this.outx);
				outx.close();
			} else if (title1 != null) {
				FileOutputStream fileOut = null;
				try {
					fileOut = new FileOutputStream("D:\\test."+fileType);
					wb.write(fileOut);
				} finally {
					if (fileOut != null) {
						fileOut.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// log.info("===="+this.FileName+"=====Excel文件行数为："+"文件无法继续写下去了！");
			System.out.println("文件导出出错");
//			this.state = "写EXCEL文件出错，出错信息：" + e.getMessage();
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		String[] strCol = {"丽人1","丽人2","丽人3","丽人4","丽人5","丽人6"};
		String[] str = { "12", "23333333333333333333", "33", "44", "22","11111111111111111111111" };
		String title1 = "工作表";
		int rowNum = 120000;
		String fileType = "xls";
		PubExcleUtil excel = new PubExcleUtil();
		boolean reasult = excel.excelUtil(strCol, str, title1, rowNum, fileType);
		System.out.println(reasult);
	}
}
