package Qtrip.QtripProject;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ReportSingleton {
    public static ExtentReports reports;
    public static ExtentTest test;
    static RemoteWebDriver driver;
    static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));

    static String configFilePath = System.getProperty("user.dir") + "/extent_customization_configs.xml";
    public static void setExtentReport()
    {
        reports = new ExtentReports(System.getProperty("user.dir") + "/ExtentReports_" + timeStamp +".html");
        reports.loadConfig(new File(configFilePath));
    }

    public static String capture(WebDriver driver) throws IOException
    {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File("src/../QKARTImages/" + System.currentTimeMillis()+ ".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return errflpath;
    }
    
    public static void endExtentReport()
    {
        reports.endTest(test);
        reports.flush();
    }
}