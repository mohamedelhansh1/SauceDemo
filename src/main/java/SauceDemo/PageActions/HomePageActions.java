package SauceDemo.PageActions;

import SauceDemo.Pges.HomePage;
import org.openqa.selenium.WebDriver;

public class HomePageActions extends HomePage {
    public HomePageActions(WebDriver driver) {
        super(driver);
    }
    public void performLogout(){
        openBurgerMenu();
        clickLogoutButton();
    }
}
