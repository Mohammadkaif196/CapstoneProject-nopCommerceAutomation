package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(id = "gender-male")
    private WebElement genderMaleRadio;

    @FindBy(id = "FirstName")
    private WebElement firstNameField;

    @FindBy(id = "LastName")
    private WebElement lastNameField;

    @FindBy(id = "Email")
    private WebElement emailField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(css = ".result")
    private WebElement registrationResult;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // Helper: type slowly like a human
    private void typeSlowly(WebElement element, String text) {
        element.clear();
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            try {
                Thread.sleep(150); // delay between keystrokes (0.15s)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String password) {
        genderMaleRadio.click();
        typeSlowly(firstNameField, firstName);
        typeSlowly(lastNameField, lastName);
        typeSlowly(emailField, email);
        typeSlowly(passwordField, password);
        typeSlowly(confirmPasswordField, password);
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public String getRegistrationResult() {
        return registrationResult.getText();
    }
}
