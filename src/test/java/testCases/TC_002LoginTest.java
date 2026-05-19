package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import BasePackage.BaseClass;
import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.MyAccountPage;

public class TC_002LoginTest  extends BaseClass {
	@Test(groups={"master","sanity"})
	public void verify_login() {
		try {
		logger.info("starting login test");
		Homepage hp=new Homepage(driver);
		logger.info("clicked my account");
		hp.clickMyAccount();
		logger.info("clicked my login");
		hp.clicklogin();
		
		
		Loginpage lp=new Loginpage(driver);
		Thread.sleep(5000);
		logger.info("passed email");
		lp.setemail(p.getProperty("email"));
		Thread.sleep(5000);
		logger.info("passed password");
		lp.setpass(p.getProperty("password"));
		lp.button();
		
		MyAccountPage myacc=new MyAccountPage(driver);
		logger.info("checking is my acc exist");
		Thread.sleep(5000);
		boolean targetpage=myacc.ismyaccExist();
		Assert.assertTrue(targetpage);
		logger.info("finished login page");
		}catch(Exception e) {
			Assert.fail();
		}
		
		
	}

}
