package Qtrip.QtripProject.pages;

import java.net.MalformedURLException;
import java.util.List;
import javax.lang.model.util.ElementScanner6;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import Qtrip.QtripProject.DriverSingleton;
import Qtrip.QtripProject.SeleniumWrapper;


public class AdventurePage {
    WebDriver driver;
    public AdventurePage() throws MalformedURLException
    {
        this.driver= DriverSingleton.getDriver();
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
    
    @FindBy(id ="duration-select")
    WebElement filterValueTextBox;

    @FindBy(id ="category-select")
    WebElement categoryTextBox;

    @FindBy(id="search-adventures")
    WebElement adventureSearchTextBox;

    @FindAll(@FindBy(xpath = "//select[@id='category-select']/option"))
    List<WebElement> categoryList;

    @FindAll(@FindBy(xpath = "//select[@id='duration-select']/option"))
    List<WebElement> filterList;

    @FindAll(@FindBy(xpath = "//div[@id='data']/div"))
    List<WebElement> resultCount;

    @FindAll(@FindBy(xpath = "//div[@class='activity-card-text text-md-center w-100 mt-3']/div[1]/h5[1]"))
    List<WebElement> adventureList;

    HomePage home = new HomePage();
    public void setFilterValues(String filterValue) throws InterruptedException
    {
      
            SeleniumWrapper.click(filterValueTextBox, driver);
            //List<WebElement> filterList = driver.findElements(By.xpath("//select[@id='duration-select']/option"));
            for(WebElement sortedFilterList : filterList)
            {
                if(sortedFilterList.getText().equals(filterValue))
                {
                    SeleniumWrapper.click(sortedFilterList, driver);
                    break;
                }
            }
        
    }

    public void setCategoryValue(String category) throws InterruptedException
    {
        
        SeleniumWrapper.click(categoryTextBox, driver);
            //List<WebElement> categoryList = driver.findElements(By.xpath("//select[@id='category-select']/option"));
            for(WebElement sortedCategory : categoryList)
            {
                if(sortedCategory.getText().equals(category))
                {
                    SeleniumWrapper.click(sortedCategory, driver);
                    break;
                }
            }
        
       
        
    }

    public int getResultCount()
    {
      
        //List<WebElement> resultCount = driver.findElements(By.xpath("//div[@id='data']/div"));
        int actualCount = resultCount.size();
        return actualCount;     
    }
   
    public void selectAdventure(String adventure,WebDriver driver) throws InterruptedException
    {
        SeleniumWrapper.sendKeys(adventureSearchTextBox, adventure);
        Thread.sleep(2000);
         JavascriptExecutor js = (JavascriptExecutor) driver;
        //List<WebElement> adventurelist = driver.findElements(By.xpath("//div[@class='activity-card-text text-md-center w-100 mt-3']/div[1]/h5[1]"));
		for(WebElement l : adventureList)
		{

            js.executeScript("arguments[0].scrollIntoView();", l);  
            Thread.sleep(2000);
			if(l.getText().equals(adventure))
			{
				SeleniumWrapper.click(l, driver);
                Thread.sleep(2000);
				break;
			}
		}
    }
}

