package SauceDemo.Pages;

import SauceDemo.Pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ShoppingCartPage extends BasePage {
    // Constructor to initialize the ShoppingCartPage class
    public ShoppingCartPage(WebDriver driver) {
        super(driver); // Call the parent class constructor
    }

    // WebElement for the "Remove Backpack" button using @FindBy annotation
    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement removeBackpackButtonBy;

    // Locator for item names within the cart
    By itemNameBy = By.className("inventory_item_name");

    // Locator for "Remove" button for items in the cart
    By removeButtonBy = By.xpath("//button[text()='Remove']");

    // WebElement for the "Checkout" button
    @FindBy(id = "checkout")
    WebElement checkoutButtonBy;

    /**
     * Verifies if the backpack is in the cart by comparing the button text.
     * @param expectedText The expected text of the "Remove Backpack" button.
     */
    public void verifyBackpackIsInCart(String expectedText) {
        // Assert that the text of the button matches the expected text
        Assert.assertEquals(readTextFromElement(removeBackpackButtonBy), expectedText);
    }

    /**
     * Retrieves the name of an item in the cart.
     * @return The name of the cart item as a String.
     */
    public String returnCartItemName() {
        // Find the element representing the item's name and return its text
        return driver.findElement(itemNameBy).getText();
    }

    /**
     * Verifies if an item is displayed in the cart by checking the presence of its "Remove" button.
     */
    public void verifyItemIsInCart() {
        // Assert that the "Remove" button is displayed
        isElementDisplayed(removeButtonBy);
    }

    /**
     * Navigates to the checkout page by clicking the "Checkout" button.
     * @return A new instance of the CheckoutPage class.
     */
    public CheckoutPage navigateToCheckout() {
        // Click the "Checkout" button
        clickElement(checkoutButtonBy);
        // Return an instance of the CheckoutPage for further interactions
        return new CheckoutPage(driver);
    }
}


