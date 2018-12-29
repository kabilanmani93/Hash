package pages;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMyTrip 
{
	static WebDriver driver;
	static WebDriverWait wait;
	
	public MakeMyTrip(WebDriver ldriver)
	{
		this.driver = ldriver;
	}
	
	@FindBy(id="hp-widget__sfrom")	WebElement fromCity;
	@FindBy(id="hp-widget__sTo") WebElement toCity;
	String FromCityList = ".//div[@class='filterOptins']//child :: div[contains(@class,'autocomplete_from')]//following-sibling :: li/div/p/span";
	String ToCityList = ".//div[@class='filterOptins']//child :: div[contains(@class,'autocomplete_to')]//following-sibling :: li/div/p/span";
	
	@FindBy(id="searchBtn") WebElement SearchButton;	
	
	@FindBy(id="hp-widget__depart")	 WebElement DepartureDate;
	@FindBy(id="hp-widget__return")	 WebElement ReturnDate;
	
	//Departure
	final String Dep_DatePicker= ".//div[contains(@class,'dateFilter')]//child:: div[contains(@class,'ui-datepicker-group-first')]";
	final String Dep_DatePicker_Right= ".//div[contains(@class,'dateFilter')]//child:: div[contains(@class,'ui-datepicker-group-last')]";
	final String Dep_First_Month_Year = Dep_DatePicker+ "//following-sibling:: div[@class = 'ui-datepicker-title']//span"; 
	
	@FindBy(xpath= Dep_First_Month_Year+"[1]")  WebElement Departure_FirstMonth;
	@FindBy(xpath= Dep_First_Month_Year+"[2]") WebElement Departure_FirstYear;	
	
	String Departure_CustomTextFirstDate = Dep_DatePicker + "//following-sibling:: table/tbody/tr/td/a";
	
	@FindBy(xpath=Dep_DatePicker+ "//child :: a[contains(@title,'Prev')]//span")WebElement Departure_PrevButton;
	@FindBy(xpath=Dep_DatePicker_Right+"//child :: a[contains(@title,'Next')]//span")WebElement Departure_NextButton;
	
	//Return
	final String Ret_DatePicker= ".//div[contains(@class,'dateFilterReturn')]//child:: div[contains(@class,'ui-datepicker-group-first')]";
	final String Ret_DatePicker_Right= ".//div[contains(@class,'dateFilterReturn')]//child:: div[contains(@class,'ui-datepicker-group-last')]";
	final String Ret_First_Month_Year = Ret_DatePicker+ "//following-sibling:: div[@class = 'ui-datepicker-title']//span"; 
	
	@FindBy(xpath= Ret_First_Month_Year+"[1]")  WebElement Return_FirstMonth;
	@FindBy(xpath= Ret_First_Month_Year+"[2]")  WebElement Return_FirstYear;	
	
	String Return_CustomTextFirstDate = Ret_DatePicker + "//following-sibling:: table/tbody/tr/td/a";
	
	@FindBy(xpath=Ret_DatePicker+ "//child :: a[contains(@title,'Prev')]//span")WebElement Return_PrevButton;
	@FindBy(xpath= Ret_DatePicker_Right+ "//child :: a[contains(@title,'Next')]//span")WebElement Return_NextButton;	
	
		
	//Enter From City
	public void selectFromCity(String fromcityName)
	{
		fromCity.clear();
		fromCity.sendKeys(fromcityName);
		driver.findElement(By.xpath(FromCityList+"[contains(text(),'"+fromcityName+"')]")).click();
		
	}
	//Enter To City
	public void selectToCity(String tocityName)
	{
		toCity.sendKeys(tocityName);
		driver.findElement(By.xpath(ToCityList+"[contains(text(),'"+tocityName+"')]")).click();
	}
	
	//search
	public void search()
	{
		SearchButton.click();		
	}
	

	///Calendar Functions
	public void selectDepartureDate(String Dep_Date)
	{
		//parse date
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		try
		{			
			date = dateFormat.parse(Dep_Date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
		
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		int ip_day = Integer.parseInt(new SimpleDateFormat("dd").format(date));    // always 2 digits
		String ip_month = new SimpleDateFormat("MMMM").format(date);  // always 2 digits
		String ip_year = new SimpleDateFormat("yyyy").format(date); // 4 digit year
		String ip_month_year = ip_month.toUpperCase()+ " "+ ip_year;
		System.out.println(ip_day + ip_month_year);	
		
		// Logic to navigate to the respective
		wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.visibilityOf(DepartureDate));
		
		DepartureDate.click();
		String CalendarFirstYear = Departure_FirstYear.getText();
		String  CalendarFirstMonth= Departure_FirstMonth.getText();		
		System.out.println(CalendarFirstMonth + " "+ CalendarFirstYear);
		
		while(true)
		{
			try 
			{
				driver.findElement(By.xpath(Dep_First_Month_Year+"[contains(text(),'"+ip_year+"')]")).isDisplayed();
				System.out.println("Desired year selected");
				selectmonth("Departure",ip_month);	
				System.out.println("Desired month selected");				
				break;
			}
			catch (Exception e)
			{
				Departure_NextButton.click();
			}
		}
		driver.findElement(By.xpath(Departure_CustomTextFirstDate+"[text()="+ip_day+"]")).click();
		System.out.println("Desired date selected");
	}
	
public void selectReturnDate(String Return_Date)
	{
		//parse date
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		try
		{			
			date = dateFormat.parse(Return_Date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
		
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		int ip_day = Integer.parseInt(new SimpleDateFormat("dd").format(date));    // always 2 digits
		String ip_month = new SimpleDateFormat("MMMM").format(date);  // always 2 digits
		String ip_year = new SimpleDateFormat("yyyy").format(date); // 4 digit year
		String ip_month_year = ip_month.toUpperCase()+ " "+ ip_year;
		System.out.println(ip_day + ip_month_year);	
		
		// Logic to navigate to the respective
		wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.visibilityOf(ReturnDate));
		
		ReturnDate.click();
		String CalendarFirstYear = Return_FirstYear.getText();
		String  CalendarFirstMonth= Return_FirstMonth.getText();		
		System.out.println(CalendarFirstMonth + " "+ CalendarFirstYear);
		
		while(true)
		{
			try 
			{
				driver.findElement(By.xpath(Ret_First_Month_Year+"[contains(text(),'"+ip_year+"')]")).isDisplayed();
				System.out.println("Desired year selected");
				selectmonth("Return",ip_month);	
				System.out.println("Desired month selected");				
				break;
			}
			catch (Exception e)
			{
				Return_NextButton.click();
			}
		}
		driver.findElement(By.xpath(Return_CustomTextFirstDate+"[text()="+ip_day+"]")).click();
		System.out.println("Desired date selected");
	}	
	
public void selectmonth(String Type,String ip_month)
{
	while(true)
	{
		try 
		{
			if(Type == "Departure")
			{
			    driver.findElement(By.xpath(Dep_First_Month_Year+"[contains(text(),'"+ip_month+"')]")).isDisplayed();	
			}
			else if(Type == "Return")
			{
				 driver.findElement(By.xpath(Ret_First_Month_Year+"[contains(text(),'"+ip_month+"')]")).isDisplayed();	
			}
			break;
		}
		catch (Exception e)
		{
			if(Type == "Departure")
			{
			   Departure_NextButton.click();
			}
			else if(Type == "Return")
			{
				Return_NextButton.click();
			}
		}
	}
}
	

	
 public  String getMonthForInt(int num) 
    {
    	
        String month = "Incorrect Month";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
     
        if (num >= 0 && num <= 11 )
        {
            month = months[num];
        }
       return month.toUpperCase();
   }
	    
 public static int getIntForMonth(String Month) 
    {
    	
    	List list = Arrays.asList(new String[] { "january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december" });
    	System.out.println(list.indexOf(Month.toLowerCase()));
    
        return list.indexOf(Month.toLowerCase()+1);
   }    
	
	
}
