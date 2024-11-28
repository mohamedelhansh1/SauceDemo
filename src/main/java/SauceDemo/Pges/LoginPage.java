package SauceDemo.Pges;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    By usernameFieldBy = By.id("user-name");
    By passwordFieldBy = By.id("password");
    By loginButtonBy = By.id("login-button");
    By errorNotificationBy = By.xpath("//h3[@data-test='error']");

    public void writeUsername(String username){
        writeText(usernameFieldBy, username);
    }
    public void writePassword(String password){
        writeText(passwordFieldBy, password);
    }
    public void clickLoginButton(){
        clickElement(loginButtonBy);
    }
    public void verifyFailedLogin(String expectedText){
        Assert.assertEquals(readTextFromElement(errorNotificationBy), expectedText);
    }
    public void verifyLogout(String expectedText){
        Assert.assertEquals(readAttributeValueAsText(loginButtonBy, "value"), expectedText);
    }
}
