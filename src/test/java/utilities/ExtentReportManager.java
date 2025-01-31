package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;


public class ExtentReportManager implements ITestListener  {
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest test;

		String repName;

		public void onStart (ITestContext testContext) {
			
			/*SimpleDateFormat df-new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss") ;
			Date dt=new Date();
			String currentdatetimestamp=df.format(dt);
			*/

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
			repName = "Test-Report-" + timeStamp + ".html";
			sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); //specify location of the report
		
			sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title 
			sparkReporter.config().setReportName("opencart Functional Testing"); // name of 
			sparkReporter.config().setTheme(Theme.DARK); ;
		
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter); 
			extent.setSystemInfo("Application" , "opencart");
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("Sub Module", "Customers");
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Envoirnment", "QA");
			
			String os = testContext.getCurrentXmlTest().getParameter("os"); //this will capture value from the xml file 
			extent.setSystemInfo("Operating System", os);
			
			String browser = testContext.getCurrentXmlTest().getParameter("browser"); //this will capture value from the xml file
			extent.setSystemInfo("Browser", browser);
			
			List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups(); //getCurrentXmlTest() this will retun the xml file
			if(!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());	// add group name
			
			}
		}
		
	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName()); //extracting the class name from the executed class
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report 
		test.log(Status.PASS, result.getName()+" got successfully executed");
	}


	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName()); //extracting the class name from the executed class
		test.assignCategory(result.getMethod().getGroups());	   //extracting the group name 
		test.log(Status.FAIL, result.getName()+" got failed"); 
		test.log(Status.INFO, result.getThrowable().getMessage());
	
		try {
			String imgPath = new BaseClass().captureScreenshot(result.getName()); //attaching the screenshot of fail case
			test.addScreenCaptureFromPath(imgPath);
		}catch (IOException e1) {
			e1.printStackTrace(); 
		}
	}

	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test. log(Status.SKIP, result.getName()+" got skipped"); 
		test. log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {

		extent.flush();

		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);

		try{
	
			Desktop.getDesktop().browse(extentReport.toURI()); //open the report automatically
	
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
		/*
		try { 
			URL url = new ﻿﻿URL("file://"+System. getProperty("user.dir")+"\\reports\\"+repName);
				﻿
		//Create the email message
		ImageHtmlEmail email = new ImageHtmlEmail();
		﻿﻿email.setDataSourceResolver(new DataSourceUrlResolver(url));
		﻿﻿email.setHostName("smtp-googlemail.com"); //this is for gmail 
		email.setSmtpPort(465);					   //this is for gmail port
		﻿email.setAuthenticator(new DefaultAuthenticator("architgarg112@gmail.com", "")) ;
		email.setSSLOnConnect(true); 
		email.setFrom("architgarg112@gmail.com");//Sender 
		email.setSubject("Test Results");
		﻿﻿email.setMsg("Please find Attached Report....");
		﻿﻿email.addTo("arcit.garg@gmail.com"); //Receiver 
		email.attach(url,"extent report", "please check report..."); 
		email.send(); // send the email
		}
		﻿﻿catch(Exception e) 
		{ 
			e.printStackTrace() ;		
			}
		*/	
	}
	
}
