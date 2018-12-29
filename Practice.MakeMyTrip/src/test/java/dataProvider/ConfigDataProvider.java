package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider 
{
	Properties pro;
	
	public ConfigDataProvider()
	{
		File src = new File ("./Configuration/config.properties");
		
		try
		{
			FileInputStream fis = new FileInputStream(src);			
			pro = new Properties();
			pro.load(fis);
		}
		catch (Exception e)
		{
			System.out.println("The Exception message is" + e.getMessage());
		}
	}
	
	public String getApplicationURL()
	{
		String url = pro.getProperty("URL");
		return url;
	}
	
	public String getChromePath()
	{
		String chromepath = pro.getProperty("chromePath");
		return chromepath;
	}
	 
	

}
