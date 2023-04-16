package TestPack;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import Utils.ExcelData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import Pages.BasePage;
import Pages.WeatherPage;
import Utils.RunConfig;
import email.SendMailAfterExec;
import jxl.read.biff.BiffException;

public class CompareResult extends BasePage {
	WebDriver driver;
	WeatherPage weatherPage;

	@Test(dataProvider = "cityData")
	public void TempCompare(String RunMode, String Browser, String city) throws InterruptedException, IOException {

		test = extent.createTest("Comaparison value for " + city);
		driver = openBrowser("Edge");
		weatherPage = new WeatherPage(driver, test);
		PageFactory.initElements(driver, weatherPage);
		try {
			weatherPage.getUITempValue(city);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Assert.fail("Exception occured " + e);
		}

	}

	@AfterTest
	public void close() {
		extent.flush();
		driver.close();
		SendMailAfterExec sendMailAfterExec = new SendMailAfterExec();
		try {
			// sendMailAfterExec.sendMail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void openReport() {
		try {
			Desktop.getDesktop().browse(new File(RunConfig.REPORT_PATH + ".html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@DataProvider(name = "cityData")
	public String[][] cityData() throws BiffException, IOException {
		ExcelData excelData = new ExcelData(RunConfig.DATA_PATH);
		String[][] data = excelData.getData("RunSheet");
		return data;
	}
}
