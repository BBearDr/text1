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
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;


public class ReadExcel {
	   // �����ļ�������  
    private BufferedReader reader = null;  
    // �ļ�����  
    private String filetype;  
    // �ļ�������������  
    private InputStream is = null;  
    // ��ǰ��Sheet  
    private int currSheet;  
    // ��ǰλ��  
    private int currPosition;  
    // Sheet����  
    private int numOfSheets;  
    // HSSFWorkbook  
    HSSFWorkbook workbook = null;  
    // ����Cell֮���Կո�ָ�  
    private static String EXCEL_LINE_DELIMITER = ",";  
  
    // ���캯������һ��ExcelReader  
    public  ReadExcel(String inputfile) throws IOException, Exception {  
        // �жϲ����Ƿ�Ϊ�ջ�û������  
        if (inputfile == null || inputfile.trim().equals("")) {  
            throw new IOException("NO input file!!!");  
        }  
        // ȡ���ļ����ĺ�׺����ֵ��filetype  
        filetype = inputfile.substring(inputfile.lastIndexOf(".") + 1);  
          
        // ���ÿ�ʼ��Ϊ0  
        currPosition = 0;  
        // ���õ�ǰλ��Ϊ0  
        currSheet = 0;  
        // �����ļ�������  
        is = new FileInputStream(inputfile);  
          
        // �ж��ļ���ʽ  
        if (filetype.equalsIgnoreCase("xls")) {  
            // �����Excel�ļ��򴴽�HSSFWorkbook��ȡ  
            workbook = new HSSFWorkbook(is); 
            // ����Sheet��  
            numOfSheets = workbook.getNumberOfSheets();  
        } else {  
            throw new Exception("File Type incorrrect!");  
        }
        
    }  
    /**
     * ��ȡexcel
     * @return
     */
    public List<List<String>> readExcel(){
    	List<List<String>> row = new ArrayList<List<String>>();
    	try {
			for(currSheet=0;currSheet< workbook.getNumberOfSheets(); currSheet++){
				if(filetype.equalsIgnoreCase("xls")){
					Sheet sheet = (Sheet)workbook.getSheetAt(currSheet);//���ָ���ı�
					if(sheet == null){
						continue;
					}
					//ѭ��row
					for(int rowNum=1;rowNum<sheet.getLastRowNum();rowNum++){
						Row hssfRow = sheet.getRow(rowNum);//����ж���
						if(hssfRow == null){
							continue;
						}
						//ѭ��cell
						List<String> cell = new ArrayList<String>();
						//��ñ����е�Ԫ��ĸ���
						for(short cellNum=0; cellNum<hssfRow.getLastCellNum();cellNum++){
							Cell hssfCell = hssfRow.getCell(cellNum);
							
							if(hssfCell == null){
								continue;
							}
							cell.add(getValue(hssfCell));
						}
						System.out.println(cell);
						row.add(cell);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return row;
    }
    // ���ڶ�ȡ�ļ���һ��  
    public String readLine() throws IOException {  
        // excel�ļ�ͨ��poi��ȡ�ļ�  
        if (filetype.equalsIgnoreCase("xls")) {  
            // ����currSheetֵ��õ�ǰ��sheet  
            HSSFSheet sheet = workbook.getSheetAt(currSheet);  
              
            // �жϵ�ǰ���Ƿ񵽵�ǰSheet�Ľ�β  
            if (currPosition > sheet.getLastRowNum()) {  
                // ��ǰ��λ������  
                currPosition = 0;  
                // �ж��Ƿ���Sheet  
                while (currSheet < numOfSheets - 1) {  
                    // �õ���һ��Sheet  
                    currSheet += 1;  
                    sheet = workbook.getSheetAt(currSheet);  
                      
                    // ��ǰ�����Ƿ��Ѿ������ļ�ĩβ  
                    if (currPosition > sheet.getLastRowNum()) {  
                        // ��ǰSheetָ����һ��Sheet  
                        currSheet++;  
                        continue;  
                    } else {  
                        // ��ȡ��ǰ����  
                        int row = currPosition;  
                        currPosition++;  
                        // ��ȡ��ǰ������  
                        return getLine(sheet, row);  
                    }  
                }  
                return null;  
            }  
            // ��ȡ��ǰ����  
            int row = currPosition;  
            currPosition++;  
            // ��ȡ��ǰ������  
            return getLine(sheet, row);  
        }  
        return null;  
    }  
    /**
     * ��ȡһ����Ԫ���ֵ
     * @param cell
     * @return
     */
    private String getValue(Cell cell){
    	// �����ַ���������  
        StringBuffer buffer = new StringBuffer();  
    	String cellvalue = null;  
        if (cell != null) {  
        	//�ж��Ƿ��Ǻϲ���Ԫ��
//			boolean is = isMergedRegion(sheet,rowNum,1);
//			if(is){
//				//��ȡ�ϲ���Ԫ��ֵ
//				cellvalue = getMergedRegionValue(sheet, rowNum,1);
//			}
            // �жϵ�ǰCell��Type  
            switch (cell.getCellType()) {  
              
                // Cell��TypeΪNUMERIC  
                case HSSFCell.CELL_TYPE_NUMERIC: {  
                    // �жϵ�ǰ��cell�Ƿ�ΪDate  
                    if (HSSFDateUtil.isCellDateFormatted((HSSFCell) cell)) {  
                        // �����Date������ȡ�ø�Cell��Dateֵ  
                        Date date = cell.getDateCellValue();  
                        // ��Dateת���ɱ��ظ�ʽ���ַ���  
                        cellvalue = cell.getDateCellValue().toLocaleString();  
                    } else { // ����Ǵ�����  
                        // ȡ�õ�ǰCell����ֵ,ǿ��ת��Ϊint  
                        Integer num = new Integer(  
                                (int) cell.getNumericCellValue());  
                        cellvalue = String.valueOf(num);  
                    }  
                    break;  
                }  
                // Cell��TypeΪSTRING  
                case HSSFCell.CELL_TYPE_STRING:  
                    // ȡ�õ�ǰ��Cell�ַ���  
                    cellvalue = cell.getStringCellValue().replaceAll("'", "''");  
                    break;  
                // Ĭ�ϵ�Cellֵ  
                default:  
                    cellvalue = " ";  
                }  
        } else {  
            cellvalue = "";  
        }  
        // ��ÿ���ֶ�֮�����ָ��  
        buffer.append(cellvalue);  
        // ȥ�����һ���ָ���  
//        buffer.substring(0, buffer.length() - 1);  
        
        return buffer.toString();
    }
  
    // ����Sheet��һ������  
    private String getLine(HSSFSheet sheet, int row) {  
        // ��������ȡ��Sheet��һ��  
        HSSFRow rowline = sheet.getRow(row);  
        // �����ַ���������  
        StringBuffer buffer = new StringBuffer();  
        // ��ȡ��ǰ�е�����  
        int filledColumns = rowline.getLastCellNum();  
        HSSFCell cell = null;  
        // ѭ������������  
        for (int i = 0; i < filledColumns; i++) {  
            // ȡ�õ�ǰCell  
            cell = rowline.getCell((short) i);  
            String cellvalue = null;  
            if (cell != null) {  
                // �жϵ�ǰCell��Type  
                switch (cell.getCellType()) {  
                  
                    // Cell��TypeΪNUMERIC  
                    case HSSFCell.CELL_TYPE_NUMERIC: {  
                        // �жϵ�ǰ��cell�Ƿ�ΪDate  
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {  
                            // �����Date������ȡ�ø�Cell��Dateֵ  
                            Date date = cell.getDateCellValue();  
                            // ��Dateת���ɱ��ظ�ʽ���ַ���  
                            cellvalue = cell.getDateCellValue().toLocaleString();  
                        } else { // ����Ǵ�����  
                            // ȡ�õ�ǰCell����ֵ,ǿ��ת��Ϊint  
                            Integer num = new Integer(  
                                    (int) cell.getNumericCellValue());  
                            cellvalue = String.valueOf(num);  
                        }  
                        break;  
                    }  
                    // Cell��TypeΪSTRING  
                    case HSSFCell.CELL_TYPE_STRING:  
                        // ȡ�õ�ǰ��Cell�ַ���  
                        cellvalue = cell.getStringCellValue().replaceAll("'", "''");  
                        break;  
                    // Ĭ�ϵ�Cellֵ  
                    default:  
                        cellvalue = " ";  
                    }  
            } else {  
                cellvalue = "";  
            }  
            // ��ÿ���ֶ�֮�����ָ��  
            buffer.append(cellvalue).append(EXCEL_LINE_DELIMITER);  
            // ȥ�����һ���ָ���  
            buffer.substring(0, buffer.length() - 1);  
        }  
        return buffer.toString();  
    }  

    private boolean isMergedRegion(Sheet sheet ,int row, int col){
    	int sheetMergeCount = sheet.getNumMergedRegions();
    	for(int i=0; i<sheetMergeCount; i++){
    		CellRangeAddress range = sheet.getMergedRegion(i);
    		int firstCol = range.getFirstColumn();
    		int lastCol = range.getLastColumn();
    		int firstRow = range.getFirstRow();
    		int lastRow = range.getLastRow();
    		if(row >= firstRow && row <= lastRow){
    			if(col >= firstCol && col <= lastCol){
    				return true;
    			}
    		}
//    		Region region = sheet.getMergedRegionAt(i);
//    		HSSFCell hCell = sheet.getRow(region.getRowFrom()).getCell(region.getColumnFrom());
//    		System.out.println("��"+(i+1)+"���ϲ���Ԫ��ֵΪ��"+hCell.getStringCellValue().toString());
//    		if(hCell != null){
//    			return true;
//    		}
    	}
    	return false;
    	
    }
    private String getMergedRegionValue(Sheet sheet ,int row, int col){
    	int sheetMergeCount = sheet.getNumMergedRegions();
    	System.out.println("�ϲ���������"+sheetMergeCount);
    	for(int i=0; i<sheetMergeCount; i++){
    		CellRangeAddress range = sheet.getMergedRegion(i);
    		int firstCol = range.getFirstColumn();
    		int lastCol = range.getLastColumn();
    		int firstRow = range.getFirstRow();
    		int lastRow = range.getLastRow();
    		if(row >= firstRow && row <= lastRow){
    			if(col >= firstCol && col <= lastCol){
    				Row rRow = sheet.getRow(firstRow);
    				Cell cCell = rRow.getCell(firstCol);
    				return getValue(cCell);
    			}
    		}
//    		Region region = sheet.getMergedRegionAt(i);
//    		HSSFCell hCell = sheet.getRow(region.getRowFrom()).getCell(region.getColumnFrom());
//    		System.out.println("��"+(i+1)+"-----���ϲ���Ԫ��ֵΪ��"+hCell.getStringCellValue().toString());
//    		if(hCell != null){
//    			return getValue(sheet,row,hCell);
//    		} 
    	}
    	return null;
    	
    }
    // �ر���Դ  
    public void closeResource() {  
        try {  
            if (is != null)   
                is.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                if (reader != null)   
                    reader.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }     
        }     
    }  
  
    // ����case  
    public static void main(String[] args) {  
        try {  
            ReadExcel er = new ReadExcel("d://�й�����ϵ.xls");  
            List<List<String>> row = er.readExcel();  
            //�õ�excel���ݺ�д�������ݵĴ���Ϳ����ˣ��磺�������ݿ�ȵ�  
           
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
}
