package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;


public class TC3_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups = "datadriven") //class is added coz data provider is in diffrent class
	public void verify_loginDDT(String email, String pass, String exp) throws InterruptedException
	{	//String email, String pass, String exp
		logger.info("***** starting TC3_LoginDDT *****");
		try
		{
		//HomePage
		HomePage hp =new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();   
		
		//Login
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPass(pass);
		lp.clickLoginbtn();
		
		//logged in, now verifying the MyAccount heading exists
		MyAccountPage mp=new MyAccountPage(driver);
		boolean head=mp.isMyAccoubtPageExist();
		
		
		/*Data is valid - login success - test pass - logout
		Data is valid -- login failed - test fail

		Data is invalid - login success - test fail - logout
		Data is invalid - login failed - test pass
		*/
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(head==true)
			{
				//logger.info("***** login success for valid *****");
				mp.clickMyAccount();
				mp.clickLogoutbtn();
				//logger.info("***** logout success for valid *****");
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(head==true)
			{
				//logger.info("***** login success for invalid (Fail)*****");
				mp.clickMyAccount();
				mp.clickLogoutbtn();
				//logger.info("***** logout success for invalid *****");
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finshed TC3_LoginDDT *****");
	}
	
}
