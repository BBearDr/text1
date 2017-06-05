
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
 * ����һ��Excel
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
 * @date:2016��8��15��
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
		/********************* ��ʽ���� ******************************************/
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
		// ��ʽ2���Ĳ��� �ޱ߿�
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
		// ����һ���¹�����
		Sheet sheet = wb.createSheet();
		wb.setSheetName(0, "������");
		sheet.setDefaultColumnWidth((short) 14);
		PrintSetup myPrintSetup = sheet.getPrintSetup();
		myPrintSetup.setPaperSize((short) 9); // ���ó�A4ֽ
		myPrintSetup.setLandscape(true); // ֽ�ź��
		// ������������ʼΪ���������д��ʽ
		Row row;
		Cell cell;
		int i = 0;// ��
		int j = 0;// ��
		// List<Object> list = new ArrayList<Object>();
		// list.add(1);
		// list.add("12");
		// list.add(new Date());
		// list.add("11111111111111111111111");
		// list.add(6.34);
		// list.add(4.444444);
		String[] value = { "12", "23333333333333333333", "33", "44", "22",
				"11111111111111111111111" };
		// д���Ⲣ�Һϲ���Ԫ��
		row = sheet.createRow((short) i);// ��������
		cell = row.createCell((short) j);// ������cell
		cell.setCellStyle(title);
		// cell.setCellValue(this.Subject);//���������Ľ���ַ���
		cell.setCellValue("����");
		sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 5));
		// ��ѯ��������
		// i++;
		// row = sheet.createRow((short)i);//��������
		// cell = row.createCell((short)j);//������cell
		// cell.setCellStyle(normal);
		// cell.setCellValue(this.WherePart);//���������Ľ���ַ���
		// д����
		i = i + 1;
		row = sheet.createRow(i);// ��������

		try {
			for (int tmpi = 1; tmpi <= 6; tmpi++) {
				cell = row.createCell(j); // ������cell
				cell.setCellStyle(normal);
				// cell.setCellValue(StrTool.getStr(this.Title, tmpi, "@"));
				// //���������Ľ���ַ���
				cell.setCellValue("����");
				cell.setCellType(Cell.CELL_TYPE_STRING);
				j++;
			}
		} catch (Exception e) {
			this.state = "д��ͷ����������ȷ������Ӧ���� " + Row + " ��";
			return false;
		}
		// д����
		i = i + 1;
		int k = 1;
		boolean isSheet = true;
		try {
			for (int maintmpi = 1; maintmpi <= 100000; maintmpi++) {
				row = sheet.createRow(i);
				j = 0;
				for (int tmpj = 1; tmpj <= 6; tmpj++) {
					cell = row.createCell(j); // ������cell
					cell.setCellStyle(normal_left);
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					// //����cell���������ĸ�λ�ֽڽض�
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(value[tmpj - 1]); // ���������Ľ���ַ���
					// cell.setCellValue(list.get(tmpj-1));
					j++;
				}
				i++;
				// ������10000ʱǿ��ˢ������̣�����ɹ�������������������
				if (maintmpi % 10000 == 0) {
					((SXSSFSheet) sheet).flushRows(10000);
				}
			}

			// System.out.println(sheet.getColumnWidth(1));
			// ���ݵ�Ԫ�������Զ��������,ֻ�г���Ĭ�Ͽ�ȲŻ��Զ�������ȣ�Ҳ�����ȫ��
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
				// log.info("===="+this.FileName+"=====Excel�ļ�����Ϊ��"+"�ļ��޷�����д��ȥ�ˣ�");
				System.out.println("�ļ���������");
				this.state = "дEXCEL�ļ�����������Ϣ��" + e.getMessage();
				return false;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}

}
