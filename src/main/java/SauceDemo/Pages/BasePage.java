package SauceDemo.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;  // WebDriver instance used to interact with the browser
    protected WebDriverWait wait;  // WebDriverWait instance used to handle explicit waits

    // Constructor to initialize WebDriver and WebDriverWait with a default timeout
    public BasePage(WebDriver driver) {
        // Ensure WebDriver is initialized properly before passing it to the constructor
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver is null. Please initialize it before passing.");
        }
        this.driver = driver;
        // Initialize the WebDriverWait with a 10-second timeout for waiting elements
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Initialize WebElements annotated with @FindBy
        PageFactory.initElements(driver, this);
    }

    // Wait for visibility of an element located by a 'By' locator
    public void waitVisibility(By elementLocator) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementLocator));  // Wait for the element to be visible
    }

    // Wait for visibility of a specific WebElement
    public void waitVisibility(WebElement elementToWait) {
        wait.until(ExpectedConditions.visibilityOf(elementToWait));  // Wait for the WebElement to be visible
    }

    // Write text into a text field (WebElement version)
    public void writeText(WebElement elementLocator, String text) {
        waitVisibility(elementLocator);  // Ensure the element is visible before interacting
        elementLocator.sendKeys(text);  // Write the given text into the input field
    }

    // Click an element located by a 'By' locator
    public void clickElement(By elementLocator) {
        waitVisibility(elementLocator);  // Ensure the element is visible before clicking
        driver.findElement(elementLocator).click();  // Perform the click action on the element
    }

    // Click a WebElement directly (WebElement version)
    public void clickElement(WebElement elementToClick) {
        waitVisibility(elementToClick);  // Ensure the element is visible before clicking
        elementToClick.click();  // Perform the click action on the WebElement
    }

    // Read the text of a WebElement
    public String readTextFromElement(WebElement element) {
        waitVisibility(element);  // Ensure the element is visible before reading its text
        return element.getText();  // Return the text content of the element
    }

    // Read the value of an attribute from a WebElement (e.g., "value", "href")
    public String readAttributeValueAsText(WebElement element, String attributeName) {
        waitVisibility(element);  // Ensure the element is visible before reading its attribute
        return element.getAttribute(attributeName);  // Return the value of the specified attribute
    }

    // Check if an element is displayed using its 'By' locator
    public void isElementDisplayed(By elementLocator) {
        waitVisibility(elementLocator);  // Ensure the element is visible before checking
        Assert.assertTrue(driver.findElement(elementLocator).isDisplayed(), "Element is not displayed: " + elementLocator.toString());
    }

    // Helper method to extract price from a string (e.g., "$19.99" -> 19.99)
    private double extractPrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", "").trim());  // Remove the '$' and convert the remaining string to a double
    }
}