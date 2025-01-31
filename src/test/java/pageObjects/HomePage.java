package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	//Constructor
	
	public HomePage(WebDriver driver)
	{						// we have written a constructor in BasePage class we can use in all page object class by extending base page 	
		super(driver); 		// we are invoking parent class constructor by super key.
	}
	
	//Locators 
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkMyaccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegister;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement lnkLogin; //added in step 5 
	
	//Action
	
	public void clickMyAccount()
	{
		lnkMyaccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}
}
