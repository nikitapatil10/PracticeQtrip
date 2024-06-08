package Qtrip.QtripProject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Qtrip.QtripProject.DriverSingleton;
import Qtrip.QtripProject.SeleniumWrapper;

import java.net.MalformedURLException;

public class HomePage {
   
    WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    @FindBy(xpath = "//a[text()='Register']")
    WebElement registerButton;

    @FindBy(xpath = "//li[@class='nav-item auth']/div")
    WebElement loggedIn;

    @FindBy(id="autocomplete")
    WebElement searchTextBox;

    @FindBy(id = "results")
    WebElement assertAutoCompleteWord;
    public HomePage() throws MalformedURLException
    {
        this.driver= DriverSingleton.getDriver();
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
    
    public void navigateToHomePage()
    {
            if(!driver.getCurrentUrl().equals(this.url))
            {
                SeleniumWrapper.navigate(driver, this.url);
            }
    }

    public void clickRegister()
    {
       SeleniumWrapper.click(registerButton, driver);
    }

    public Boolean isUserLoggedIn()
    {
       if(driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/"))
       {
            return true;
       }
       else{
        return false;
       }
    }

    public void loggedOutUSer()
    {
        SeleniumWrapper.click(loggedIn,driver);
    }

    public void searchCity(String city) throws InterruptedException
    {
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        
        
        SeleniumWrapper.sendKeys(searchTextBox,city);
        Thread.sleep(2000);
    }

    public Boolean assertAutoCompleteText(String city) throws InterruptedException
    {
        Thread.sleep(2000);
        if(assertAutoCompleteWord.getText().equals(city))
        {
            return true;
        }
        else 
        {  
            return false;
        }
        
    }
    public void selectCity(String city)
    {
    
            // WebDriverWait wait = new WebDriverWait(driver, 30);
            // wait.until(ExpectedConditions.visibilityOf(assertAutoCompleteWord));
            SeleniumWrapper.click(assertAutoCompleteWord,driver);
           
       
    }
}


