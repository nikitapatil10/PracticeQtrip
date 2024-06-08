package Qtrip.QtripProject.pages;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Qtrip.QtripProject.DriverSingleton;
import Qtrip.QtripProject.SeleniumWrapper;



public class RegisterPage {

    WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    public String lastGeneratedUsername="";

    public  RegisterPage() throws MalformedURLException
    {
        this.driver= DriverSingleton.getDriver();
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    @FindBy(id="floatingInput")
    //WebElement emailAddressTextBox = SeleniumWrapper.findElementWithRetry(driver,By.id("floatingInput"),3);
    WebElement emailAddressTextBox;

    @FindBy(xpath="//div[@class='form-floating mb-3'][2]/input")
    WebElement passwordTextBox;// = SeleniumWrapper.findElementWithRetry(driver,By.xpath("//div[@class='form-floating mb-3'][2]/input"),3);

    @FindBy(xpath="//div[@class='form-floating mb-3'][3]/input")
    WebElement confirmPasswordTextBox;

    @FindBy(xpath="//button[text()='Register Now']")
    WebElement registerButton;

    public void navigateToRegisterPage()
    {
        if(!driver.getCurrentUrl().equals(this.url))
        {
            SeleniumWrapper.navigate(driver, this.url);
        }
    }

    public Boolean registerNewUser(String emailId,String password,Boolean generateRandomUsername) throws InterruptedException
    {

        driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
        if (generateRandomUsername == true)
        emailId = emailId+UUID.randomUUID().toString();
        SeleniumWrapper.sendKeys(emailAddressTextBox,emailId);
        //Thread.sleep(2000);
        SeleniumWrapper.sendKeys(passwordTextBox,password);
        //Thread.sleep(2000);
        SeleniumWrapper.sendKeys(confirmPasswordTextBox,password);
        //Thread.sleep(2000);
        SeleniumWrapper.click(registerButton,driver);
        //Thread.sleep(2000);
        this.lastGeneratedUsername = emailId;
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("https://qtripdynamic-qa-frontend.vercel.app/pages/login"));

        return this.driver.getCurrentUrl().endsWith("/login");
    }
}
