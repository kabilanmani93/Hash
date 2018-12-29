package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;


public class ListPage 
{
	static WebDriver driver;
	static WebDriverWait wait;
	
	public ListPage(WebDriver ldriver)
	{
		this.driver = ldriver;
	}
	
	//ORs
	final String Dep_LeftSideList_Panel= "//div[contains(@class,'left_pannel')]";
	final String Dep_LeftSideList_Panel_NoofRecords= Dep_LeftSideList_Panel + "//child :: div[contains(@class,'ng-binding ng-scope')]";
	
	final String Ret_RightSideList_Panel= "//div[contains(@class,'right_pannel')]";
	final String Ret_RightSideList_Panel_NoofRecords= Ret_RightSideList_Panel + "//child :: div[contains(@class,'ng-binding ng-scope')]";
	
	final String FlightName_path ="/div[contains(@class,'hidden-xs visible-stb')]/div[1]//following-sibling :: span[contains(@class,'splt_flt_info' )]";
	final String DepartureTime_path ="/div[contains(@class,'hidden-xs visible-stb')]/div[2]/p[contains(@class,'splt_time_info')]";
	final String ArrivalTime_path="/div[contains(@class,'hidden-xs visible-stb')]/div[3]//following-sibling :: p[contains(@class,'splt_time_info')]";
	final String Rates_path ="/div[contains(@class,'hidden-xs visible-stb')]/div[4]//following-sibling :: p";

	//functions
	public HashMap<String, List<String>>  Fetch_Depature_FlightFares()
	{
		List<WebElement> rows = driver.findElements(By.xpath(Dep_LeftSideList_Panel_NoofRecords));
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		
		for (int i=0,j=0 ; i<rows.size(); i++,j++)
		{
		   String Daparture_FlightName = driver.findElement(By.xpath(Dep_LeftSideList_Panel_NoofRecords+"/div[(@data-flt-index='"+i+"')]"+FlightName_path)).getText();
		   String Daparture_Fligh_DeptTime = driver.findElement(By.xpath(Dep_LeftSideList_Panel_NoofRecords+"/div[(@data-flt-index='"+i+"')]"+DepartureTime_path)).getText();
		   String Daparture_FlightArrivTime= driver.findElement(By.xpath(Dep_LeftSideList_Panel_NoofRecords+"/div[(@data-flt-index='"+i+"')]"+ArrivalTime_path)).getText();
		   String Daparture_FlightRate = driver.findElement(By.xpath(Dep_LeftSideList_Panel_NoofRecords+"/div[(@data-flt-index='"+i+"')]"+Rates_path)).getText();
		   
		   /*
		   System.out.println("The FlightName is " + Daparture_FlightName);
		   System.out.println("The Dtime is " + Daparture_Fligh_DeptTime);
		   System.out.println("The ATime is " + Daparture_FlightArrivTime);
		   System.out.println("The FRate is " + Daparture_FlightRate);
		   */
		   
		   //array of objects
		   //List<FareDetails> FlightDetails = new ArrayList<FareDetails>();
		   System.out.println("------------------------------------------------START ARRAY SORT");
		   FareDetails[] fareDetails = new FareDetails[rows.size()];
		   //fareDetails[i].setFareDetails(Daparture_FlightName,Daparture_Fligh_DeptTime,Daparture_FlightArrivTime,Daparture_FlightRate);
		   fareDetails[i]= new FareDetails(Daparture_FlightName,Daparture_Fligh_DeptTime,Daparture_FlightArrivTime,Daparture_FlightRate);
		   //Arrays.sort(fareDetails, FareDetails.FlightNameComparator);
		   System.out.println(fareDetails[i].getFlightName());
		
		   System.out.println("------------------------------------------------END ARRAY SORT");
		   
		   
		   //list
		   List<String> object = new LinkedList<String>(); 
		   object.add(Daparture_FlightName); 
		   object.add(Daparture_Fligh_DeptTime); 
		   object.add(Daparture_FlightArrivTime);
		   object.add(Daparture_FlightRate);
		   		   
		   //map		   
		   map.put("Dept_Fare_"+j, object);		   
		   
		}
		return map;
	}
	

	
	
	//Create 
	public static class FareDetails implements Comparable<FareDetails>
	{
		private String Fname;
		private String DTime;
		private String ATime;
		private String Rate;
		
		public FareDetails( String FlightName, String DepTime, String ArrTime,String Rates)
		{
			 super();
			 this.Fname = FlightName ;
			 this.DTime = DepTime;
			 this.ATime = ArrTime;
			 this.Rate = Rates;
		}
		
		public String getFlightName() {
			return Fname;
		}
		public void setFruitName(String FlightName) {
			this.Fname = FlightName;
		}
		
		public String getDeptTiime() {
			return DTime;
		}
		public void setDepTime(String DepTime) {
			this.DTime = DepTime;
		}
		
		public String getArrTime() {
			return ATime;
		}
		public void setArrTime(String ArrTime) {
			this.ATime = ArrTime;
		}
		
		public String getFlightRate() {
			return Rate;
		}
		public void setFlightRate(String Rates) {
			this.Rate = Rates;
		}

		public int compareTo(FareDetails compareFare) 
		{
			
			int compareFlightRate =  Integer.parseInt(compareFare.getFlightRate()); 
			
			//ascending order
			return Integer.parseInt(this.Rate) - compareFlightRate;
			
			//descending order
			//return compareQuantity - this.quantity;
			
		}
		
		
		public static Comparator<FareDetails> FlightNameComparator = new Comparator<FareDetails>() 
		{

		    public int compare(FareDetails fare1, FareDetails fare2) 
		    {
		    	
		      String fareName1 = fare1.getFlightName().toUpperCase();
		      String fareName2 = fare2.getFlightName().toUpperCase();
		      
		      //ascending order
		      return fareName1.compareTo(fareName2);
		      
		      //descending order
		      //return fruitName2.compareTo(fruitName1);
		    }
		};
		
	}
}
	

