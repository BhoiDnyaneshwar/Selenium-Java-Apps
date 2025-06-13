package ExcelUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtility {
	
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;   //sheet is a collection of rows
                                     //rows is collection of cell
	
	public static void getSheetName(String filePath, String sheetName) {
		try {
			FileInputStream fis=new FileInputStream(filePath);
			workbook=new XSSFWorkbook(fis);
			int numberOfSheets=workbook.getNumberOfSheets();
			for(int i=0;i<numberOfSheets;i++) {
				if(workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
					sheet=workbook.getSheetAt(i);
				}
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	
	public static int getExpectedColumnIndex(String columnName) {
		Map<String, Integer> storedHeaderInfo=new HashMap<String, Integer>();
		Iterator<Cell> firstRowCells=sheet.getRow(0).cellIterator();
		while(firstRowCells.hasNext()) {
			Cell cell=firstRowCells.next();
			storedHeaderInfo.put(cell.getStringCellValue(), cell.getColumnIndex());
		}
		return storedHeaderInfo.get(columnName);
	}
	
	public static int getTargetedRowIndex(String TargatedText) {
		int rowNum=-1;
		//int targatedText=Integer.parseInt(TargatedText);
		for(int i=1;i<=sheet.getLastRowNum();i++) {
			Row rows=sheet.getRow(i);	
			//Iterator<Row> rows=sheet.rowIterator();
			Iterator<Cell> cells=rows.cellIterator();
				while(cells.hasNext()) {
					Cell cell=cells.next();
					if(cell.getCellType()==CellType.STRING&&cell.getStringCellValue().equals(TargatedText)) {
						rowNum=i;						
					}else if(cell.getCellType()==CellType.NUMERIC&&NumberToTextConverter.toText(cell.getNumericCellValue())==TargatedText) {
						rowNum=i;						
					}					
				}			
			}
		return rowNum;
	}

	public void setCellUpdatedValue(String filePath, String sheetName, String columnName, String TargatedText, String updateValue) {
		getSheetName(filePath, sheetName);
		Cell cell=sheet.getRow(getTargetedRowIndex(TargatedText)).getCell(getExpectedColumnIndex(columnName));
		if(cell==null) {
			cell=sheet.getRow(getTargetedRowIndex(TargatedText)).createCell(getExpectedColumnIndex(columnName));
		}
		cell.setCellValue(updateValue);
		
		try {
			FileOutputStream fos=new FileOutputStream(filePath);
			workbook.write(fos);
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getExpectedCellValue(String filePath, String sheetName, String columnName, String TargatedText) {
		getSheetName(filePath, sheetName);
		Cell cell=sheet.getRow(getTargetedRowIndex(TargatedText)).getCell(getExpectedColumnIndex(columnName));
		if(cell.getCellType()==CellType.STRING) {
			return cell.getStringCellValue();						
		}else if(cell.getCellType()==CellType.NUMERIC) {
			return NumberToTextConverter.toText(cell.getNumericCellValue());						
		}else {
			return cell.toString();
		}
	}
	
}
