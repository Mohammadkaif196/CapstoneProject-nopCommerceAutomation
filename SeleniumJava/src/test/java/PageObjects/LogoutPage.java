package PageObjects;

import org.openqa.selenium.WebDriver;

public class LogoutPage extends BasePage {

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitleAfterLogout() {
        return driver.getTitle();
    }
}
