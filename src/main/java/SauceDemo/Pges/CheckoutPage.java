package SauceDemo.Pges;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    By firstNameFieldBy = By.id("first-name");
    By lastNameFieldBy = By.id("last-name");
    By postalCodeFieldBy = By.id("postal-code");
    By continueButtonBy = By.id("continue");
    By totalPriceBy = By.className("summary_subtotal_label");

    public void writeFirstName(String firstName){
        writeText(firstNameFieldBy, firstName);
    }
    public void writeLastName(String lastName){
        writeText(lastNameFieldBy, lastName);
    }
    public void writePostalCode(String postalCode){
        writeText(postalCodeFieldBy, postalCode);
    }
    public void clickContinueButton(){
        clickElement(continueButtonBy);
    }
    public double returnTotalPrice(){
        return Double.parseDouble(driver.findElement(totalPriceBy).getText().substring(13));
    }
}
