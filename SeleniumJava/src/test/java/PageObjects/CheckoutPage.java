package PageObjects;

import org.openqa.selenium.By;

public class CheckoutPage {

    // Login
    public static final By LOGIN_LINK = By.xpath("(//div[@class='header-links']//a)[2]");
    public static final By EMAIL_INPUT = By.xpath("(//input[@type='email'])[1]");
    public static final By PASSWORD_INPUT = By.xpath("//div[@class='login-password']//input[1]");

    // Search
    public static final By SEARCH_BOX = By.xpath("//input[@placeholder='Search store']");
    public static final By SEARCH_RESULT_PRODUCT = By.xpath("(//a[@href='/nikon-d5500-dslr'])[1]");
    public static final By ADD_TO_CART_BUTTON = By.xpath("(//button[@class='button-1 add-to-cart-button'])[1]");
    public static final By SUCCESS_MESSAGE = By.xpath("//div[@class='bar-notification success']//p[1]");
    public static final By CLOSE_SUCCESS_MESSAGE = By.xpath("//span[@title='Close']");
    public static final By SHOPPING_CART_LINK = By.xpath("//span[text()='Shopping cart']");

    // Checkout
    public static final By TERMS_CHECKBOX = By.xpath("((//span[text()='$670.00'])[4]/following::input)[1]");
    public static final By CHECKOUT_BUTTON = By.xpath("//button[@class='button-1 checkout-button']");

    // Billing Address
    public static final By CITY_INPUT = By.xpath("//label[@for='BillingNewAddress_City']/following-sibling::input[1]");
    public static final By ADDRESS1_INPUT = By.xpath("(//label[text()='Address 1:']/following::input)[1]");
    public static final By ZIP_INPUT = By.xpath("(//label[text()='Zip / postal code:']/following::input)[1]");
    public static final By PHONE_INPUT = By.xpath("//input[@type='tel']");
    public static final By STATE_DROPDOWN = By.xpath("//select[@data-trigger='state-select']");
    public static final By STATE_ALABAMA = By.partialLinkText("Alabama");

    // Continue Buttons
    public static final By CONTINUE_BILLING = By.xpath("(//button[@name='save'])[1]");
    public static final By CONTINUE_SHIPPING = By.xpath("(//button[text()='Continue'])[3]");
    public static final By CONTINUE_PAYMENT_METHOD = By.xpath("//button[@class='button-1 payment-method-next-step-button']");
    public static final By CONTINUE_PAYMENT_INFO = By.xpath("(//button[text()='Continue'])[5]");
    public static final By CONFIRM_ORDER = By.xpath("//button[text()='Confirm']");

    // Final Success
    public static final By ORDER_SUCCESS_MESSAGE = By.xpath("//strong[text()='Your order has been successfully processed!']");
}