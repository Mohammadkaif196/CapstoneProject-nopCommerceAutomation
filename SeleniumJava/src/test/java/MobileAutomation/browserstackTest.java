// Package declaration - organizes your test classes
package MobileAutomation;

// IMPORT STATEMENTS - Bringing in required Java and Selenium classes
import java.net.URL;                          // To create a URL object for BrowserStack connection
import java.util.HashMap;                     // To store BrowserStack options in key-value format
import java.util.Map;                        // Interface for Map (HashMap implements this)
import org.openqa.selenium.WebDriver;         // Main interface to control the browser
import org.openqa.selenium.Alert;             // To handle JavaScript alerts/popups
import org.openqa.selenium.WebElement;        // Represents HTML elements on a webpage
import org.openqa.selenium.By;                // To locate elements using XPath, ID, etc.
import org.openqa.selenium.remote.DesiredCapabilities;  // To define browser/device settings
import org.openqa.selenium.remote.RemoteWebDriver;      // To run tests on remote machines (like BrowserStack)
import org.testng.annotations.AfterTest;     // Annotation to run code after all tests
import org.testng.annotations.BeforeMethod;  // Annotation to run code before each test method
import org.testng.annotations.Test;          // Marks a method as a test case
import org.openqa.selenium.support.ui.WebDriverWait;   // Explicit wait - waits for conditions
import org.openqa.selenium.JavascriptExecutor;          // To execute JavaScript in browser
import org.testng.Assert;                     // For assertions (verify expected vs actual)
import org.openqa.selenium.support.ui.ExpectedConditions; // Pre-built wait conditions
import java.time.Duration;                    // Modern way to define time durations (seconds, minutes)

public class browserstackTest {

    // Global variable: WebDriver instance to control the browser
    WebDriver driver;

    // BrowserStack credentials (your account details)
    public static final String USERNAME = "username";
    public static final String AUTOMATE_KEY = "password";
     
    // Full BrowserStack URL with username and key embedded
    // Format: http://username:accesskey@hub-cloud.browserstack.com/wd/hub
    public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    // WebDriverWait object to wait for elements/alerts intelligently
    WebDriverWait wait;

    // SETUP METHOD - Runs BEFORE every @Test method
    @BeforeMethod
    public void setUp() {
        try {
            // Step 1: Create a container for browser and device settings
            DesiredCapabilities caps = new DesiredCapabilities();

            // Step 2: Create a map to store BrowserStack-specific options
            Map<String, Object> bstackOptions = new HashMap<>();

            // Fill in device and environment details
            bstackOptions.put("deviceName", "iPhone 12 Pro");     // Which phone to use
            bstackOptions.put("osVersion", "14");                // iOS version
            bstackOptions.put("projectName", "Cognixia Cloud Project"); // Project name in BrowserStack dashboard
            bstackOptions.put("buildName", "OrangeHRM Test");    // Group of test runs
            bstackOptions.put("sessionName", "JS ALERTS CLOUD"); // Name of this specific test session
            bstackOptions.put("realMobile", "true");             // Use real device, not emulator

            // Step 3: Set browser and BrowserStack options
            caps.setCapability("browserName", "safari");         // Use Safari browser on iPhone
            caps.setCapability("bstack:options", bstackOptions); // Attach all the above options

            // Step 4: Connect to BrowserStack and launch Safari on iPhone 12 Pro
            driver = new RemoteWebDriver(new URL(URL), caps);

            // Step 5: Open the test website
            driver.get("https://the-internet.herokuapp.com/javascript_alerts");

            // Step 6: Initialize explicit wait (max 10 seconds) for better reliability
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        } catch (Exception e) {
            // If anything goes wrong (network, wrong credentials, etc.), print the error
            e.printStackTrace();
        }
    }

    // TEST CASE - Verifies that JS Prompt Alert works correctly
    @Test
    public void verifyJSPromptAlert() throws InterruptedException {

        // Print message to console for tracking test flow
        System.out.println("=== Waiting for and clicking the prompt alert button ===");

        // Wait up to 10 seconds until the 3rd button (JS Prompt) is clickable, then click it
        // XPath: Finds the 3rd button inside div with class 'example'
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@class='example']//button)[3]")))
                .click();

        System.out.println("=== Waiting for alert and sending text ===");

        // Wait until a JavaScript alert appears
        wait.until(ExpectedConditions.alertIsPresent());

        // Switch control from webpage to the alert popup
        Alert alert = driver.switchTo().alert();

        // Type text into the prompt alert
        alert.sendKeys("anuvrat verma");

        // Click "OK" to accept the alert
        alert.accept();

        System.out.println("=== Waiting for and verifying result text ===");

        // Wait for result message to appear and get its text
        String resultText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p[text()='You entered: anuvrat verma']"))
        ).getText();

        // ASSERTION: Check if actual text matches expected text
        // If not, test fails with custom message
        Assert.assertEquals(resultText, "You entered: anuvrat verma", 
                           "Verification failed! Result text did not match.");
    }

    // CLEANUP METHOD - Runs AFTER all tests in this class are done
    @AfterTest
    public void tearDown() {
        System.out.println("=== Closing browser ===");
        
        // Close the browser and end the session on BrowserStack
        // This also uploads results to BrowserStack dashboard
        driver.quit();
    }
}