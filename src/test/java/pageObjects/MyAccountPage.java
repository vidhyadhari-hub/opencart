package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//div[@id='content']//h2[text()='My Account']")
	WebElement Myacctext;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
	WebElement logout;
	public boolean ismyaccExist() {
		try {
		return Myacctext.isDisplayed();
		}
		catch(Exception e){
			return false;
		}
	}
	public void logoutt() {
		logout.click();
	}
		
	

}
