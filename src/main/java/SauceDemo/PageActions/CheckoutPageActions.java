package SauceDemo.PageActions;

import SauceDemo.Pages.CheckoutPage;
import org.openqa.selenium.WebDriver;

public class CheckoutPageActions extends CheckoutPage {

    public CheckoutPageActions(WebDriver driver) {
        super(driver);
    }

    /**
     * Fills in the checkout details including first name, last name, and postal code, then proceeds.
     * This method uses the existing methods from the `CheckoutPage` class to enter the details and click the continue button.
     *
     * @param firstName The first name to be entered in the checkout form.
     * @param lastName The last name to be entered in the checkout form.
     * @param postalCode The postal code to be entered in the checkout form.
     */
    public void fillInCheckoutDetails(String firstName, String lastName, String postalCode) {
        // Fill in the first name
        writeFirstName(firstName);

        // Fill in the last name
        writeLastName(lastName);

        // Fill in the postal code
        writePostalCode(postalCode);

        // Click the continue button to proceed to the next step
        clickContinueButton();
    }

}
