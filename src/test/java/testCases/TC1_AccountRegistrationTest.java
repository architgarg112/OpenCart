package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC1_AccountRegistrationTest extends BaseClass{
	
	@Test(groups = {"regression","master"})
	public void verify_account_registration() throws InterruptedException
	{
		logger.info("------- TC1_AccountRegistrationTest Test Started -------");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("Open Registration page");
		AccountRegistrationPage ar =new AccountRegistrationPage(driver);
		//static data
		/*ar.setFirstName("Ag");
		ar.settTelephone("56256445");
		ar.setPassword("Opencart112");
		*/
		
		logger.info("Providing customer details");
		//Dynamic data
		ar.setFirstName(randomString().toUpperCase()); //random data with upper case
		ar.setLastName(randomString().toUpperCase());
		String mail = randomString()+"@gmail.com"; //we cant pass same email again and again so need to generate random email everytime 
		ar.setEmail(mail);
		System.out.println("Email : "+mail);
		ar.settTelephone(randomNum());
		
		String pass=randomAlphaNum();
		System.out.println("Password : "+pass);
		
		ar.setPassword(pass);
		ar.setConfirmPassword(pass);
		ar.setPrivacyPolicy();
		Thread.sleep(5000);
		ar.clickContinue();
		String confmsg =ar.getConfirmationMsg();
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!"); as it is hard assertion so once it is failed catch block is not executed that y we use if else here
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.debug("debug logs");
			logger.error("Error logs");
			
			Assert.assertTrue(false);
		}
		

		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("------- TC1_AccountRegistrationTest Test Finished -------");
	}
}
