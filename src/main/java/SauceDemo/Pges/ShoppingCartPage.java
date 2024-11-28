package SauceDemo.Pges;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ShoppingCartPage extends BasePage{
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }
    By removeBackpackButtonBy = By.id("remove-sauce-labs-backpack");
    By itemNameBy = By.className("inventory_item_name");
    By removeButtonBy = By.xpath("//button[text()='Remove']");
    By checkoutButtonBy = By.id("checkout");
    public void verifyBackpackIsInCart(String expectedText){
        Assert.assertEquals(readTextFromElement(removeBackpackButtonBy), expectedText);
    }
    public String returnCartItemName(){
        return driver.findElement(itemNameBy).getText();
    }
    public void verifyItemIsInCart(){
        isElementDisplayed(removeButtonBy);
    }
    public CheckoutPage navigateToCheckout(){
        clickElement(checkoutButtonBy);
        return new CheckoutPage(driver);
    }
}
