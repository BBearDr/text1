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
	    //设置导出进行强制刷新条数
	    private int FlushNum = 10000;
	    //自动调整的宽度
	    private int colNormalWidth = 7;
	    //设定一个sheet内的最大条数
	    private int sheetNum = 65000;
	
		/**
		 * 新建工作表，设定sheet表名，宽度
		 * @param wb
		 * @param sheetNum 第几个工作表
		 */
		private Sheet SheetStyle(SXSSFWorkbook wb,int sheetNum){
			Sheet sheet = wb.createSheet();
			wb.setSheetName(sheetNum, this.SheetName+sheetNum);
			sheet.setDefaultColumnWidth(14);
			return sheet;
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
		public boolean ExcelTitle(SXSSFWorkbook wb,Sheet sheet,int i,int j){
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
				cell.setCellValue(this.Subject);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, this.Col-1));
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		/**
		 * 新建列名
		 * @param wb
		 * @param sheet
		 * @param i 设定开始行数
		 * @param j 设定开始列数
		 * @return
		 */
		public boolean ExcelCol(SXSSFWorkbook wb,Sheet sheet,int i,int j){
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
				for (int tmpi = 1; tmpi <= Col; tmpi++) {
					Cell cell = row.createCell(j); // 建立新cell
					cell.setCellStyle(normal);
//					cell.setCellValue(StrTool.getStr(this.Title, tmpi, "@"));
					cell.setCellType(Cell.CELL_TYPE_STRING);
					j++;
				}
			} catch (Exception e) {
				this.state = "写表头出错，请检查正确的列数应该是 " + Col + " 列";
				e.printStackTrace();
				return false;
			}
			return true;
		}
		/**
		 * 写查询内容
		 * @param wb
		 * @param sheet
		 * @param i 设定开始的行数
		 * @param j 设定开始的列数
		 * @param rowNum 设定一个sheet表中导出的行数
		 * @return
		 */
		public boolean ExcelCreate(SXSSFWorkbook wb,Sheet sheet,int i,int j,int rowNum){
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
			
			//样式2    宋体右对齐  写正文时用
	        Font fontright = wb.createFont();
	        fontright.setColor(Font.COLOR_NORMAL);
	        fontright.setFontName("宋体");
	        fontright.setFontHeightInPoints((short)12);
	        CellStyle normal_right= wb.createCellStyle();
	        normal_right.setFont(fontright);
	        normal_right.setAlignment(CellStyle.ALIGN_LEFT);//水平左齐
	        normal_right.setVerticalAlignment(CellStyle.VERTICAL_TOP);//垂直居中
	        normal_right.setBorderBottom(CellStyle.BORDER_THIN);//下边框
	        normal_right.setBorderLeft(CellStyle.BORDER_THIN);//左边框
	        normal_right.setBorderRight(CellStyle.BORDER_THIN);//右边框
	        normal_right.setBorderTop(CellStyle.BORDER_THIN);//上边框
	        
			try {
				for (int maintmpi = 1; maintmpi <= rowNum; maintmpi++) {
					Row row = sheet.createRow(i);
					j = 0;
					for (int tmpj = 1; tmpj <= Col; tmpj++) {
						String value = "";//tSSRS.GetText(maintmpi, tmpj);
						Cell cell = row.createCell(j); // 建立新cell
						cell.setCellStyle(normal_left);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						boolean NumColbool = false;
						//对数字格式的数据进行导出调整
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
	                             cell.setCellValue(dValue.doubleValue()); //设置中西文结合字符串
	                         } catch (Exception e) {
	                         	e.printStackTrace();
	                             this.state = value + " 转成double出错！";
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
//	    	                           cell.setCellValue(Stringtools.stringtodouble(value)); //设置中西文结合字符串
	    	                       } catch (Exception e) {
	    	                           this.state = value + " 转成double出错！";
	    	                           return false;
	    	                       }
	    	                   } else {
	    	                       cell.setCellType(Cell.CELL_TYPE_STRING);
	    	                       cell.setCellValue(value); //设置中西文结合字符串
	    	                   }
	                    }
						j++;
					}
					i++;
					// 当超过10000时强制刷新入磁盘，定义成公共变量！！！！！！
					if (maintmpi % FlushNum == 0) {
						((SXSSFSheet) sheet).flushRows(FlushNum);
					}
				}
				//根据数据判断是否增加自动调整宽度
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
		 * 导出excel
		 * @return
		 */
		public boolean PubCreateExcel(){
			// 声明变量，开始为标题和内容写样式
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet sheet;
			if(Row <= sheetNum){
				int i = 0;// 行
				int j = 0;// 列
				int num = (Row > sheetNum)?sheetNum : Row;
				sheet = SheetStyle(wb, 0);//当只有一个表时设定为0
				ExcelTitle(wb, sheet, i, j);//写标题
				i++;
				ExcelCol(wb, sheet, i, j);//写列名
				i++;
				ExcelCreate(wb, sheet, i, j, num);//内容
			}else{
				for(int k =0 ; k<=(Row/sheetNum) ; k++){
					int i = 0;// 行
					int j = 0;// 列
					int num = (Row - sheetNum*k > sheetNum)?sheetNum : Row-sheetNum*k;
					sheet = SheetStyle(wb, k);
					ExcelTitle(wb, sheet, i, j);//写标题
					i++;
					ExcelCol(wb, sheet, i, j);//写列名
					i++;
					ExcelCreate(wb, sheet, i, j, num);//内容
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
//				log.info("===="+this.FileName+"=====Excel文件行数为："+"文件无法继续写下去了！");
				System.out.println("文件导出出错");
				this.state = "写EXCEL文件出错，出错信息：" + e.getMessage();
				return false;
			}
			return true;
		}
	
}
