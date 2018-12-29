package testcases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import factory.BrowserFactory;
import factory.DataProviderFactory;
import pages.ListPage;
import pages.MakeMyTrip;

public class SearchFareAndPrint 
{
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setUP()
	{
		//report = new ExtentReports("./Reports/LoginPageReport.html",true);
		//logger = report.startTest("Verify Login Page","This Page will verify the Profile img after login");
			
		driver = BrowserFactory.getBrowser("chrome");		
		driver.get(DataProviderFactory.getConfig().getApplicationURL());		
		
	}
	
	@Test
	public void EnterDetails()
	{
		MakeMyTrip mtp = PageFactory.initElements(driver, MakeMyTrip.class);
		String Fromcity = DataProviderFactory.getExcel().getData("InputParameter", 1, 0);
		String Tocity = DataProviderFactory.getExcel().getData("InputParameter", 1, 1);
		String DepartureDate = DataProviderFactory.getExcel().getData("InputParameter", 1, 2);
		String ReturnDate = DataProviderFactory.getExcel().getData("InputParameter", 1, 3);
		
		mtp.selectFromCity(Fromcity);
		mtp.selectToCity(Tocity);
		mtp.selectDepartureDate(DepartureDate);
		mtp.selectReturnDate(ReturnDate);
		mtp.search();
		
		
		ListPage FaresList = PageFactory.initElements(driver, ListPage.class);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		//Departure List
		map = FaresList.Fetch_Depature_FlightFares();
		for (Map.Entry<String, List<String>> entry : map.entrySet()) 
        {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            System.out.println("Key = " + key + " , " + "Values = " + values + "n");
            
        }
		
		//sort by lowest Fare and get that value
		
		//Return List
		
		
	}
	
}
