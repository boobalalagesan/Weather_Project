package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.BasePage;

public class ExtentReportManager extends BasePage {
	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports setExtentReport() {
		if(extent==null) {
			htmlReporter = new ExtentHtmlReporter(RunConfig.REPORT_PATH + ".html");
			htmlReporter.loadConfig(RunConfig.Extent_Config_Path);
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
		}
		return extent;
	}
}
