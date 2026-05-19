package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends BasePage{
	public Loginpage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//input[@id='input-email']")
	WebElement emailelement;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement passelement;
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginbtn;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
	WebElement lgout;
	public void setemail(String email) {
		emailelement.sendKeys(email);
	}
	public void setpass(String pass) {
		passelement.sendKeys(pass);
	}
	public void button() {
		loginbtn.click();
		
	}
	public void logoutbtn() {
		lgout.click();
		
	}
	

}
