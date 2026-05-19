package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	public  AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	

	@FindBy(xpath="//input[@placeholder=\"First Name\"]")
	WebElement firstname;
	@FindBy(xpath="//input[@placeholder=\"Last Name\"]")
	WebElement lastname;
	@FindBy(xpath="//input[@placeholder=\"E-Mail\"]")
	WebElement email;
	@FindBy(xpath="//input[@placeholder=\"Telephone\"]")
	WebElement phone;
	@FindBy(xpath="//input[@placeholder=\"Password\"]")
	WebElement password;
	@FindBy(xpath="//input[@placeholder=\"Password Confirm\"]")
	WebElement confirmpassword;
	@FindBy(xpath="//input[@type=\"checkbox\"]")
	WebElement checkbox;
	@FindBy(xpath="//input[@value=\"Continue\"]")
	WebElement continuebutton;
	@FindBy(xpath="//div[@id=\"content\"]//h1")
	WebElement confirmMsg;
	
	
	
	public void setFirstname(String first) {
			firstname.sendKeys(first);
	}
	public void setLastname(String last) {
		lastname.sendKeys(last);
	}public void setEmail(String emailname) {
		email.sendKeys(emailname);
	}public void setPhone(String phne) {
		phone.sendKeys(phne);	
	}public void setPassword(String psswd) {
		password.sendKeys(psswd);
	}
	public void setconfirmPassword(String pswd) {
		confirmpassword.sendKeys(pswd);
	}
	
	public void setCheck() {
		checkbox.click();
	}
	public void setcontinue() {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",continuebutton);
		//continuebutton.click();
	}
	public String setConfirmMsg() {
		try {
			return (confirmMsg.getText());
		}catch(Exception e)
		{
			return (e.getMessage());
		}
	}
	

}







