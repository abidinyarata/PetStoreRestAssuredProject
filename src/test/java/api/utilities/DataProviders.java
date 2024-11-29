package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException
	{
		String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
		ExcelUtility excelUtility = new ExcelUtility(path);
	
		int rowNumber = excelUtility.getRowCount("Sheet1");	
		int colNumber = excelUtility.getCellCount("Sheet1", 1);
		
		String [][] apidata = new String[rowNumber][colNumber];
		
		for(int i = 1; i <= rowNumber; ++i) 		
			for(int k = 0; k < colNumber; ++k) 
				apidata[i - 1][k] = excelUtility.getCellData("Sheet1", i, k);  

		return apidata;
	}
	
	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException
	{
		String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
		ExcelUtility excelUtility = new ExcelUtility(path);
	
		int rowNumber = excelUtility.getRowCount("Sheet1");	
			
		String [] apidata = new String[rowNumber];
		
		for(int i = 1; i <= rowNumber; ++i)	
			apidata[i - 1] = excelUtility.getCellData("Sheet1", i, 1);  
			
		return apidata;
	}
}