package Qtrip.QtripProject.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReservationHistory {
    static WebDriver driver;
    public ReservationHistory(WebDriver driver)
    {
        this.driver = driver;
    }

    public  List<WebElement> reservationHistory()
    {
        List<WebElement> reservationList = driver.findElements(By.xpath("//tbody[@id='reservation-table']/tr"));
        return reservationList;
    }
}
