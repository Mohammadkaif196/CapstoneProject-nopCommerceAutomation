package MobileAutomation;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class WikipediaAppTests {

    private AppiumDriver driver;
    private WebDriverWait wait;

    public static final String USERNAME = "your username";
    public static final String ACCESS_KEY = "your password";
    public static final String APP_URL = "bs://{your_url}";
    public static final String HUB_URL = "http://hub.browserstack.com/wd/hub";

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", USERNAME);
        bstackOptions.put("accessKey", ACCESS_KEY);
        bstackOptions.put("projectName", "Wikipedia App Automation");
        bstackOptions.put("buildName", "Build_03");
        bstackOptions.put("sessionName", "Wikipedia Tests");
        bstackOptions.put("deviceName", "Google Pixel 7");
        bstackOptions.put("osVersion", "13.0");

        caps.setCapability("appium:app", APP_URL);
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("bstack:options", bstackOptions);

        driver = new AppiumDriver(new URL(HUB_URL), caps);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private void captureScreenshot(String testName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File("screenshots/" + testName + ".png"));
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot for " + testName);
        }
    }
    @Test(priority = 1)
    public void verifyTextJohnBullockClarkPresent() {
        try {
            By textLocator = By.xpath("//*[contains(@text,'John Bullock Clark')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
            Assert.assertTrue(driver.findElement(textLocator).isDisplayed(),
                    "\"John Bullock Clark\" text is not displayed on the home screen!");
            System.out.println("✔ \"John Bullock Clark\" text is visible on the home screen.");
        } catch (Exception e) {
            captureScreenshot("verifyTextJohnBullockClarkPresent");
            Assert.fail("Test failed in verifyTextJohnBullockClarkPresent: " + e.getMessage());
        }
    }


    @Test(priority = 2)
    public void verifySearchFieldPresent() {
        try {
            By searchField = By.xpath("//*[@text='Search Wikipedia']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
            Assert.assertTrue(driver.findElement(searchField).isDisplayed(), "Search field is not displayed!");
            System.out.println("✔ Search field is visible on the home screen.");
        } catch (Exception e) {
            captureScreenshot("verifySearchFieldPresent");
            Assert.fail("Test failed in verifySearchFieldPresent: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void searchForTerm() {
        try {
            By searchElement = By.xpath("//*[@text='Search Wikipedia']");
            wait.until(ExpectedConditions.elementToBeClickable(searchElement)).click();

            By searchInput = By.id("org.wikipedia.alpha:id/search_src_text");
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).sendKeys("Appium");

            By searchResults = By.id("org.wikipedia.alpha:id/page_list_item_title");
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));

            int resultCount = driver.findElements(searchResults).size();
            Assert.assertTrue(resultCount > 0, "No search results found!");
            System.out.println("✔ Search results displayed successfully! Found: " + resultCount);
        } catch (Exception e) {
            captureScreenshot("searchForTerm");
            Assert.fail("Test failed in searchForTerm: " + e.getMessage());
        }
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
