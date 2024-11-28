package SauceDemo.PageActions;

import SauceDemo.Pges.CheckoutPage;
import org.openqa.selenium.WebDriver;

public class CheckoutPageActions extends CheckoutPage {
    public CheckoutPageActions(WebDriver driver) {
        super(driver);
    }
    public void fillInCheckoutDetails(String firstName, String lastName, String postalCode){
        writeFirstName(firstName);
        writeLastName(lastName);
        writePostalCode(postalCode);
        clickContinueButton();
    }
}
