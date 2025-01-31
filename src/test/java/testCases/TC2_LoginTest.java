package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC2_LoginTest extends BaseClass{

	@Test(groups={"sanity","master"})
	public void verify_login() throws InterruptedException
	{	
		try
		{
		logger.info("***** starting TC2_LoginTest *****");
		//click on login link from dropdown
		HomePage hp =new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();   
		
		//entering details for login
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPass(p.getProperty("pass"));
		lp.clickLoginbtn();
		
		//logged in, now verifying the MyAccount heading exists
		MyAccountPage mp=new MyAccountPage(driver);
		boolean head=mp.isMyAccoubtPageExist();
		//Assert.assertEquals(head, true,"Login Failed");
		Assert.assertTrue(head);
		Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** Finshed TC2_LoginTest *****");
		
	}
}
