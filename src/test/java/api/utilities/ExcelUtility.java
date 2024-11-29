package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fileInputStream;
	public FileOutputStream fileOutputStream;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path;
	
	public ExcelUtility(String path)
	{
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException
	{
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		
		int rowCount = sheet.getLastRowNum();
		
		workbook.close();
		fileInputStream.close();
		
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rowNumber) throws IOException 
	{
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		
		int cellCount = row.getLastCellNum();
		
		workbook.close();
		fileInputStream.close();
		
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rowNumber, int colNumber) throws IOException 
	{
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);
		cell = row.getCell(colNumber);

		DataFormatter formatter = new DataFormatter();
		String data;
		
		try {
			data = formatter.formatCellValue(cell); 
		} 
		catch (Exception e) {
			data = "";
		}
		
		workbook.close();
		fileInputStream.close();
		
		return data;
	}
}