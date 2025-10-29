package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(linkText = "Register")
    private WebElement registerLink;

    @FindBy(linkText = "Log out")
    private WebElement logoutLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickRegister() {
        registerLink.click();
    }

    public boolean isLogoutDisplayed() {
        try {
            return logoutLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        logoutLink.click();
    }
}
