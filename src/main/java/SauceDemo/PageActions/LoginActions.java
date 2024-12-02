package SauceDemo.PageActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginActions {
    private WebDriver driver; // WebDriver instance to interact with the browser

    // Locators for login elements on the login page
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    /**
     * Constructor for LoginActions.
     * Initializes the WebDriver instance used for performing login actions.
     * Throws an exception if the driver is null.
     */
    public LoginActions(WebDriver driver) {
        // Validate if the driver is initialized correctly
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver is null. Ensure it is initialized before passing.");
        }
        this.driver = driver; // Assign the provided WebDriver to the class variable
    }

    /**
     * Performs the login action using the provided username and password.
     * @param username The username for login.
     * @param password The password for login.
     */
    public void performLogin(String username, String password) {
        // Log the username for debugging purposes
        System.out.println("Performing login with username: " + username);

        // Find the username field and enter the username
        driver.findElement(usernameField).sendKeys(username);

        // Find the password field and enter the password
        driver.findElement(passwordField).sendKeys(password);

        // Find the login button and click it to submit the login form
        driver.findElement(loginButton).click();

        // Log success for debugging purposes
        System.out.println("Login action performed successfully.");
    }
}
