package Qtrip.QtripProject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {
    
    public static boolean click(WebElement elementToBeClickable,WebDriver driver)
    {
        if(elementToBeClickable.isDisplayed())
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", elementToBeClickable);
            elementToBeClickable.click();
            return true;
        }
        else
            return false;  
    }

    public static boolean sendKeys(WebElement textBoxElement,String stringToSend)
    {
        textBoxElement.clear();
        textBoxElement.sendKeys(stringToSend);
        return true;
    }
    
    public static boolean navigate(WebDriver driver,String url)
    {
        if(url.equals(url))
        {
            driver.get(url);
            return true;
        }
        else
            return false;
        
    }

    public static WebElement findElementWithRetry(WebDriver driver,By by,int retryCount)
    {
        
        if(!driver.findElement(by).isDisplayed())
        {
            for(int i = 1; i <= retryCount; i++)
            {
                driver.findElement(by);
            }
            return null;
        }
        return driver.findElement(by);
    }
}