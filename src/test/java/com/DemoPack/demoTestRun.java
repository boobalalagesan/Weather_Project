package com.DemoPack;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import Pages.BasePage;

public class demoTestRun extends BasePage{
	WebDriver driver;
	@Test
	public void run() {
		test=extent.createTest("Opening Youtube");
		driver=openBrowser("Chrome");
		driver.get("https://www.youtube.com/");

	}
	@AfterTest
	public void close() {
		extent.flush();
		driver.close();
	}
}
