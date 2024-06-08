package Qtrip.QtripProject.pages;


import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Qtrip.QtripProject.DriverSingleton;
import Qtrip.QtripProject.SeleniumWrapper;

public class LoginPage {

    WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login";

    public LoginPage() throws MalformedURLException
    {
        this.driver= DriverSingleton.getDriver();
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    @FindBy(id="floatingInput")
    WebElement emailIdTextBox;

    @FindBy(id="floatingPassword")
    WebElement passwordTextBox;

    @FindBy(xpath="//button[text()='Login to QTrip']")
    WebElement loginButton;

    public void navigateToLoginPage()
    {
        if(!driver.getCurrentUrl().equals(this.url))
        {
            SeleniumWrapper.navigate(driver, this.url);
        }
    }

    public void performLogin(String emailId,String password) throws InterruptedException
    {
        SeleniumWrapper.sendKeys(emailIdTextBox,emailId);
        SeleniumWrapper.sendKeys(passwordTextBox,password);
        SeleniumWrapper.click(loginButton,driver);
        Thread.sleep(2000);
       // WebDriverWait wait = new WebDriverWait(driver,30);
        //wait.until(ExpectedConditions.alertIsPresent());
        //driver.switchTo().alert().accept();
       
    }
}
