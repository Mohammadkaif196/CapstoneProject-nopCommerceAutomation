package TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import PageObjects.HomePage;
import PageObjects.RegisterPage;
import PageObjects.LogoutPage;

public class RegisterLogoutTest {

    WebDriver driver;
    PageObjects.HomePage homePage;
    PageObjects.RegisterPage registerPage;
    PageObjects.LogoutPage logoutPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        System.out.println("Browser launched and opened nopCommerce demo site");
    }

    @Test
    public void testRegisterAndLogout() throws InterruptedException {
        homePage = new PageObjects.HomePage(driver);
        registerPage = new PageObjects.RegisterPage(driver);
        logoutPage = new PageObjects.LogoutPage(driver);

        // Step 1: Go to Register page
        System.out.println("➡️ Navigating to Register Page...");
        homePage.clickRegister();
        Thread.sleep(1000);

        // Step 2: Fill out registration form
        String randomEmail = "user" + RandomStringUtils.randomNumeric(4) + "@test.com";
        String password = "Password123";

        System.out.println("📝 Filling registration form...");
        registerPage.fillRegistrationForm("John", "Doe", randomEmail, password);
        Thread.sleep(1000);

        // Step 3: Submit registration
        System.out.println("🚀 Submitting registration form...");
        registerPage.clickRegisterButton();

        // Wait for result to load
        Thread.sleep(2000);

        // Step 4: Verify successful registration
        String result = registerPage.getRegistrationResult();
        System.out.println("📩 Registration message received: " + result);
        Assert.assertEquals(result, "Your registration completed", "❌ Registration failed!");
        System.out.println("✅ Registration verified successfully!");

        // Step 5: Logout
        System.out.println("➡️ Logging out...");
        homePage.clickLogout();
        Thread.sleep(2000);

        // Step 6: Verify redirection to home page after logout
        String title = driver.getTitle();
        System.out.println("🏠 Current page title after logout: " + title);
        Assert.assertTrue(title.contains("nopCommerce demo store"), "❌ Logout redirection failed!");
        System.out.println("✅ Logout successful and redirected to home page!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println("🧹 Closing browser...");
            driver.quit();
            System.out.println("🔴 Test completed and browser closed.");
        }
    }
}

