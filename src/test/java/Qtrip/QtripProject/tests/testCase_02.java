package Qtrip.QtripProject.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import com.relevantcodes.extentreports.LogStatus;

import Qtrip.QtripProject.DP;
import Qtrip.QtripProject.DriverSingleton;
import Qtrip.QtripProject.ReportSingleton;
import Qtrip.QtripProject.pages.AdventurePage;
import Qtrip.QtripProject.pages.HomePage;;

public class testCase_02 {
 
    @Test(description = "verify that the search and filters are working fine",dataProvider = "data-provider",dataProviderClass = DP.class,priority = 2,groups = {"Search and Filter flow"})
    public void TestCase02(String cityName, String categoryFilter, String durationFilter, String expectedFilterResult, String expectedUnfilterResult ) throws InterruptedException, IOException
    {
        int status;
        ReportSingleton.test = ReportSingleton.reports.startTest("verify that the search and filters are working fine");
        WebDriver driver= DriverSingleton.getDriver();
        HomePage home = new HomePage();
        home.navigateToHomePage();
        Thread.sleep(2000);
        home.searchCity(cityName);
        //Thread.sleep(2000);
        
        Boolean result = home.assertAutoCompleteText(cityName);
        if(!result)
        {
            System.out.println("No city Found");
        }
        else{
              //Thread.sleep(2000);
              home.selectCity(cityName);
              //Thread.sleep(2000);
              AdventurePage adventure = new AdventurePage();
              adventure.setFilterValues(durationFilter);
              Thread.sleep(2000);
              adventure.setCategoryValue(categoryFilter);
              //Thread.sleep(2000);
              status = adventure.getResultCount();
              //Thread.sleep(2000);
              AssertJUnit.assertEquals(String.valueOf(status),expectedFilterResult,"The actual and expected count is different");
              //Thread.sleep(2000);
              driver.findElement(By.xpath("//select[@id='duration-select']/following-sibling::div")).click();
              driver.findElement(By.xpath("//select[@id='category-select']/following-sibling::div")).click();
              driver.findElement(By.xpath("//input[@id='search-adventures']/following-sibling::div")).click();
              //Thread.sleep(2000);
              status = adventure.getResultCount();
              AssertJUnit.assertEquals(String.valueOf(status),expectedUnfilterResult,"The actual and expected count of unfiltered values is different");
              ReportSingleton.test.log(LogStatus.PASS,ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver)) ,"Successfully verify that the search and filters are working fine");
            }
    }
   
}
  