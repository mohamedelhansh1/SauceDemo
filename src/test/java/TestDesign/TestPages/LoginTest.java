package TestDesign.TestPages;

import SauceDemo.PageActions.LoginActions;
import SauceDemo.Pges.HomePage;
import SauceDemo.utilities.PropertyManager;
import TestDesign.TestComponents.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    WebDriver driver;
    @Test(testName = "Login with valid credentials.")
    public void loginTest(){
        LoginActions loginMethods = new LoginActions(driver);
        loginMethods.performLogin(PropertyManager.getInstance().getValidUsername(), PropertyManager.getInstance().getValidPassword());

        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.readTitle(), "Product");
    }

}
