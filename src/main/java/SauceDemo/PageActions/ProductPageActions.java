package SauceDemo.PageActions;

import SauceDemo.Pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class ProductPageActions extends ProductPage {
    private WebDriver driver; // WebDriver instance to interact with the browser
    // Get product name, price, and description lists from ProductPage
    List<WebElement> productName = getProductName();
    List<WebElement> ProductPrice = getProductsPrice();
    List<WebElement> ProductDesc = getProductDescription();

    // Constructor
    public ProductPageActions(WebDriver driver) {
        super(driver);  // Initialize the parent class (ProductPage)
        // Validate if the driver is initialized correctly
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver is null. Ensure it is initialized before passing.");
        }
        this.driver=driver;
    }

    // Perform logout by opening the burger menu and clicking the logout button
    public void performLogout() {
        openBurgerMenu();  // Open the burger menu
        clickLogoutButton();  // Click the logout button
    }

    // Print the total number of products available on the page
    public void returnNumOfProducts() {
        // Print the name of products
        System.out.println("Total Products: " + productName.stream().collect(Collectors.toList()).size());
    }

    // Print the names of all products available on the page
    public void returnAllProductName() {
        List<WebElement> productName = getProductName();  // Get the list of product names
        for (WebElement product : productName) {
            // Get and print the actual text (product name) from each WebElement
            String productNameText = product.getText();
            System.out.println("Product: " + productNameText);
        }
    }

    // Print the prices of all products available on the page
    public void returnAllProductPrice() {
        // Iterate through product prices and print each one
        for (WebElement price : ProductPrice) {
            // Get and print the actual price (product price) from each WebElement
            String productPriceText = price.getText();
            System.out.println("Product Price: " + productPriceText);
        }
    }

    // Print the descriptions of all products available on the page
    public void returnAllProductDesc() {
        for (WebElement desc : ProductDesc) {
            // Get and print the actual description (product description) from each WebElement
            String productDescText = desc.getText();
            System.out.println("Description: " + productDescText);
        }
    }
    public void verifyAllProductsDisplayed() {
        // Retrieve the lists of product names, prices, and descriptions
        List<WebElement> productNames = getProductName();
        List<WebElement> productPrices = getProductsPrice();
        List<WebElement> productDescriptions = getProductDescription();

        // Check if all lists have the same size
        if (productNames.size() != productPrices.size() || productNames.size() != productDescriptions.size()) {
            throw new AssertionError("Mismatch in the number of product names, prices, and descriptions.");
        }

        // Verify each product's details
        for (int i = 0; i < productNames.size(); i++) {
            WebElement productNameElement = productNames.get(i);
            WebElement productPriceElement = productPrices.get(i);
            WebElement productDescriptionElement = productDescriptions.get(i);

            // Validate that the elements are displayed
            Assert.assertTrue(productNameElement.isDisplayed(), "Product name is not displayed for product index " + i);
            Assert.assertTrue(productPriceElement.isDisplayed(), "Product price is not displayed for product index " + i);
            Assert.assertTrue(productDescriptionElement.isDisplayed(), "Product description is not displayed for product index " + i);

            // Print the product details to confirm
            System.out.println("Product " + (i + 1) + ":");
            System.out.println("Name: " + productNameElement.getText());
            System.out.println("Price: " + productPriceElement.getText());
            System.out.println("Description: " + productDescriptionElement.getText());
            System.out.println("-------------------------");
        }

        System.out.println("All products are displayed correctly!");
    }


    // Print detailed information for each product (name, price, description)
    public void returnProductsDetails() {
        // Ensure all lists have the same size (optional validation)
        if (productName.size() != ProductPrice.size() || productName.size() != ProductDesc.size()) {
            throw new IllegalStateException("Mismatch in product details list sizes");
        }

        // Loop through the product details and print them
        for (int i = 0; i < productName.size(); i++) {
            System.out.println("Product Details:");
            System.out.println("Name: " + productName.get(i).getText());  // Print the product name
            System.out.println("Price: " + ProductPrice.get(i).getText());  // Print the product price
            System.out.println("Description: " + ProductDesc.get(i).getText());  // Print the product description
            System.out.println("-------------------------");
        }
    }

    // Validate if product prices are sorted correctly (ascending order)
    public void validatePriceSorting() {
        // Step 1: Get product prices before sorting
        List<WebElement> productPriceElements = getProductsPrice();  // Get product price elements
        List<Double> pricesBeforeSorting = productPriceElements.stream()
                .map(priceElement -> Double.parseDouble(priceElement.getText().replace("$", "")))  // Convert price to double
                .collect(Collectors.toList());

        // Print the prices before sorting
        System.out.println("Prices Before Sorting: " + pricesBeforeSorting);

        // Step 2: Locally sort the prices in ascending order
        List<Double> locallySortedPrices = new ArrayList<>(pricesBeforeSorting);  // Make a copy of the prices list
        Collections.sort(locallySortedPrices);  // Sort the prices in ascending order
        System.out.println("Locally Sorted Prices: " + locallySortedPrices);

        // Step 3: Apply sorting using dropdown (e.g., sort by Price Low to High)
        clickFilterDDL();  // Click the filter dropdown to open the sorting options
        selectFilter();  // Select the sorting option (e.g., "Price (Low to High)")

        // Step 4: Get product prices after sorting from the UI
        List<WebElement> sortedProductPriceElements = getProductsPrice();  // Get the sorted price elements from UI
        List<Double> pricesAfterSorting = sortedProductPriceElements.stream()
                .map(priceElement -> Double.parseDouble(priceElement.getText().replace("$", "")))  // Convert price to double
                .collect(Collectors.toList());

        // Print the prices after sorting
        System.out.println("Prices After Sorting (From UI): " + pricesAfterSorting);

        // Step 5: Compare locally sorted prices with UI sorted prices
        // Assert that the prices after sorting are equal to the locally sorted prices
        Assert.assertEquals(pricesAfterSorting, locallySortedPrices, "The product prices are not sorted correctly!");
    }}