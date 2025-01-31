package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger;	//Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {
	//it contain reusable methods like login, logout, random number creation
	//these cases will be used in all the test cases.
	
	public static WebDriver driver;
	public Logger logger;  //Log4j
	public Properties p; //congif.properties
	
	@BeforeClass(groups= {"sanity","regression","master","datadriven"})
	@Parameters({"os","browser"})
	void setup(String os,String br) throws IOException
	{
		//Loading congif.properties file
		FileReader file =new FileReader("./src//test//resources//config.properties");
		p=new Properties(); //creating object
		p.load(file); //load property file
		
		//logging
		logger=LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase())
		{
			case "chrome": driver=new ChromeDriver(); break;
			case "edge": driver=new EdgeDriver(); break;
			case "firefox": driver=new FirefoxDriver(); break;
			default: System.out.println("Invalid Browser"); return;
		}	
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appurl")); //reading url from property file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"sanity","regression","master","datadriven"})
	void tearDown()
	{
		driver.close();
	}

	//random no creation
	public String randomString() //new method for random alphabet
	{
		@SuppressWarnings("deprecation")
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomNum() //new method for random number
	{
		@SuppressWarnings("deprecation")
		String generatednum = RandomStringUtils.randomNumeric(10);
		return generatednum;
	}
	
	public String randomAlphaNum() //new method for random alphanum
	{
		@SuppressWarnings("deprecation")
		String generatedalphanum = RandomStringUtils.randomAlphanumeric(5);
		return generatedalphanum;
	}
	
	public String captureScreenshot(String tname) throws IOException{
		
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot ts =(TakesScreenshot)driver;
		File sourcefile = ts.getScreenshotAs(OutputType.FILE); //take ss
		
		String targetfilepath = System.getProperty("user.dir")+"\\screenshots\\" + tname+"_"+timestamp+".png";//dynamic ss path
		File targetfile = new File(targetfilepath); 
		
		sourcefile.renameTo(targetfile); //copy source file to target file
		return targetfilepath;
	}
	
}
