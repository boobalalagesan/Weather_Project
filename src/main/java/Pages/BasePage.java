package Pages;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Utils.RunConfig;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	public WebDriver driver;
	public ExtentTest test;
	public BasePage() {

	}
	public BasePage(ExtentTest test, WebDriver driver) {
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
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (Browser.contentEquals("Firefox")) {
			System.setProperty("webdriver.gecko.driver",RunConfig.MOZILLA_DRIVER_EXE);
			driver = new FirefoxDriver();
		}
		else if(Browser.contentEquals("Edge")) {
			System.setProperty("webdriver.edge.driver", RunConfig.EDGE_DRIVER_EXE);
		}
		driver.manage().window().maximize();
		driver.get(RunConfig.Test_ApplicationURL);
		return driver;
	}
}
