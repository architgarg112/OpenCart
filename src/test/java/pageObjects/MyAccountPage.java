package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage  extends BasePage{

		//Constructor
		public MyAccountPage(WebDriver driver)
		{						
			super(driver); 		
		}
		
		//Locators 
		@FindBy(xpath="//h2[normalize-space()=\"My Account\"]") WebElement msgHeading;
		@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkMyaccount;
		@FindBy(xpath="//a[normalize-space()=\"Logout\"]") WebElement btnLogout;  //added in step 6
		
		//Action
		public boolean isMyAccoubtPageExist()
		{
			try 
			{
			return (msgHeading.isDisplayed());
			}
			catch(Exception e)
			{
				return false;
			}
			
		}
		
		public void clickMyAccount()
		{
			lnkMyaccount.click();
		}
		
		public void clickLogoutbtn()
		{
			btnLogout.click();
		}
		
		
	}

	

