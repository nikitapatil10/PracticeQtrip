
package Qtrip.QtripProject.pages;

import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import Qtrip.QtripProject.DriverSingleton;
import Qtrip.QtripProject.SeleniumWrapper;

public class HistoryPage {
    WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html";

    public HistoryPage() throws MalformedURLException
    {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);

    }
    @FindAll(@FindBy(xpath="//tbody[@id='reservation-table']/tr/td[2]"))
    List<WebElement> reservationList;

    public void navigateToHistoryPage()
    {
        if(!driver.getCurrentUrl().equals(this.url))
        {
            SeleniumWrapper.navigate(driver, this.url);
        }
    }
    public int getReservations()
    {
        ReservationHistory history = new ReservationHistory(driver);
        return history.reservationHistory().size();
    }

    public void cancelReservation(String adventureName) throws InterruptedException
    {
        //List<WebElement> reservationList = driver.findElements(By.xpath("//tbody[@id='reservation-table']/tr/td[2]"));
        WebElement cancelButton = driver.findElement(By.xpath("//tbody[@id='reservation-table']/tr/th/following-sibling::td[8]/div/button"));
        for(WebElement reservations : reservationList)
        {
            //Thread.sleep(2000);
            if(reservations.getText().equals(adventureName))
            {
                SeleniumWrapper.click(cancelButton, driver);
                //Thread.sleep(2000);
                break;
                
            }
        }
    }

}