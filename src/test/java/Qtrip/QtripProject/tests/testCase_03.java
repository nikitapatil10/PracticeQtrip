package Qtrip.QtripProject.tests;



import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Qtrip.QtripProject.DP;
import Qtrip.QtripProject.DriverSingleton;
import Qtrip.QtripProject.ReportSingleton;
import Qtrip.QtripProject.pages.AdventureDetailsPage;
import Qtrip.QtripProject.pages.AdventurePage;
import Qtrip.QtripProject.pages.HistoryPage;
import Qtrip.QtripProject.pages.HomePage;
import Qtrip.QtripProject.pages.LoginPage;
import Qtrip.QtripProject.pages.RegisterPage;

public class testCase_03 {
   @Test(description = "verify that adventure bookiing and cancellation works fine",dataProvider = "data-provider",dataProviderClass = DP.class,priority = 3,groups = {"Booking and Cancellation Flow"})
   public void TestCase03(String userName,String password,String city,String adventureName, String guestName,String date,String persons) throws InterruptedException, IOException
   {
        Boolean status;
        ReportSingleton.test = ReportSingleton.reports.startTest("verify that adventure bookiing and cancellation works fine");
        WebDriver driver= DriverSingleton.getDriver();
        RegisterPage register = new RegisterPage();
        //Thread.sleep(2000);
        register.navigateToRegisterPage();
        //Thread.sleep(2000);
        status = register.registerNewUser(userName, password, true);
        //Thread.sleep(2000);
        
        AssertJUnit.assertTrue(status);
        String lastGeneratedUsername = register.lastGeneratedUsername;
       // System.out.println(lastGeneratedUsername);
        LoginPage login = new LoginPage();
        login.navigateToLoginPage();
        login.performLogin(lastGeneratedUsername, password);
       // Thread.sleep(2000);
        HomePage home = new HomePage();
        home.navigateToHomePage();
        Thread.sleep(2000);
        home.searchCity(city);
        Boolean result = home.assertAutoCompleteText(city);
        if(!result)
        {
         System.out.println("No city Found");
        }
        else
        {
         home.selectCity(city);
         Thread.sleep(2000);
         AdventurePage adventure = new AdventurePage();
         adventure.selectAdventure(adventureName,driver);
         Thread.sleep(2000);
         AdventureDetailsPage adventureDetails = new AdventureDetailsPage();
         //adventureDetails.navigateToAdventurePage();
         adventureDetails.bookAdventureDetails(guestName, date, persons);
         Thread.sleep(2000);
         HistoryPage history = new HistoryPage();
         history.navigateToHistoryPage();
         Thread.sleep(2000);
         int count = history.getReservations();
         Thread.sleep(2000);
         AssertJUnit.assertEquals(count,1);
         history.cancelReservation(adventureName);
         Thread.sleep(2000);
         driver.navigate().refresh();
         Thread.sleep(2000);
         count = history.getReservations();
         Thread.sleep(2000);
         AssertJUnit.assertEquals(count,0);
         ReportSingleton.test.log(LogStatus.PASS,ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver)) ,"Successfully verify that adventure bookiing and cancellation works fine");
         home.loggedOutUSer();
        }    
        
   }


}