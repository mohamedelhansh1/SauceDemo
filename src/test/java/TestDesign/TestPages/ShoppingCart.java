package TestDesign.TestPages;

import SauceDemo.PageActions.LoginActions;
import SauceDemo.Pages.ProductPage;
import SauceDemo.utilities.PropertyManager;
import TestDesign.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ShoppingCart extends BaseTest {
    // Declare a variable to store the expected number of items in the cart
    int expectedItemCount;

    // Define the test case for adding products to the shopping cart
    @Test
    public void addProductToCart() throws InterruptedException {
        // Initialize actions for logging in and interacting with the product page
        LoginActions loginMethods = new LoginActions(driver); // Login-related actions


        // Perform login with valid credentials retrieved from the PropertyManager
        loginMethods.performLogin(
                PropertyManager.getInstance().getValidUsername(), // Fetch username from properties
                PropertyManager.getInstance().getValidPassword()  // Fetch password from properties
        );
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


        // Retrieve the number of items in the cart after adding products
        int actualItemCount = productPage.getNumberOfItems();

        // Define the expected number of items in the cart after adding products
        expectedItemCount = productNamesToAdd.size();

        // Assert that the actual item count matches the expected item count
        Assert.assertEquals(
                actualItemCount,
                expectedItemCount,
                "The cart item count does not match the expected value after adding products."
        );

        // Remove one product (e.g., "Sauce Labs Backpack") from the shopping cart
        productPage.removeProductFromCartByName("Sauce Labs Backpack");

        // Update the actual item count after removing a product
        actualItemCount = productPage.getNumberOfItems();

        // Define the new expected number of items in the cart
        expectedItemCount = productNamesToAdd.size() - 1;

        // Assert that the updated item count matches the new expected value
        Assert.assertEquals(
                actualItemCount,
                expectedItemCount,
                "The cart item count does not match the expected value after removing a product."
        );
    }
}
