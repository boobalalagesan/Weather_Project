package Pages;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Utils.ExtentReportManager;
import Utils.RunConfig;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	public WebDriver driver;
	public static  int focus_Row;
	public ExtentReports extent=ExtentReportManager.setExtentReport();
	public ExtentTest test;
	public BasePage() {

	}
	public BasePage(WebDriver driver,ExtentTest test) {
		this.driver=driver;
		this.test=test;
	}
	public void takeScreenshot() {
		try {
			String path=((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);

			test.log(Status.INFO, "ScreenShot",MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
		} 
		catch (IOException e) {

			e.printStackTrace();
		}
	}

	public WebDriver openBrowser(String Browser) {

		if(Browser.contentEquals("Chrome")) {
			driver=WebDriverManager.chromedriver().create();
			//driver = new ChromeDriver();
		}
		else if (Browser.contentEquals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(Browser.contentEquals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get(RunConfig.Test_ApplicationURL);
		test.log(Status.PASS, "Application Opened"); 
		takeScreenshot();
		return driver;
	}
	
}
