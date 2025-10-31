package TestCases;

import io.github.bonigarcia.wdm.WebDriverManager; // Manages ChromeDriver automatically (downloads if missing)
import org.openqa.selenium.*;                     // Core Selenium classes: WebDriver, WebElement, By, etc.
import org.openqa.selenium.chrome.ChromeDriver;   // Launches Chrome browser locally
import org.openqa.selenium.support.ui.*;          // For Select (dropdowns) and other UI support
import org.testng.annotations.*;                  // Import TestNG annotations like @Test, @BeforeMethod
import PageObjects.CheckoutPage;                  // Your Page Object class containing element locators
import org.slf4j.Logger;                         // Logging interface
import org.slf4j.LoggerFactory;                  // Factory to create Logger instances
import java.time.Duration;                       // Modern way to define wait durations (e.g., 15 seconds)
import static org.testng.Assert.*;               // Static import for assertEquals, assertTrue, etc.

public class purchaseTest {

    // Global variable: Controls the browser during the test
    private WebDriver driver;

    // Explicit wait object: Waits intelligently for elements instead of fixed sleep
    private WebDriverWait wait;

    // Logger to print structured logs (better than System.out.println)
    // Logger name = current class (purchase.class)
    private static final Logger log = LoggerFactory.getLogger(purchaseTest.class);

    // SETUP METHOD - Runs BEFORE every @Test method
    @BeforeMethod
    public void setup() {
        // Launch Chrome browser using ChromeDriver
        driver = new ChromeDriver();

        // Maximize browser window for better visibility and element access
        driver.manage().window().maximize();

        // Open the nopCommerce demo website
        driver.get("https://demo.nopcommerce.com/");

        // Initialize explicit wait: max 15 seconds for any condition
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait until page title contains "nopCommerce demo store." to confirm page loaded
        wait.until(ExpectedConditions.titleContains("nopCommerce demo store."));

        // Get current page title for logging and verification
        String title = driver.getTitle();

        // Log success message with actual title
        log.info("Successfully connected! Page title: {}", title);
    }

    // MAIN TEST CASE - End-to-end purchase of a DSLR camera
    @Test(description = "End-to-End Checkout: DSLR Purchase")
    public void endToEndCheckout() {

        // 1. LOGIN TO THE WEBSITE
        clickAndWait(CheckoutPage.LOGIN_LINK); // Click "Log in" link

        // Enter email in login form
        sendKeysAndLog(CheckoutPage.EMAIL_INPUT, "123@gmail.com");

        // Enter password
        sendKeysAndLog(CheckoutPage.PASSWORD_INPUT, "123@456@789");

        // Click the login button using XPath
        clickAndWait(By.xpath("//button[@class='button-1 login-button']"));

        // 2. SEARCH FOR "DSLR"
        sendKeysAndLog(CheckoutPage.SEARCH_BOX, "dslr"); // Type "dslr" in search box

        // Press ENTER key to submit search
        driver.findElement(CheckoutPage.SEARCH_BOX).sendKeys(Keys.ENTER);

        // Wait until the product appears in search results
        wait.until(ExpectedConditions.presenceOfElementLocated(CheckoutPage.SEARCH_RESULT_PRODUCT));

        // Locate the product link (Nikon D5500 DSLR)
        WebElement productLink = driver.findElement(CheckoutPage.SEARCH_RESULT_PRODUCT);

        // Scroll the product into view using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productLink);
        log.info("Scrolled to Nikon D5500");

        // Wait until the product is clickable
        wait.until(ExpectedConditions.elementToBeClickable(productLink));

        // Click on the product to open its details page
        productLink.click();
        log.info("Clicked Nikon D5500 DSLR");

        // 3. ADD TO CART
        clickAndWait(CheckoutPage.ADD_TO_CART_BUTTON); // Click "Add to cart" button

        // Wait for success message bar to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutPage.SUCCESS_MESSAGE));

        // Get the success message text
        String successMsg = driver.findElement(CheckoutPage.SUCCESS_MESSAGE).getText();

        // VERIFY: Check if correct message appears after adding to cart
        assertEquals(successMsg, "The product has been added to your shopping cart",
                "Verification failed for Add to Cart message.");
        log.info("Success: {}", successMsg);

        // Close the success notification bar
        clickAndWait(CheckoutPage.CLOSE_SUCCESS_MESSAGE);

        // 4. GO TO SHOPPING CART
        clickAndWait(CheckoutPage.SHOPPING_CART_LINK); // Click "Shopping cart" link

        // 5. PROCEED TO CHECKOUT
        clickAndWait(CheckoutPage.TERMS_CHECKBOX);     // Accept terms of service
        clickAndWait(CheckoutPage.CHECKOUT_BUTTON);   // Click "Checkout"

        // 6. FILL BILLING ADDRESS
        sendKeysAndLog(CheckoutPage.CITY_INPUT, "new york");
        sendKeysAndLog(CheckoutPage.ADDRESS1_INPUT, "bigbuilding 25, central road, Opp. big lake");
        sendKeysAndLog(CheckoutPage.ZIP_INPUT, "123456");
        sendKeysAndLog(CheckoutPage.PHONE_INPUT, "1234567890");

        // Select "Alabama" from state dropdown
        new Select(driver.findElement(CheckoutPage.STATE_DROPDOWN)).selectByVisibleText("Alabama");
        log.info("Selected state: Alabama");

        // 7. CLICK ALL "CONTINUE" BUTTONS
        clickAndWait(CheckoutPage.CONTINUE_BILLING);         // Confirm billing
        clickAndWait(CheckoutPage.CONTINUE_SHIPPING);        // Confirm shipping address
        clickAndWait(CheckoutPage.CONTINUE_PAYMENT_METHOD);  // Choose payment method
        clickAndWait(CheckoutPage.CONTINUE_PAYMENT_INFO);    // Confirm payment info

        // 8. FINAL CONFIRMATION
        clickAndWait(CheckoutPage.CONFIRM_ORDER); // Click "Confirm" to place order

        // 9. VERIFY ORDER SUCCESS
        wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutPage.ORDER_SUCCESS_MESSAGE));
        String finalMsg = driver.findElement(CheckoutPage.ORDER_SUCCESS_MESSAGE).getText();

        // FINAL ASSERTION: Confirm order was placed
        assertEquals(finalMsg, "Your order has been successfully processed!",
                "Verification failed for Order Success message.");
        log.info("Order placed successfully! {}", finalMsg);
    }

    // CLEANUP METHOD - Runs AFTER every @Test method
    @AfterMethod
    public void tearDown() {
        // Check if driver was initialized
        if (driver != null) {
            log.info("Closing browser...");
            driver.quit(); // Closes browser and ends WebDriver session
        }
    }

    // HELPER METHOD: Click an element with explicit wait
    private void clickAndWait(By locator) {
        // Wait until element is clickable (visible + enabled)
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click(); // Perform click
        log.info("Clicked: {}", locator); // Log what was clicked
    }

    // HELPER METHOD: Type text into an input field with wait and logging
    private void sendKeysAndLog(By locator, String text) {
        // Wait until input field is visible
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear(); // Clear any existing text
        element.sendKeys(text); // Type the new text
        log.info("Entered in {}: {}", locator, text); // Log action
    }
}