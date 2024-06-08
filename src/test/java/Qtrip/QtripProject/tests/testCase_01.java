package Qtrip.QtripProject.tests;




import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import java.io.IOException;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.LogStatus;

import Qtrip.QtripProject.DP;
import Qtrip.QtripProject.DriverSingleton;
import Qtrip.QtripProject.ReportSingleton;
import Qtrip.QtripProject.pages.HomePage;
import Qtrip.QtripProject.pages.LoginPage;
import Qtrip.QtripProject.pages.RegisterPage;


public class testCase_01 {
    @Test(description = "verify the user is register successfully",dataProvider = "data-provider",dataProviderClass = DP.class,priority = 1,groups = {"Login Flow"})
    //@Parameters({ "TC1_Username", "TC1_Password" })
    public void TestCase01(String TC1_Username,String TC1_Password) throws InterruptedException, IOException
    {
        Boolean status;  
        ReportSingleton.test = ReportSingleton.reports.startTest("verify the user is register successfully");
        WebDriver driver= DriverSingleton.getDriver();
        RegisterPage register = new RegisterPage();
        //Thread.sleep(2000);
        register.navigateToRegisterPage();
        //Thread.sleep(2000);
        status = register.registerNewUser(TC1_Username, TC1_Password, true);
        //Thread.sleep(2000);
        
        AssertJUnit.assertTrue(status);
        String lastGeneratedUsername = register.lastGeneratedUsername;
       // System.out.println(lastGeneratedUsername);
        LoginPage login = new LoginPage();
        login.navigateToLoginPage();
        login.performLogin(lastGeneratedUsername, TC1_Password);
        //Thread.sleep(2000);
        ReportSingleton.test.log(LogStatus.PASS,ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver)) ,"Successfully logged in with the registered  user");
        HomePage home = new HomePage();
        status = home.isUserLoggedIn();
        //Thread.sleep(2000);
        AssertJUnit.assertTrue(status);
        home.loggedOutUSer();
        // // Thread.sleep(2000);
        // assertFalse(status,"User is still logged in");
    }

    
    
    
    
}