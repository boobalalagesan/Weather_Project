package com.DemoPack;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Pages.BasePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClass extends BasePage{
	
	@Test
	public void run() throws IOException {

		WebDriver driver=WebDriverManager.edgedriver().create();
		//WebDriver driver=new EdgeDriver();
		driver.get("https://www.youtube.com/");
		/*
		 * test.log(Status.PASS, "YouTube opened"); takeScreenshot();
		 */

	}
	
	

}
