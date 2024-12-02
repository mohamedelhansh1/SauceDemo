package SauceDemo.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {
    WebDriver driver; // WebDriver instance to interact with the browser

    // Web elements located on the checkout page using the @FindBy annotation
    @FindBy(id = "first-name")
    WebElement firstNameFieldBy; // The input field for the first name

    @FindBy(css = ".complete-header")
    WebElement confirmationMessage;
    @FindBy(id = "last-name")
    WebElement lastNameFieldBy; // The input field for the last name

    @FindBy(id = "postal-code")
    WebElement postalCodeFieldBy; // The input field for the postal code

    @FindBy(id = "continue")
    WebElement continueButtonBy; // The continue button to proceed to the next step

    @FindBy(className = "summary_subtotal_label")
    WebElement totalPriceBy; // The label showing the total price of the items

    @FindBy(id = "finish")
    WebElement finishButton; // The finish button to complete the checkout process

    @FindBy(id = "back-to-products")
    WebElement backToHomeBtn; // The button to navigate back to the product page

    // Constructor to initialize the WebDriver and PageFactory elements
    public CheckoutPage(WebDriver driver) {
        super(driver); // Call to the parent class constructor (BasePage) to initialize WebDriver
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize the WebElements on the page
    }

    // Method to enter the first name in the respective input field
    public void writeFirstName(String firstName) {
        writeText(firstNameFieldBy, firstName); // Method from BasePage to write text in the first name field
    }

    // Method to enter the last name in the respective input field
    public void writeLastName(String lastName) {
        writeText(lastNameFieldBy, lastName); // Method from BasePage to write text in the last name field
    }

    // Method to enter the postal code in the respective input field
    public void writePostalCode(String postalCode) {
        writeText(postalCodeFieldBy, postalCode); // Method from BasePage to write text in the postal code field
    }

    // Method to click the continue button and proceed to the next step
    public void clickContinueButton() {
        clickElement(continueButtonBy); // Method from BasePage to click the continue button
    }

    // Method to return the total price as a double value
    public double returnTotalPrice() {
        // Extracts the numeric value from the total price label and converts it to double
        return Double.parseDouble(totalPriceBy.getText().substring(13));
    }

    // Method to click the finish button to complete the checkout process
    public void clickFinishButton() {
        clickElement(finishButton); // Method from BasePage to click the finish button
    }

    // Method to click the "Back to Products" button to navigate back to the product page
    public void clickBackToHomeBtn() {
        clickElement(backToHomeBtn); // Method from BasePage to click the "Back to Products" button
    }
    public String getConfirmationMessage(){
        System.out.println("Confirmation Message:"+ readTextFromElement(confirmationMessage));
        return   readTextFromElement(confirmationMessage);
    }
}
