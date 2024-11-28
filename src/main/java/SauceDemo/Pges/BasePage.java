package SauceDemo.Pges;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void waitVisibility(By elementLocator){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementLocator));
    }
    public void waitVisibility(WebElement elementToWait){
        wait.until(ExpectedConditions.visibilityOf(elementToWait));
    }
    public void writeText(By elementLocator, String text){
        waitVisibility(elementLocator);
        driver.findElement(elementLocator).sendKeys(text);
    }
    public void clickElement(By elementLocator){
        waitVisibility(elementLocator);
        driver.findElement(elementLocator).click();
    }
    public void clickElement(WebElement elementToClick){
        waitVisibility(elementToClick);
        elementToClick.click();
    }
    public String readTextFromElement(By elementLocator){
        waitVisibility(elementLocator);
        return driver.findElement(elementLocator).getText();
    }
    public String readAttributeValueAsText(By elementLocator, String attributeName){
        waitVisibility(elementLocator);
        return driver.findElement(elementLocator).getAttribute(attributeName);
    }
    public WebElement returnItemWithMaxPriceAsWebElement(By elementLocator, By itemPriceLocator){
        List<WebElement> itemList = driver.findElements(elementLocator);
        if(itemList.size() == 0){
            throw new SkipException("No available items in shop!");
        }
        String maxPriceAsString = itemList.get(0).findElement(itemPriceLocator).getText();
        double maxPriceAsDouble = Double.parseDouble(maxPriceAsString.replace("$", ""));
        WebElement maxPriceElement = itemList.get(0);
        for(int i = 1; i< itemList.size(); i++){
            String priceAsString = itemList.get(i).findElement(itemPriceLocator).getText();
            double priceAsNumber = Double.parseDouble(priceAsString.substring(1));
            if(priceAsNumber > maxPriceAsDouble){
                maxPriceAsDouble = priceAsNumber;
                maxPriceElement = itemList.get(i);
            }
        }
        return maxPriceElement;
    }
    public WebElement returnItemWithMinPriceAsWebElement(By elementLocator, By itemPriceLocator){
        List<WebElement> itemList = driver.findElements(elementLocator);
        if(itemList.size() == 0){
            throw new SkipException("No available items in shop!");
        }
        String minPriceAsString = itemList.get(0).findElement(itemPriceLocator).getText();
        double minPriceAsDouble = Double.parseDouble(minPriceAsString.replace("$", ""));
        WebElement minPriceElement = itemList.get(0);
        for(int i = 1; i< itemList.size(); i++){
            String priceAsString = itemList.get(i).findElement(itemPriceLocator).getText();
            double priceAsNumber = Double.parseDouble(priceAsString.substring(1));
            if(priceAsNumber < minPriceAsDouble){
                minPriceAsDouble = priceAsNumber;
                minPriceElement = itemList.get(i);
            }
        }
        return minPriceElement;
    }
    public WebElement selectRandomWebElement(By elementLocator){
        List<WebElement> itemList = driver.findElements(elementLocator);
        Random random = new Random();
        int size = itemList.size();
        int selection = random.nextInt(size-1);
        return itemList.get(selection);
    }
    public WebElement selectRandomItemAsWebElement(By elementLocator){
        List<WebElement> itemList = driver.findElements(elementLocator);
        Random random = new Random();
        int size = itemList.size();
        int selection = random.nextInt(size-1);
        return itemList.get(selection);
    }
    public void isElementDisplayed(By elementLocator){
        Assert.assertTrue(driver.findElement(elementLocator).isDisplayed());
    }
}
