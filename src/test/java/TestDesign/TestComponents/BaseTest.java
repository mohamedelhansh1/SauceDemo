package TestDesign.TestComponents;

import SauceDemo.utilities.PropertyManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("Chrome") String browser) {
        switch (browser.toLowerCase()){
            case "chrome":
                startChrome();
                break;
            case "edge":
                startEdge();
                break;
            case "firefox":
                startFirefox();
                break;
            default:
                startChrome();
                System.out.println("Trazeni browser nije podrzan, pokrenut je Chrome!");
        }

        driver.get(PropertyManager.getInstance().getUrl());
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    public WebDriver getDriver(){
        return driver;
    }
    public void startChrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    public void startEdge(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }
    public void startFirefox(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
}


