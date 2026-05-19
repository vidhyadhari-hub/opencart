package testCases;


import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import BasePackage.BaseClass;
import Utilities.DataProviders;
import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.MyAccountPage;

	public class TC_003LoginDDT extends BaseClass{
		@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="datadriven")
		public void verify_login(String email,String password,String Exp) {
			try {
			logger.info("login test");
			//homepage
			Homepage hp=new Homepage(driver);
			hp.clickMyAccount();
			hp.clicklogin();
			
			//loginpage
			Loginpage lp=new Loginpage(driver);
			lp.setemail(email);
			lp.setpass(password);
			lp.button();
			WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
			MyAccountPage myacc=new MyAccountPage(driver);
			//Thread.sleep(5000);
			//mywait.until(ExpectedConditions.visibilityOf(myacc));
			boolean target_page=myacc.ismyaccExist();
			if(Exp.equalsIgnoreCase("Valid")) 
			{
				if(target_page==true)
				{
					Assert.assertTrue(true);
					myacc.logoutt();
				}
				else
					Assert.assertTrue(false);
			}
			if(Exp.equalsIgnoreCase("Invalid"))
			{
				if(target_page==true) 
				{
					myacc.logoutt();
					Assert.assertTrue(false);	
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
			logger.info("finished login test");
			}catch(Exception e) {
				Assert.fail();
			}
		}
	}
			
			
			
			


	    