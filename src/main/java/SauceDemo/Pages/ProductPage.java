package SauceDemo.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.NoSuchElementException;

// ProductPage class manages interactions with the product page of the application.
public class ProductPage extends BasePage {

    WebDriver driver; // WebDriver instance to interact with the browser

    // Locators for elements on the product page using @FindBy annotations
    @FindBy(className = "title")
    WebElement titleLabelBy; // The title of the product page

    @FindBy(css = ".inventory_item_name")
    List<WebElement> productName; // List of product names displayed on the page

    // CSS selector to locate all product names
    By productsBy = By.cssSelector(".inventory_item");

    // CSS selector to locate a single product's name

    @FindBy(className = "inventory_item")
    List<WebElement> products; // List of product descriptions
    @FindBy(className = "inventory_item_price")
    List<WebElement> ProductPrice; // List of product prices

    @FindBy(className = "inventory_item_desc")
    List<WebElement> description; // List of product descriptions

    // Locators for the burger menu and the logout button in the menu
    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenuBy;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutButtonBy;

    // Locator for the shopping cart icon
    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCartIconBy;
    @FindBy(className = "inventory_item_desc")
    WebElement descriptionBy; // Locator for individual product description

    // Locator for the badge displaying the number of items in the shopping cart
    @FindBy(css = ".shopping_cart_badge")
    WebElement numberOfItemsBy;

    // Locator for the product filter dropdown container
    @FindBy(css = ".select_container")
    WebElement filterBy;

    // Locator for the filter dropdown itself
    @FindBy(css = ".product_sort_container")
    WebElement filterDDL;

    // Constructor to initialize the WebDriver and PageFactory elements
    public ProductPage(WebDriver driver) {
        super(driver); // Call to BasePage constructor to initialize WebDriver
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize PageFactory elements
    }

    // Method to read the title of the page
    public String readTitle() {
        return readTextFromElement(titleLabelBy); // Read text from title label
    }

    // Method to check if the page has loaded by verifying title visibility
    public boolean isLoaded() {
        return titleLabelBy.isDisplayed(); // Return true if the title is displayed
    }

    // Method to open the burger menu for navigation options
    public void openBurgerMenu() {
        clickElement(burgerMenuBy); // Click the burger menu button
    }

    // Method to click the logout button from the burger menu
    public void clickLogoutButton() {
        clickElement(logoutButtonBy); // Click the logout button
    }

    // Method to get the list of product names displayed on the page
    public List<WebElement> getProductName() {
        waitVisibility(productsBy); // Wait for the products to be visible
        return productName; // Return the list of product names
    }

    // Method to get the list of product prices
    public List<WebElement> getProductsPrice() {
        waitVisibility(productsBy); // Wait for the products to be visible
        return ProductPrice; // Return the list of product prices
    }

    // Method to get the list of product descriptions
    public List<WebElement> getProductDescription() {
        waitVisibility(descriptionBy); // Wait for the descriptions to be visible
        return description; // Return the list of product descriptions
    }

    // Method to add the backpack product to the cart
    public List<WebElement> getProductList() {
        waitVisibility(productsBy);
        return products;
    }
    public void addProductsToCartByNames(List<String> productNames) {
        for (String productName : productNames) {
            WebElement product = getProductList().stream()
                    .filter(item -> item.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Product not found: " + productName));
            product.findElement(By.cssSelector(".btn_inventory")).click();
            System.out.println("Added product to cart: " + productName);
        }
    }
    public void removeProductFromCartByName(String productName) {
        WebElement product = getProductList().stream()
                .filter(item -> item.findElement(By.cssSelector(".inventory_item_name")).getText().equals(productName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product not found for removal: " + productName));
        product.findElement(By.cssSelector(".btn_inventory")).click();
        System.out.println("Removed product from cart: " + productName);
    }

    // Method to get the number of items in the shopping cart
    public int getNumberOfItems() {
        waitVisibility(numberOfItemsBy); // Wait for the cart badge to be visible
        String itemCountText = readTextFromElement(numberOfItemsBy); // Get the item count as text
        return Integer.parseInt(itemCountText); // Convert the item count text to an integer and return
    }

    // Method to click the filter dropdown to change the sorting of products
    public ProductPage clickFilterDDL() {
        clickElement(filterBy); // Click the filter dropdown container
        return this; // Return the current instance of ProductPage for method chaining
    }

    // Method to select a sorting filter (low to high price)
    public void selectFilter() {
        Select select = new Select(filterDDL); // Create a Select instance for the filter dropdown
        select.selectByValue("lohi"); // Select the "low to high" price sorting option
    }

    // Method to navigate to the shopping cart page by clicking the cart icon
    public ShoppingCartPage navigateToShoppingCart() {
        clickElement(shoppingCartIconBy); // Click the shopping cart icon
        return new ShoppingCartPage(driver); // Return a new instance of the ShoppingCartPage
    }
}


