package pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import factory.BrowserFactory;
import factory.DataProviderFactory;

public class SelectDateMakeMytrip 
{
	
	@Test
    public void selectdate()
    {
		String selectdate = "10/15/2016";
		Date d = new Date(selectdate);
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM/dd/yyyy");
		String date = sdf.format(d);
		System.out.println(date);
		String[] split = date.split("/");
		System.out.println(split[0]+ " "+ split[1]+ " " +split[2]);
		String month = split[0].toUpperCase() + " " +split[2];
		System.out.println("month----->" + month);
		
		
		
		WebDriver driver = BrowserFactory.getBrowser("Chrome");
		driver.get(DataProviderFactory.getConfig().getApplicationURL());
		
		driver.findElement(By.id("hp-widget__depart")).click();
    }

}
