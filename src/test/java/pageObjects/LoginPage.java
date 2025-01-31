package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	//Constructor
	
	public LoginPage(WebDriver driver)
	{						// we have written a constructor in BasePage class we can use in all page object class by extending base page 	
		super(driver); 		// we are invoking parent class constructor by super key.
	}
	
	//Locators 
	@FindBy(xpath="//input[@id=\"input-email\"]") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPass;
	@FindBy(xpath="//input[@value=\"Login\"]") WebElement btnLogin;
	
	//Action
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setPass(String pass)
	{
		txtPass.sendKeys(pass);
	}
	
	public void clickLoginbtn()
	{
		btnLogin.click();
	}
	
	
}
