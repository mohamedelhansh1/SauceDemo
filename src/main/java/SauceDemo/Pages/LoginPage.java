package SauceDemo.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends BasePage {
    WebDriver driver; // WebDriver instance to interact with the browser

    // Web elements located on the login page using the @FindBy annotation
    @FindBy(id = "user-name")
    WebElement usernameFieldBy; // The input field for the username

    @FindBy(id = "password")
    WebElement passwordFieldBy; // The input field for the password

    @FindBy(id = "login-button")
    WebElement loginButtonBy; // The login button

    @FindBy(css = "h3[data-test='error']")
    WebElement errorNotificationBy; // Error notification displayed on failed login attempt

    // Constructor to initialize the WebDriver and PageFactory elements
    public LoginPage(WebDriver driver) {
        super(driver); // Call to the parent class constructor (BasePage) to initialize WebDriver
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize the WebElements on the page
    }

    // Method to enter the username in the username input field
    public void writeUsername(String username) {
        writeText(usernameFieldBy, username); // Method from BasePage to write text in the username field
    }

    // Method to enter the password in the password input field
    public void writePassword(String password) {
        writeText(passwordFieldBy, password); // Method from BasePage to write text in the password field
    }

    // Method to click the login button to submit the login form
    public void clickLoginButton() {
        clickElement(loginButtonBy); // Method from BasePage to click the login button
    }

    // Method to verify the error notification message after a failed login
    public void verifyFailedLogin(String expectedText) {
        Assert.assertEquals(readTextFromElement(errorNotificationBy), expectedText);
        // Verifies that the error message matches the expected text
    }

    // Method to verify that the logout button is displayed with the correct text after logging out
    public void verifyLogout(String expectedText) {
        Assert.assertEquals(readAttributeValueAsText(loginButtonBy, "value"), expectedText);
        // Verifies that the logout button contains the expected text (like "Login")
    }

    // Method to get the error message text displayed after a failed login attempt
    public String getErrorMessage() {
        return errorNotificationBy.getText(); // Returns the text of the error notification
    }


}
