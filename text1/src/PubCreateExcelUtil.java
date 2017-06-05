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

public class PubCreateExcelUtil {
	 private String state="";
//	 private SSRS tSSRS;
	    private String Title="";
	    private String Subject="";
	    private String SheetName="";
	    private int Row=0;
	    private int Col=0;
	    private String FileName="";
	    private String WherePart="";
	    private String NumCol = "";
	    private OutputStream outx=null;
	    //���õ�������ǿ��ˢ������
	    private int FlushNum = 10000;
	    //�Զ������Ŀ��
	    private int colNormalWidth = 7;
	    //�趨һ��sheet�ڵ��������
	    private int sheetNum = 65000;
	
		/**
		 * �½��������趨sheet���������
		 * @param wb
		 * @param sheetNum �ڼ���������
		 */
		private Sheet SheetStyle(SXSSFWorkbook wb,int sheetNum){
			Sheet sheet = wb.createSheet();
			wb.setSheetName(sheetNum, this.SheetName+sheetNum);
			sheet.setDefaultColumnWidth(14);
			return sheet;
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
		public boolean ExcelTitle(SXSSFWorkbook wb,Sheet sheet,int i,int j){
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
				cell.setCellValue(this.Subject);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, this.Col-1));
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
		 * @return
		 */
		public boolean ExcelCol(SXSSFWorkbook wb,Sheet sheet,int i,int j){
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
				for (int tmpi = 1; tmpi <= Col; tmpi++) {
					Cell cell = row.createCell(j); // ������cell
					cell.setCellStyle(normal);
//					cell.setCellValue(StrTool.getStr(this.Title, tmpi, "@"));
					cell.setCellType(Cell.CELL_TYPE_STRING);
					j++;
				}
			} catch (Exception e) {
				this.state = "д��ͷ����������ȷ������Ӧ���� " + Col + " ��";
				e.printStackTrace();
				return false;
			}
			return true;
		}
		/**
		 * д��ѯ����
		 * @param wb
		 * @param sheet
		 * @param i �趨��ʼ������
		 * @param j �趨��ʼ������
		 * @param rowNum �趨һ��sheet���е���������
		 * @return
		 */
		public boolean ExcelCreate(SXSSFWorkbook wb,Sheet sheet,int i,int j,int rowNum){
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
			
			//��ʽ2    �����Ҷ���  д����ʱ��
	        Font fontright = wb.createFont();
	        fontright.setColor(Font.COLOR_NORMAL);
	        fontright.setFontName("����");
	        fontright.setFontHeightInPoints((short)12);
	        CellStyle normal_right= wb.createCellStyle();
	        normal_right.setFont(fontright);
	        normal_right.setAlignment(CellStyle.ALIGN_LEFT);//ˮƽ����
	        normal_right.setVerticalAlignment(CellStyle.VERTICAL_TOP);//��ֱ����
	        normal_right.setBorderBottom(CellStyle.BORDER_THIN);//�±߿�
	        normal_right.setBorderLeft(CellStyle.BORDER_THIN);//��߿�
	        normal_right.setBorderRight(CellStyle.BORDER_THIN);//�ұ߿�
	        normal_right.setBorderTop(CellStyle.BORDER_THIN);//�ϱ߿�
	        
			try {
				for (int maintmpi = 1; maintmpi <= rowNum; maintmpi++) {
					Row row = sheet.createRow(i);
					j = 0;
					for (int tmpj = 1; tmpj <= Col; tmpj++) {
						String value = "";//tSSRS.GetText(maintmpi, tmpj);
						Cell cell = row.createCell(j); // ������cell
						cell.setCellStyle(normal_left);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						boolean NumColbool = false;
						//�����ָ�ʽ�����ݽ��е�������
	                    if(NumCol!=null&&!"".equals(NumCol)&&!"null".equals(NumCol))
	                    {
	                	   String[] NumColArr = NumCol.split("\\|");
	                       for(int c=0;c<NumColArr.length;c++)
	                       {
	                    	   if(String.valueOf(tmpj).equals(NumColArr[c]))
	                    	   {
	                    		   NumColbool = true;
	                    		   break;
	                    	   }
	                       }
	                    }  
	                    if(NumColbool)
	                    {
	                 	   Double dValue;
	                        try {
	     					dValue = Double.valueOf(value);
	     					try {
	                             cell.setCellType(Cell.CELL_TYPE_NUMERIC);
	                             cell.setCellStyle(normal_right);
	                             cell.setCellValue(dValue.doubleValue()); //���������Ľ���ַ���
	                         } catch (Exception e) {
	                         	e.printStackTrace();
	                             this.state = value + " ת��double����";
	                             return false;
	                         }
	     					
	     				} catch (NumberFormatException e1) {
	     					// TODO Auto-generated catch block
	     					//e1.printStackTrace();
	     				}
	                    }
	                    else
	                    {
	    					if (value.indexOf("double") >= 0) {
	    	                       value = value.substring(6, value.length());
	    	                       try {
	    	                           cell.setCellType(Cell.CELL_TYPE_NUMERIC);
	    	                           cell.setCellStyle(normal_right);
//	    	                           cell.setCellValue(Stringtools.stringtodouble(value)); //���������Ľ���ַ���
	    	                       } catch (Exception e) {
	    	                           this.state = value + " ת��double����";
	    	                           return false;
	    	                       }
	    	                   } else {
	    	                       cell.setCellType(Cell.CELL_TYPE_STRING);
	    	                       cell.setCellValue(value); //���������Ľ���ַ���
	    	                   }
	                    }
						j++;
					}
					i++;
					// ������10000ʱǿ��ˢ������̣�����ɹ�������������������
					if (maintmpi % FlushNum == 0) {
						((SXSSFSheet) sheet).flushRows(FlushNum);
					}
				}
				//���������ж��Ƿ������Զ��������
				for (int tmpj = 1; tmpj <= Col; tmpj++) {
					String value = "";//tSSRS.GetText(1, tmpj);
					int colWidth = value.length();
					if (colWidth > colNormalWidth) {
						sheet.autoSizeColumn(tmpj - 1);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		/**
		 * ����excel
		 * @return
		 */
		public boolean PubCreateExcel(){
			// ������������ʼΪ���������д��ʽ
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet sheet;
			if(Row <= sheetNum){
				int i = 0;// ��
				int j = 0;// ��
				int num = (Row > sheetNum)?sheetNum : Row;
				sheet = SheetStyle(wb, 0);//��ֻ��һ����ʱ�趨Ϊ0
				ExcelTitle(wb, sheet, i, j);//д����
				i++;
				ExcelCol(wb, sheet, i, j);//д����
				i++;
				ExcelCreate(wb, sheet, i, j, num);//����
			}else{
				for(int k =0 ; k<=(Row/sheetNum) ; k++){
					int i = 0;// ��
					int j = 0;// ��
					int num = (Row - sheetNum*k > sheetNum)?sheetNum : Row-sheetNum*k;
					sheet = SheetStyle(wb, k);
					ExcelTitle(wb, sheet, i, j);//д����
					i++;
					ExcelCol(wb, sheet, i, j);//д����
					i++;
					ExcelCreate(wb, sheet, i, j, num);//����
				}
			}
			try {
				if (this.outx != null) {
					wb.write(this.outx);
					outx.close();
				} else if (this.FileName!= null) {
					FileOutputStream fileOut = null;
					try {
						fileOut = new FileOutputStream(this.FileName);
						wb.write(fileOut);
					} finally {
						if (fileOut != null) {
							fileOut.close();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
//				log.info("===="+this.FileName+"=====Excel�ļ�����Ϊ��"+"�ļ��޷�����д��ȥ�ˣ�");
				System.out.println("�ļ���������");
				this.state = "дEXCEL�ļ�����������Ϣ��" + e.getMessage();
				return false;
			}
			return true;
		}
	
}
