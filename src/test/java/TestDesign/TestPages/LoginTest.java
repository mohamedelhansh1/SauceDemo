package TestDesign.TestPages;

import SauceDemo.PageActions.LoginActions;
import SauceDemo.Pages.LoginPage;
import SauceDemo.Pages.ProductPage;
import SauceDemo.utilities.PropertyManager;
import TestDesign.TestComponents.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    // Test case for login with valid credentials
    @Test(testName = "Login with valid credentials")
    public void loginWithValidCredentials() {
        // Get WebDriver instance from BaseTest
        WebDriver driver = getDriver();

        // Initialize LoginActions for handling login-related operations
        LoginActions loginMethods = new LoginActions(driver);

        // Perform login with valid credentials retrieved from the PropertyManager
        loginMethods.performLogin(
                PropertyManager.getInstance().getValidUsername(), // Fetch valid username
                PropertyManager.getInstance().getValidPassword()  // Fetch valid password
        );

        // Verify successful login by checking the page title (redirected to ProductPage)
        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(
                productPage.readTitle(),
                "Products",
                "The login was not successful, or the expected page did not load."
        );
    }

    // Test case for login with invalid credentials
    @Test(testName = "Login with invalid credentials")
    public void loginWithInvalidCredentials() {
        // Get WebDriver instance from BaseTest
        WebDriver driver = getDriver();

        // Initialize LoginActions for handling login-related operations
        LoginActions loginMethods = new LoginActions(driver);

        // Perform login with invalid credentials retrieved from the PropertyManager
        loginMethods.performLogin(
                PropertyManager.getInstance().getInvalidUsername(), // Fetch invalid username
                PropertyManager.getInstance().getInvalidPassword()  // Fetch invalid password
        );

        // Verify that an appropriate error message is displayed
        LoginPage loginPage = new LoginPage(driver); // Access login page elements
        String expectedErrorMessage =
                "Epic sadface: Username and password do not match any user in this service"; // Expected error message

        // Assert that the actual error message matches the expected error message
        loginPage.verifyFailedLogin(expectedErrorMessage);
    }
}

