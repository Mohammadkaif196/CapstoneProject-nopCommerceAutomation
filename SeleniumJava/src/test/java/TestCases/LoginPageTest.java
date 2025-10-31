package TestCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.*;

import PageObjects.LoginPage;
import utils.ExcelUtils;

@Epic("Login Module")
@Feature("Login Functionality")
public class LoginPageTest extends BaseTest {

    // ===> Test Method <===
    @Test(dataProvider = "loginData")
    @Story("Excel Data-driven Login Verification")
    @Description("Verifies login functionality for multiple credentials from Excel and updates the results back to the sheet.")
    public void verifyLoginFromExcel(String email, String password, String expectedResult, int rowNum) throws Exception {

        LoginPage loginPage = new LoginPage(driver);
        String excelPath = System.getProperty("user.dir") + "/testData/loginData.xlsx";
        ExcelUtils excel = new ExcelUtils(excelPath, "Sheet1");

        driver.get("https://demo.nopcommerce.com/");
        loginPage.LoginLink();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickRememberMe();
        loginPage.clickLogin();

        Thread.sleep(1500); // wait for page load
        String actualResult;
        String errorMsg = "";

        try {
            boolean isMyAccountVisible = !driver.findElements(By.xpath("//a[@class='ico-account']")).isEmpty();

            if (isMyAccountVisible) {
                actualResult = "Valid";
                Allure.step("✅ Login successful for: " + email);
                driver.findElement(By.linkText("Log out")).click();
                Thread.sleep(1000);
            } else {
                actualResult = "Invalid";
                errorMsg = loginPage.getErrorMessage();
                Allure.step("❌ Login failed for: " + email + " | Error: " + errorMsg);
            }

        } catch (Exception e) {
            actualResult = "Invalid";
            errorMsg = loginPage.getErrorMessage();
            Allure.step("⚠️ Exception for: " + email + " | " + e.getMessage());
        }

        boolean match = expectedResult.equalsIgnoreCase(actualResult);
        excel.setCellData(rowNum, 3, actualResult, match); // ✅ write result (colored cell)
        excel.saveAndClose();

        if (!match) {
            Allure.step("❌ Mismatch → Expected: " + expectedResult + " but got: " + actualResult);
        }

        Assert.assertEquals(actualResult, expectedResult,
                "Expected: " + expectedResult + ", but got: " + actualResult);
    }

    // ===> DataProvider <===
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        String excelPath = System.getProperty("user.dir") + "/testData/loginData.xlsx";
        ExcelUtils excel = new ExcelUtils(excelPath, "Sheet1");
        int totalRows = excel.getRowCount();

        Object[][] data = new Object[totalRows][4]; // email, password, expectedResult, rowNum

        for (int i = 1; i <= totalRows; i++) {
            data[i - 1][0] = excel.getCellData(i, 0); // email
            data[i - 1][1] = excel.getCellData(i, 1); // password
            data[i - 1][2] = excel.getCellData(i, 2); // expectedResult
            data[i - 1][3] = i; // rowNum for writing back
        }

        excel.saveAndClose();
        return data;
    }
}
