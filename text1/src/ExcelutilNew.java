
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * 创建一个Excel
 * <p>
 * Title:ExcelutilNew
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author:chenjx
 * @date:2016年8月15日
 */
public class ExcelutilNew {
	private String state = "";
	private String Title = "";
	private String Subject = "";
	private String SheetName = "";
	private int Row = 0;
	private int Col = 0;
	private String FileName = "";
	private String WherePart = "";
	private String NumCol = "";
	private OutputStream outx = null;

	public static void main(String[] args) {
		long starTime = System.currentTimeMillis();
		ExcelutilNew excel = new ExcelutilNew();

		boolean result = excel.PubCreateExcel();
		long endTime = System.currentTimeMillis();
		long time = endTime - starTime;
		System.out.println(time);
		System.out.println(result);
	}

	public boolean PubCreateExcel() {
		SXSSFWorkbook wb = new SXSSFWorkbook();
		/********************* 样式定义 ******************************************/
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
		// 样式2正文部分 无边框
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
		// 建立一个新工作表
		Sheet sheet = wb.createSheet();
		wb.setSheetName(0, "工作表");
		sheet.setDefaultColumnWidth((short) 14);
		PrintSetup myPrintSetup = sheet.getPrintSetup();
		myPrintSetup.setPaperSize((short) 9); // 设置成A4纸
		myPrintSetup.setLandscape(true); // 纸张横放
		// 声明变量，开始为标题和内容写样式
		Row row;
		Cell cell;
		int i = 0;// 行
		int j = 0;// 列
		// List<Object> list = new ArrayList<Object>();
		// list.add(1);
		// list.add("12");
		// list.add(new Date());
		// list.add("11111111111111111111111");
		// list.add(6.34);
		// list.add(4.444444);
		String[] value = { "12", "23333333333333333333", "33", "44", "22",
				"11111111111111111111111" };
		// 写标题并且合并单元格
		row = sheet.createRow((short) i);// 建立新行
		cell = row.createCell((short) j);// 建立新cell
		cell.setCellStyle(title);
		// cell.setCellValue(this.Subject);//设置中西文结合字符串
		cell.setCellValue("标题");
		sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 5));
		// 查询条件？？
		// i++;
		// row = sheet.createRow((short)i);//建立新行
		// cell = row.createCell((short)j);//建立新cell
		// cell.setCellStyle(normal);
		// cell.setCellValue(this.WherePart);//设置中西文结合字符串
		// 写列名
		i = i + 1;
		row = sheet.createRow(i);// 建立新行

		try {
			for (int tmpi = 1; tmpi <= 6; tmpi++) {
				cell = row.createCell(j); // 建立新cell
				cell.setCellStyle(normal);
				// cell.setCellValue(StrTool.getStr(this.Title, tmpi, "@"));
				// //设置中西文结合字符串
				cell.setCellValue("列名");
				cell.setCellType(Cell.CELL_TYPE_STRING);
				j++;
			}
		} catch (Exception e) {
			this.state = "写表头出错，请检查正确的列数应该是 " + Row + " 列";
			return false;
		}
		// 写内容
		i = i + 1;
		int k = 1;
		boolean isSheet = true;
		try {
			for (int maintmpi = 1; maintmpi <= 100000; maintmpi++) {
				row = sheet.createRow(i);
				j = 0;
				for (int tmpj = 1; tmpj <= 6; tmpj++) {
					cell = row.createCell(j); // 建立新cell
					cell.setCellStyle(normal_left);
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					// //设置cell编码解决中文高位字节截断
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(value[tmpj - 1]); // 设置中西文结合字符串
					// cell.setCellValue(list.get(tmpj-1));
					j++;
				}
				i++;
				// 当超过10000时强制刷新入磁盘，定义成公共变量！！！！！！
				if (maintmpi % 10000 == 0) {
					((SXSSFSheet) sheet).flushRows(10000);
				}
			}

			// System.out.println(sheet.getColumnWidth(1));
			// 根据单元格内容自动调整宽度,只有超出默认宽度才会自动调整宽度，也是设成全局
			for (int tmpj = 1; tmpj <= 6; tmpj++) {
				int colWidth = value[tmpj - 1].length();
				if (colWidth > 14) {
					sheet.autoSizeColumn(tmpj - 1);
				}
			}
			try {
				if (this.outx != null) {
					wb.write(this.outx);
					outx.close();
				} else if (this.FileName != null) {
					FileOutputStream fileOut = null;
					try {
						fileOut = new FileOutputStream("D:\\test.xls");
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
				this.state = "写EXCEL文件出错，出错信息：" + e.getMessage();
				return false;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}

}
