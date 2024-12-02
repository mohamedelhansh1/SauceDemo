package TestDesign.TestPages;

import SauceDemo.PageActions.LoginActions;
import SauceDemo.PageActions.ProductPageActions;
import SauceDemo.Pages.ProductPage;
import SauceDemo.utilities.PropertyManager;
import TestDesign.TestComponents.BaseTest;
import org.testng.annotations.Test;

public class ProductPageTest extends BaseTest {

    // Define the test case for retrieving and validating the product list
    @Test
    public void getAllProductsList() {

        // Initialize actions for logging in and interacting with the product page
        LoginActions loginMethods = new LoginActions(driver); // Login-related actions
        ProductPage productPage = new ProductPage(driver);    // Product page elements and actions

        // Perform login with valid credentials retrieved from the PropertyManager
        loginMethods.performLogin(
                PropertyManager.getInstance().getValidUsername(), // Fetch username from properties
                PropertyManager.getInstance().getValidPassword()  // Fetch password from properties
        );

        // Initialize ProductPageActions for advanced product interactions
        ProductPageActions productPageActions = new ProductPageActions(driver);

        // Retrieve and log the total number of products displayed on the page
        productPageActions.returnNumOfProducts();

        productPageActions.verifyAllProductsDisplayed();

        // Retrieve and log detailed information about each product (e.g., name, price, description)
        productPageActions.returnProductsDetails();

        // Validate that products are sorted correctly by price (low to high or high to low)
        productPageActions.validatePriceSorting();
    }
}



