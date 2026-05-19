package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import BasePackage.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.Homepage;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"master","regression"})
	public void verifyAccountCreation() {
		try {
		logger.info("starting account registration page");
		Homepage hp=new Homepage(driver);
		hp.clickMyAccount();
		logger.info("clicked on myaccount link");
		hp.clickRegister();
		logger.info("clicked on registration link");
		Thread.sleep(5000);
		AccountRegistrationPage accreg=new AccountRegistrationPage(driver);
		logger.info("providing customer info");
		accreg.setFirstname(randomeString().toUpperCase());
		accreg.setLastname(randomeString().toUpperCase());
		accreg.setEmail(randomeString()+"@gmail.com");
		accreg.setPhone(randomeint());
		String p=randomeAlphaNumeric();
		accreg.setPassword(p);
		accreg.setconfirmPassword(p);
		accreg.setCheck();
		Thread.sleep(5000);
		accreg.setcontinue();
		logger.info("validating account registration");
		Thread.sleep(5000);
		String conmmsg=accreg.setConfirmMsg();
		if(conmmsg.equals("Your Account Has Been Created!")) {
			AssertJUnit.assertTrue(true);
		}
		else	
		{
			logger.error("test failed");
			logger.debug("debug logs");
			AssertJUnit.assertTrue(false);
		}
		//Assert.assertEquals(conmmsg,"Your Account Has Been");
		}catch(Exception e) {
			
			AssertJUnit.fail();
		}
		logger.info("finished");
		
	}
	
	

}
