package TestDesign.TestPages;

import SauceDemo.PageActions.CheckoutPageActions;
import SauceDemo.PageActions.LoginActions;
import SauceDemo.PageActions.ProductPageActions;
import SauceDemo.Pages.LoginPage;
import SauceDemo.Pages.ProductPage;
import SauceDemo.Pages.ShoppingCartPage;
import SauceDemo.utilities.PropertyManager;
import TestDesign.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class SubmitOrder extends BaseTest {
    // Define the test case for submitting an order
    @Test
    public void submitOrder() {
        // Initialize actions for logging in, product interactions, and checkout
        LoginActions loginMethods = new LoginActions(driver); // Login-related actions

        // Perform login with valid credentials retrieved from the PropertyManager
        loginMethods.performLogin(
                PropertyManager.getInstance().getValidUsername(), // Fetch username from properties
                PropertyManager.getInstance().getValidPassword()  // Fetch password from properties
        );
        ProductPageActions productPageActions = new ProductPageActions(driver);
        productPageActions.verifyAllProductsDisplayed();    // validate all products are displayed properly
        ProductPage productPage = new ProductPage(driver);    // Product page elements and actions

        // List of product names to add to the cart
        List<String> productNamesToAdd = Arrays.asList(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt");

        // Add the products to the cart dynamically
        try {
            productPage.addProductsToCartByNames(productNamesToAdd);
        } catch (NoSuchElementException e) {
            System.out.println("Error while adding products to the cart: " + e.getMessage());
            Assert.fail("Test failed due to missing products.");
        }
        // Navigate to the shopping cart page
        productPage.navigateToShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver); // Access shopping cart actions

        // Proceed to the checkout page from the shopping cart
        shoppingCartPage.navigateToCheckout();

        CheckoutPageActions checkoutPageActions = new CheckoutPageActions(driver); // Actions for the checkout process

        // Fill in checkout details and proceed
        checkoutPageActions.fillInCheckoutDetails("mo", "mo", "123"); // Enter checkout details (first name, last name, and Postal code)
        checkoutPageActions.clickFinishButton(); // Click the 'Finish' button to complete the order

        Assert.assertEquals(checkoutPageActions.getConfirmationMessage(), "Thank you for your order!");
        checkoutPageActions.clickBackToHomeBtn(); // Return to the home page after completing the order

        // Assert that the user is redirected back to the product page after checkout
        Assert.assertEquals(productPage.readTitle(), "Products");

        // Perform logout actions
        productPageActions.performLogout(); // Log out of the application

        // Verify that the logout redirects to the login page
        LoginPage loginPage = new LoginPage(driver); // Access login page elements
        loginPage.verifyLogout("Login"); // Verify that the login page is displayed after logout
    }
}
