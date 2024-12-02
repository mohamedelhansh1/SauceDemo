package TestDesign.TestComponents;

import SauceDemo.utilities.PropertyManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    // Declare the WebDriver instance as protected for accessibility in subclasses
    protected WebDriver driver;

    // Accept the "browser" parameter to allow cross-browser testing
    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("Chrome") String browser) {
        // Initialize WebDriver based on the specified browser
        switch (browser.toLowerCase()) {
            case "chrome":
                startChrome(); // Setup Chrome browser
                break;
            case "edge":
                startEdge(); // Setup Edge browser
                break;
            case "firefox":
                startFirefox(); // Setup Firefox browser
                break;
            default:
                startChrome(); // Default to Chrome if no valid browser is specified
                System.out.println("Unsupported browser specified. Defaulting to Chrome.");
        }

        // Navigate to the base URL specified in the PropertyManager
        driver.get(PropertyManager.getInstance().getUrl());
    }

    // Optional teardown method to clean up WebDriver after tests
    // Uncomment this block if you want WebDriver to quit after each test
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Quit the WebDriver to close the browser
        }
    }

    // Getter method for WebDriver to ensure it is initialized before use
    public WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException(
                    "Driver has not been initialized. Please check the setup method."
            );
        }
        return driver; // Return the initialized WebDriver instance
    }

    // Private method to setup Chrome browser
    private void startChrome() {
        WebDriverManager.chromedriver().setup(); // Setup ChromeDriver
        driver = new ChromeDriver(); // Instantiate ChromeDriver
        driver.manage().window().maximize(); // Maximize the browser window
    }

    // Private method to setup Edge browser
    private void startEdge() {
        WebDriverManager.edgedriver().setup(); // Setup EdgeDriver
        driver = new EdgeDriver(); // Instantiate EdgeDriver
        driver.manage().window().maximize(); // Maximize the browser window
    }

    // Private method to setup Firefox browser
    private void startFirefox() {
        WebDriverManager.firefoxdriver().setup(); // Setup FirefoxDriver
        driver = new FirefoxDriver(); // Instantiate FirefoxDriver
        driver.manage().window().maximize(); // Maximize the browser window
    }
}
