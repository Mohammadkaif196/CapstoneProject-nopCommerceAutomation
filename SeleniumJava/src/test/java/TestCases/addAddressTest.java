package TestCases;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class addAddressTest {

    WebDriver driver;

    // ✅ Make browser parameter optional (default = chrome)
    @Parameters("browser")
    @BeforeClass
    public void setup(@Optional("chrome") String browser) throws InterruptedException {
        System.out.println("===== Starting test on browser: " + browser + " =====");

        try {
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else {
                System.out.println("Browser not supported: " + browser);
                Assert.fail("Unsupported browser specified: " + browser);
            }

            driver.manage().window().maximize();
            driver.get("https://demo.nopcommerce.com/");
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Browser setup failed for: " + browser);
        }
    }

    @Test(priority = 1)
    public void loginTest() throws InterruptedException {
        System.out.println("=== Logging in to nopCommerce Demo ===");
        driver.findElement(By.className("ico-login")).click();
        Thread.sleep(1500);

        driver.findElement(By.id("Email")).sendKeys("test14321@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.id("Password")).sendKeys("Password@14321");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.className("ico-account")).isDisplayed(), "Login failed");
        System.out.println("✅ Login successful!");
    }

    @Test(priority = 2, dependsOnMethods = "loginTest")
    public void addAddressTest() throws InterruptedException {
        System.out.println("=== Adding a new address ===");
        driver.findElement(By.className("ico-account")).click();
        Thread.sleep(1500);

        driver.findElement(By.linkText("Addresses")).click();
        Thread.sleep(1500);

        driver.findElement(By.xpath("//button[text()='Add new']")).click();
        Thread.sleep(1500);

        driver.findElement(By.id("Address_FirstName")).sendKeys("John");
        Thread.sleep(800);
        driver.findElement(By.id("Address_LastName")).sendKeys("Doe");
        Thread.sleep(800);
        driver.findElement(By.id("Address_Email")).sendKeys("john.doe@example.com");
        Thread.sleep(800);
        driver.findElement(By.id("Address_Company")).sendKeys("Tech Corp");
        Thread.sleep(800);
        driver.findElement(By.id("Address_CountryId")).sendKeys("United States");
        Thread.sleep(800);
        driver.findElement(By.id("Address_City")).sendKeys("New York");
        Thread.sleep(800);
        driver.findElement(By.id("Address_Address1")).sendKeys("123 Main St");
        Thread.sleep(800);
        driver.findElement(By.id("Address_ZipPostalCode")).sendKeys("10001");
        Thread.sleep(800);
        driver.findElement(By.id("Address_PhoneNumber")).sendKeys("1234567890");
        Thread.sleep(800);

        driver.findElement(By.xpath("//button[text()='Save']")).click();
        Thread.sleep(2000);

        WebElement successMsg = driver.findElement(By.cssSelector(".bar-notification.success"));
        Assert.assertTrue(successMsg.isDisplayed(), "Address addition failed!");
        System.out.println("✅ Address added successfully: " + successMsg.getText());

        WebElement newAddress = driver.findElement(By.cssSelector("div.address-list"));
        Assert.assertTrue(newAddress.getText().contains("123 Main St"));
        System.out.println("📬 Verified: New address is displayed in the address list.");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(1500);
            driver.quit();
            System.out.println("===== Closing browser =====\n");
        }
    }
}
