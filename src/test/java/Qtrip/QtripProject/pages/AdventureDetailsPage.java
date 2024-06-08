
package Qtrip.QtripProject.pages;


import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument.Field.Xpath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Qtrip.QtripProject.DriverSingleton;
import Qtrip.QtripProject.SeleniumWrapper;

public class AdventureDetailsPage {
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/detail/?adventure=2757195090";
    static WebDriver driver;

    public AdventureDetailsPage() throws MalformedURLException
    {
        this.driver= DriverSingleton.getDriver();
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void navigateToAdventurePage()
    {
        if(!driver.getCurrentUrl().equals(this.url))
        {
           SeleniumWrapper.navigate(driver, this.url);
        }
    }

    @FindBy(xpath = "//form[@id='myForm']/input[1]")
    WebElement nameTextBox;

    @FindBy(xpath = "//form[@id='myForm']/input[2]")
    WebElement dateTextBox;

    @FindBy(xpath = "//form[@id='myForm']/div/div[2]/input")
    WebElement personsTextBox;

    @FindBy(xpath = "//button[text()='Reserve']")
    WebElement reservationButton;

    public void bookAdventureDetails(String name,String date ,String persons) throws InterruptedException
    {
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        
        wait.until(ExpectedConditions.visibilityOf(nameTextBox));
        SeleniumWrapper.sendKeys(nameTextBox, name);
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(dateTextBox));
        SeleniumWrapper.sendKeys(dateTextBox, date);
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(personsTextBox));
        SeleniumWrapper.sendKeys(personsTextBox, persons);
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(reservationButton));
        SeleniumWrapper.click(reservationButton, driver);
        wait.until(ExpectedConditions.textToBePresentInElement(reservationMessage, "Greetings! Reservation for this adventure is successful"));
    }
    @FindBy(xpath = "//a[text()='Reservations']")
    WebElement reservationHistory;

    @FindBy(xpath = "//div[@id='reserved-banner']")
    WebElement reservationMessage;

    public Boolean isBookingSuccessful()
    {
        if(reservationMessage.getText().contains("Greetings! Reservation for this adventure is successful"))
        {
            return true;
        }
        else    
            return false;
    }

}
