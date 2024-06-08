package Qtrip.QtripProject;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;
import javax.sql.RowSetReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {

    private static WebDriver  driver = null;

    //private static DriverSingleton singleton = null;

    @BeforeSuite
    private static WebDriver launchBrowser() throws MalformedURLException{
        if(driver == null){
            // final DesiredCapabilities capabilities = new DesiredCapabilities();
            // capabilities.setBrowserName(BrowserType.CHROME);
            // driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"),capabilities);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            ReportSingleton.setExtentReport();
            
        }
        return driver;      
    }

    private DriverSingleton() throws MalformedURLException
    {
        DriverSingleton.driver = launchBrowser();
    }
    public static WebDriver getDriver() throws MalformedURLException{
        return driver;
    }
    @AfterTest
    public void quitDriver()
    {
        ReportSingleton.endExtentReport();
        driver.quit();
    }
}