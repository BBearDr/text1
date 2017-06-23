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
 * ����Excel�����ع�
 * <p>Title:PubExcleUtil</p>
 * <p>Description:</p>
 * <p>Company:</p>
 * @author:chenjx
 * @date:2016��8��16��
 */
public class PubExcleUtil {
	private OutputStream outx = null;
	/**
	 * �½�������
	 * @param wb
	 * @param sheetNum
	 */
	public void sheetStyle(SXSSFWorkbook wb,int sheetNum){
		Sheet sheet = wb.createSheet();
		wb.setSheetName(sheetNum, "������"+sheetNum);
		sheet.setDefaultColumnWidth((short) 14);
	}
	/**
	 * �½����⣬��ʽ
	 * @param wb 
	 * @param sheet
	 * @param i �趨��ʼ����
	 * @param j �趨��ʼ����
	 * @param title1 ����
	 * @param strCol �������� 
	 * @return
	 */
	public boolean ExcelTitle(SXSSFWorkbook wb,Sheet sheet,int i,int j,String title1,String[] strCol){
		// ��ʽ1 ������ д������
		Font font1 = wb.createFont();
		font1.setColor(Font.COLOR_NORMAL);
		font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font1.setFontName("����");
		font1.setFontHeightInPoints((short) 18);
		CellStyle title = wb.createCellStyle();
		title.setFont(font1);
		title.setAlignment(CellStyle.ALIGN_CENTER);// ˮƽ����
		title.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// ��ֱ����
		title.setBorderBottom(CellStyle.BORDER_THIN);// �±߿�
		title.setBorderLeft(CellStyle.BORDER_THIN);// ��߿�
		title.setBorderRight(CellStyle.BORDER_THIN);// �ұ߿�
		title.setBorderTop(CellStyle.BORDER_THIN);// �ϱ߿�
		try {
			// д���Ⲣ�Һϲ���Ԫ��
			Row row = sheet.createRow(i);// ��������
			Cell cell = row.createCell(j);// ������cell
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
	 * �½�����
	 * @param wb
	 * @param sheet
	 * @param i �趨��ʼ����
	 * @param j �趨��ʼ����
	 * @param strCol �еļ���
	 * @return
	 */
	public boolean ExcelCol(SXSSFWorkbook wb,Sheet sheet,int i,int j,String[] strCol){
		// ��ʽ2 ���� д����ʱ��
		Font font2 = wb.createFont();
		font2.setColor(Font.COLOR_NORMAL);
		font2.setFontName("����");
		font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font2.setFontHeightInPoints((short) 10);
		CellStyle normal = wb.createCellStyle();
		normal.setFont(font2);
		normal.setAlignment(CellStyle.ALIGN_CENTER);// ˮƽ����
		normal.setVerticalAlignment(CellStyle.VERTICAL_TOP);// ��ֱ����
		normal.setBorderBottom(CellStyle.BORDER_THIN);// �±߿�
		normal.setBorderLeft(CellStyle.BORDER_THIN);// ��߿�
		normal.setBorderRight(CellStyle.BORDER_THIN);// �ұ߿�
		normal.setBorderTop(CellStyle.BORDER_THIN);// �ϱ߿�
		Row row = sheet.createRow(i);// ��������
		try {
			for (int tmpi = 1; tmpi <= strCol.length; tmpi++) {
				Cell cell = row.createCell(j); // ������cell
				cell.setCellStyle(normal);
				cell.setCellValue(strCol[tmpi-1]);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				j++;
			}
		} catch (Exception e) {
//			this.state = "д��ͷ����������ȷ������Ӧ���� " + col + " ��";
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * �½�����
	 * @param wb
	 * @param sheet
	 * @param colNum 
	 * @param i
	 * @param j
	 * @param str
	 * @return
	 */
	public boolean ExcelCreate(SXSSFWorkbook wb,Sheet sheet,int colNum,int i,int j,String[] str){
		// ��ʽ2 ��������� д����ʱ��
		Font fontleft = wb.createFont();
		fontleft.setColor(Font.COLOR_NORMAL);
		fontleft.setFontName("����");
		fontleft.setFontHeightInPoints((short) 10);
		CellStyle normal_left = wb.createCellStyle();
		normal_left.setFont(fontleft);
		normal_left.setAlignment(CellStyle.ALIGN_LEFT);// ˮƽ����
		normal_left.setVerticalAlignment(CellStyle.VERTICAL_TOP);// ��ֱ����
		normal_left.setBorderBottom(CellStyle.BORDER_THIN);// �±߿�
		normal_left.setBorderLeft(CellStyle.BORDER_THIN);// ��߿�
		normal_left.setBorderRight(CellStyle.BORDER_THIN);// �ұ߿�
		normal_left.setBorderTop(CellStyle.BORDER_THIN);// �ϱ߿�
		try {
			for (int maintmpi = 1; maintmpi <= colNum; maintmpi++) {
				Row row = sheet.createRow(i);
				j = 0;
				for (int tmpj = 1; tmpj <= str.length; tmpj++) {
					Cell cell = row.createCell(j); // ������cell
					cell.setCellStyle(normal_left);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(str[tmpj - 1]); // ���������Ľ���ַ���
					j++;
				}
				i++;
				// ������10000ʱǿ��ˢ������̣�����ɹ�������������������
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
		*//********************* ��ʽ���� ******************************************//*
		// ��ʽ2 ��������� д����ʱ��
		Font fontleft = wb.createFont();
		fontleft.setColor(Font.COLOR_NORMAL);
		fontleft.setFontName("����");
		fontleft.setFontHeightInPoints((short) 10);
		CellStyle normal_left = wb.createCellStyle();
		normal_left.setFont(fontleft);
		normal_left.setAlignment(CellStyle.ALIGN_LEFT);// ˮƽ����
		normal_left.setVerticalAlignment(CellStyle.VERTICAL_TOP);// ��ֱ����
		normal_left.setBorderBottom(CellStyle.BORDER_THIN);// �±߿�
		normal_left.setBorderLeft(CellStyle.BORDER_THIN);// ��߿�
		normal_left.setBorderRight(CellStyle.BORDER_THIN);// �ұ߿�
		normal_left.setBorderTop(CellStyle.BORDER_THIN);// �ϱ߿�

		// ��ʽ2 �����Ҷ��� д����ʱ��
		Font fontright = wb.createFont();
		fontright.setColor(Font.COLOR_NORMAL);
		fontright.setFontName("����");
		fontright.setFontHeightInPoints((short) 12);
		CellStyle normal_right = wb.createCellStyle();
		normal_right.setFont(fontright);
		normal_right.setAlignment(CellStyle.ALIGN_LEFT);// ˮƽ����
		normal_right.setVerticalAlignment(CellStyle.VERTICAL_TOP);// ��ֱ����
		normal_right.setBorderBottom(CellStyle.BORDER_THIN);// �±߿�
		normal_right.setBorderLeft(CellStyle.BORDER_THIN);// ��߿�
		normal_right.setBorderRight(CellStyle.BORDER_THIN);// �ұ߿�
		normal_right.setBorderTop(CellStyle.BORDER_THIN);// �ϱ߿�

		// ��ʽ3 ���� д������ �б߿�
		Font font3 = wb.createFont();
		font3.setColor(Font.COLOR_NORMAL);
		font3.setFontName("����");
		font3.setFontHeightInPoints((short) 12);
		CellStyle border = wb.createCellStyle();
		border.setFont(font3);
		border.setAlignment(CellStyle.ALIGN_LEFT);// ˮƽ����
		border.setVerticalAlignment(CellStyle.VERTICAL_TOP);// ��ֱ����
		border.setBorderBottom(CellStyle.BORDER_THIN);// �±߿�
		border.setBorderLeft(CellStyle.BORDER_THIN);// ��߿�
		border.setBorderRight(CellStyle.BORDER_THIN);// �ұ߿�
		border.setBorderTop(CellStyle.BORDER_THIN);// �ϱ߿�
	}*/

	public boolean excelUtil(String[] strCol,String[] str,String title1,int rowNum,String fileType){
		// ������������ʼΪ���������д��ʽ
		SXSSFWorkbook wb = new SXSSFWorkbook();
		Sheet sheet;
		if(rowNum<=65000){
			System.out.println(1);
			int i = 0;// ��
			int j = 0;// ��
			int num = (rowNum>65000)?65000:rowNum;
			sheet = wb.createSheet();
			ExcelTitle(wb, sheet, i, j, title1, strCol);//д����
			i++;
			ExcelCol(wb, sheet, i, j, strCol);//д����
			i++;
			ExcelCreate(wb, sheet, num, i, j, str);//����
		}else{
			System.out.println(2);
			for(int k =0;k<=(rowNum/65000);k++){
				int i = 0;// ��
				int j = 0;// ��
				int num = (rowNum-65000*k>65000)?65000:rowNum-65000*k;
				sheet = wb.createSheet();
				ExcelTitle(wb, sheet, i, j, title1, strCol);//д����
				i++;
				ExcelCol(wb, sheet, i, j, strCol);//д����
				i++;
				ExcelCreate(wb, sheet, num, i, j, str);//����
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
			// log.info("===="+this.FileName+"=====Excel�ļ�����Ϊ��"+"�ļ��޷�����д��ȥ�ˣ�");
			System.out.println("�ļ���������");
//			this.state = "дEXCEL�ļ�����������Ϣ��" + e.getMessage();
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		String[] strCol = {"����1","����2","����3","����4","����5","����6"};
		String[] str = { "12", "23333333333333333333", "33", "44", "22","11111111111111111111111" };
		String title1 = "������";
		int rowNum = 120000;
		String fileType = "xls";
		PubExcleUtil excel = new PubExcleUtil();
		boolean reasult = excel.excelUtil(strCol, str, title1, rowNum, fileType);
		System.out.println(reasult);
	}
}
