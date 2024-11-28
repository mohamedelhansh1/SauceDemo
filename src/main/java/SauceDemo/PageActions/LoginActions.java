package SauceDemo.PageActions;

import SauceDemo.Pges.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginActions  extends LoginPage {

    public LoginActions(WebDriver driver) {
        super(driver);
    }
    public void performLogin(String username, String password){
        writeUsername(username);
        writePassword(password);
        clickLoginButton();
    }
}
