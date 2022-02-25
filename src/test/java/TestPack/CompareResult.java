package TestPack;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import Utils.ExcelData;
import org.openqa.selenium.WebDriver;
/*import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;*/
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import Pages.BasePage;
import Pages.WeatherPage;
import Utils.RunConfig;
import email.SendMailAfterExec;
import jxl.read.biff.BiffException;
public class CompareResult extends BasePage {
	WebDriver driver;
	String[][] data=null;
	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;

	WeatherPage weatherPage;
	@BeforeTest
	public void openUrl() {
		htmlReporter = new ExtentHtmlReporter(RunConfig.REPORT_PATH + ".html");
		htmlReporter.loadConfig(RunConfig.Extent_Config_Path);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		String Browser="Chrome";
		driver=openBrowser(Browser);
	}
	@Test(dataProvider = "cityData")
	public void TempCompare(String city) throws InterruptedException, IOException {
		ExtentTest test=extent.createTest("Comaparison value for "+city);
	    weatherPage=new WeatherPage(driver, test);
		PageFactory.initElements(driver, weatherPage);
		try {
			 weatherPage.getUITempValue(city);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Exception occured "+e);
		}

	}
	@AfterTest
	public void close() {
		driver.close();
		extent.flush();

		SendMailAfterExec sendMailAfterExec= new SendMailAfterExec();
		try {
			sendMailAfterExec.sendMail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@AfterSuite
	public void openReport()  {
		try {
			Desktop.getDesktop().browse(new File(RunConfig.REPORT_PATH+".html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@DataProvider(name="cityData")
	public String[][] cityData() throws BiffException, IOException {
		ExcelData excelData=new ExcelData();
		data=excelData.getExcelData();
		return data;
	}
}
