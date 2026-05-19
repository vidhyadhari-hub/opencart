package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage{
	public	Homepage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//span[normalize-space()='My Account']") 
	WebElement accountlink;
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement registrationlink;
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement loginlink;
	
	
	public void clickMyAccount() {
		accountlink.click();
	}
	public void clickRegister() {
		registrationlink.click();
	}
	public void clicklogin() {
		loginlink.click();
	}
	

}
