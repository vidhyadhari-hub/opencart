package BasePackage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups= {"sanity","master","regression","datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os,String bs) throws Exception {
		FileReader file=new FileReader("./src/test/resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			 DesiredCapabilities cap = new DesiredCapabilities();
			 if(os.equalsIgnoreCase("mac")) {
				 cap.setPlatform(Platform.MAC);
			 }
			 else if(os.equalsIgnoreCase("windows"))
			 {
				 cap.setPlatform(Platform.WIN11);
			 }
			 else if(os.equalsIgnoreCase("linux")) {
				 cap.setPlatform(Platform.LINUX);
			 }
			 else
				 System.out.println("no matching os");
			 switch(bs.toLowerCase()) {
				case "chrome":cap.setBrowserName("chrome");break;
				case "safari":cap.setBrowserName("safari");break;
				case "firefox":cap.setBrowserName("firefox");break;
				default: System.out.println("invalid browser name");
						return;
				}
			 driver = new RemoteWebDriver (new URL("http://10.83.33.83:4444"),cap);
		     
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		
		
		switch(bs.toLowerCase()) {
		case "chrome":driver=new ChromeDriver();
						break;
		case "safari":driver=new SafariDriver();
						break;
		default: System.out.println("invalid browser name");
				return;
		}
		
		driver.get(p.getProperty("appurl"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		}	
	}
	public String randomeString() {
		String generatedstring=RandomStringUtils.secure().nextAlphabetic(5);
		return generatedstring;
	}
	public String randomeint() {
		String generatednumber=RandomStringUtils.secure().nextNumeric(10);
		return generatednumber;
	}
	public String randomeAlphaNumeric() {
		String generatednum=RandomStringUtils.secure().nextNumeric(3);
		String generatedstr=RandomStringUtils.secure().nextAlphabetic(3);
		return (generatednum+"@"+generatedstr);
	}
	@AfterClass
	public void teardown() {
		driver.quit();
		
	}
	public String captureScreen(String testName) throws IOException {

	    // Time Stamp
	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    // Screenshot Object
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    // Source File
	    File sourceFile = ts.getScreenshotAs(OutputType.FILE);
	    // Target Path
	    String targetFilePath = System.getProperty("user.dir")
	            + "/screenshots/"
	            + testName + "_" + timeStamp + ".png";

	    // Target File
	    File targetFile = new File(targetFilePath);
	    // Copy Screenshot
	    FileUtils.copyFile(sourceFile, targetFile);
	       return targetFilePath;
	}
	

}
