package TestDesign.TestPages;

import SauceDemo.PageActions.LoginActions;
import SauceDemo.PageActions.ProductPageActions;
import SauceDemo.Pages.LoginPage;
import SauceDemo.Pages.ProductPage;
import SauceDemo.utilities.PropertyManager;
import TestDesign.TestComponents.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LogoutTest extends BaseTest {

    // Define the test case for login with valid credentials
    @Test(testName = "Login with valid credentials")
    public void loginWithValidCredentials() {
        // Get WebDriver instance from BaseTest
        WebDriver driver = getDriver();

        // Initialize actions for logging in
        LoginActions loginMethods = new LoginActions(driver); // Login-related actions

        // Perform login with valid credentials retrieved from the PropertyManager
        loginMethods.performLogin(
                PropertyManager.getInstance().getValidUsername(), // Fetch username from properties
                PropertyManager.getInstance().getValidPassword()  // Fetch password from properties
        );

        // Verify successful login by checking the page title (assumes redirection to ProductPage)
        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(
                productPage.readTitle(),
                "Products",
                "The login was not successful or the expected page did not load."
        );

        // Perform logout actions
        ProductPageActions homePageMethods = new ProductPageActions(driver); // Actions for the product page
        homePageMethods.performLogout(); // Log out of the application

        // Verify that the user is redirected to the login page after logout
        LoginPage loginPage = new LoginPage(driver); // Access login page elements
        loginPage.verifyLogout("Login"); // Verify the login page is displayed after logout
    }
}



