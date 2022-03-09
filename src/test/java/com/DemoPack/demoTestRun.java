package com.DemoPack;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Pages.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;

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
