package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider 
{
	XSSFWorkbook wb;
	
	public  ExcelDataProvider()
	{
		File src = new File ("./ApplicationTestData/AppData.xlsx");
		
		try
		{
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		}
		catch (Exception e)
		{
			System.out.println("The Exception message is" + e.getMessage());
		}
	}
	
	public String getData(String SheetName, int row,int col)
	{
		String Data = wb.getSheet(SheetName).getRow(row).getCell(col).getStringCellValue();
		return Data;
	}
	
	public Date getDate_Data(String SheetName, int row,int col)
	{
		Date datevalue = wb.getSheet(SheetName).getRow(row).getCell(col).getDateCellValue();
		return datevalue;
	}
	

}
