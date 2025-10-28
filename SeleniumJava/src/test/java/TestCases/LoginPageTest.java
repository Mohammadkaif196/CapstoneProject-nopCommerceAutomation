package TestCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.LoginPage;
import utils.ExcelUtils;

public class LoginPageTest extends BaseTest {

    @Test
    public void verifyLoginFromExcel() throws Exception {

        String excelPath = System.getProperty("user.dir") + "/testData/loginData.xlsx";
        ExcelUtils excel = new ExcelUtils(excelPath, "Sheet1");
        LoginPage loginPage = new LoginPage(driver);

        // ✅ Include last row properly by adding +1
        int totalRows = excel.getRowCount();

        for (int i = 1; i <= totalRows; i++) { // start from 1 (skip header)
            String email = excel.getCellData(i, 0);
            String password = excel.getCellData(i, 1);
            String expectedResult = excel.getCellData(i, 2);

            driver.get("https://demo.nopcommerce.com/");
            loginPage.LoginLink();
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickRememberMe();
            loginPage.clickLogin();

            Thread.sleep(1500); // wait for page to load

            String actualResult;
            String errorMsg = "";

            try {
                // ✅ Check if "My account" is visible — means login success
                boolean isMyAccountVisible = !driver.findElements(By.xpath("//a[@class='ico-account']")).isEmpty();

                if (isMyAccountVisible) {
                    actualResult = "Valid";
                    System.out.println("✅ Login Successful for: " + email);
                    System.out.println("Page Title: " + driver.getTitle());
                    System.out.println("Page URL: " + driver.getCurrentUrl());
                    System.out.println("------------------------------------");

                    // logout for next iteration
                    driver.findElement(By.linkText("Log out")).click();
                    Thread.sleep(1000);
                } else {
                    // ❌ Not logged in → check for error message
                    errorMsg = loginPage.getErrorMessage();
                    actualResult = "Invalid";
                    System.out.println("❌ Login Failed for: " + email);
                    System.out.println("Error Message: " + errorMsg);

                    // Retry clicking login link to reset login page
                    Thread.sleep(1000);
                    loginPage.LoginLink();
                    System.out.println("↩️ Retried clicking Login link to reset page.");
                    System.out.println("------------------------------------");
                }

            } catch (Exception e) {
                // If anything unexpected happens
                actualResult = "Invalid";
                errorMsg = loginPage.getErrorMessage();
                System.out.println("⚠️ Exception during login for: " + email);
                System.out.println("Error Message: " + errorMsg);
                System.out.println("Error Details: " + e.getMessage());
                System.out.println("------------------------------------");
            }

            boolean match = expectedResult.equalsIgnoreCase(actualResult);
            excel.setCellData(i, 3, actualResult, match);

            // If test fails, print clean message
            if (!match) {
                System.out.println("❌ Expected '" + expectedResult + "' but got '" + actualResult + "' for: " + email);
                System.out.println("------------------------------------");
            }

            Assert.assertEquals(actualResult, expectedResult,
                    "Expected: " + expectedResult + ", but got: " + actualResult);
        }

        excel.saveAndClose();
        System.out.println("\n✅ Excel updated successfully with results (green/red colors).");
    }
}
