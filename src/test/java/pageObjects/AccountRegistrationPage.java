package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	//Constructor
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	//Locators 
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstname;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastname;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtemail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement chkdPolicy; 	
	@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
	@FindBy(xpath="//div//h1[.='Your Account Has Been Created!']") WebElement msgConfirmation;
	
	
	//Action
	public void setFirstName(String fname)
	{
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtemail.sendKeys(email);
	}
	
	public void settTelephone(String tel)
	{
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)
	{
		txtConfirmPassword.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy()
	{
		chkdPolicy.click();
	}
	
	public void clickContinue()
	{
		//1
		btnContinue.click();
		
		//2
		//btnContinue.submit();
		
		//3
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		
		//4
		//JavascriptExecutor js =(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click()", btnContinue);
		
		//5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//6
		//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		
	}
	
	public String getConfirmationMsg()
	{
		try {
				return (msgConfirmation.getText());
			}
		catch(Exception e) 
			{
				return (e.getMessage());
			}
	}
	
}
