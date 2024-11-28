package SauceDemo.Pges;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }
    By titleLabelBy = By.className("title");
    By burgerMenuBy = By.id("react-burger-menu-btn");
    By logoutButtonBy = By.id("logout_sidebar_link");
    By backpackAddToCartButtonBy = By.id("add-to-cart-sauce-labs-backpack");
    By shoppingCartIconBy = By.className("shopping_cart_link");
    By allItemsBy = By.className("inventory_item");
    By priceBy = By.className("inventory_item_price");
    By itemNameBy = By.className("inventory_item_name");
    By allAddToCartButtonsBy = By.xpath("//button[contains(@id,'add-to-cart')]");
    public String readTitle(){
        return readTextFromElement(titleLabelBy);
    }
    public void openBurgerMenu(){
        clickElement(burgerMenuBy);
    }
    public void clickLogoutButton(){
        clickElement(logoutButtonBy);
    }
    public HomePage addBackpackToCart(){
        clickElement(backpackAddToCartButtonBy);
        return this;
    }
    public ShoppingCartPage navigateToShoppingCart(){
        clickElement(shoppingCartIconBy);
        return new ShoppingCartPage(driver);
    }
    public void addMostExpensiveItemToCart(){
        returnItemWithMaxPriceAsWebElement(allItemsBy,priceBy).findElement(By.tagName("button")).click();
    }
    public void addCheapestItemToCart(){
        returnItemWithMinPriceAsWebElement(allItemsBy, priceBy).findElement(By.tagName("button")).click();
    }
    public double returnCheapestItemPrice(){
        WebElement element = returnItemWithMinPriceAsWebElement(allItemsBy, priceBy);
        String priceAsString = element.findElement(priceBy).getText();
        return Double.parseDouble(priceAsString.substring(1));
    }
    public String returnMostExpensiveItemName(){
        WebElement element = returnItemWithMaxPriceAsWebElement(allItemsBy,priceBy);
        return element.findElement(itemNameBy).getText();
    }
    public WebElement addRandomToCart(){
        WebElement element = selectRandomWebElement(allAddToCartButtonsBy);
        clickElement(element);
        return element;
    }
    public double pickRandomItem(){
        WebElement element = selectRandomItemAsWebElement(allItemsBy);
        WebElement button = element.findElement(By.tagName("button"));
        if(button.getText().equals("Remove")){
            System.out.println("Rekurzija");
            pickRandomItem();
        }else {
            button.click();
        }
        return Double.parseDouble(element.findElement(priceBy).getText().substring(1));
    }
}
