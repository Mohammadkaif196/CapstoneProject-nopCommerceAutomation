import time
import pytest
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager


@pytest.fixture()
def driver():
    """
    Fixture to set up and tear down Chrome WebDriver.
    Uses webdriver-manager to automatically install the correct driver.
    """
    chrome_options = Options()
    chrome_options.add_argument("--start-maximized")  # Open browser in full screen

    # Initialize Chrome driver service (auto-installed by webdriver-manager)
    service = Service(ChromeDriverManager().install())
    drv = webdriver.Chrome(service=service, options=chrome_options)
    drv.implicitly_wait(5)

    yield drv   # Provide the driver to the test function

    print("\n🧹 Closing the browser session...")
    time.sleep(2)
    drv.quit()


def test_compare_two_products(driver):
    """
    Test Case: Compare two notebook products on nopCommerce demo site.
    Steps:
    1. Open homepage
    2. Navigate to Computers → Notebooks
    3. Add two notebooks to the comparison list
    4. Open comparison page and verify both products are listed
    """

    wait = WebDriverWait(driver, 10)

    # Step 1: Open nopCommerce homepage
    print("\n🔹 Step 1: Opening nopCommerce homepage...")
    driver.get("https://demo.nopcommerce.com/")
    time.sleep(2)

    # Step 2: Click on 'Computers' tab
    print("🔹 Step 2: Clicking 'COMPUTERS' tab...")
    computers_tab = wait.until(EC.element_to_be_clickable(
        (By.XPATH, "//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]")))
    computers_tab.click()
    time.sleep(2)

    # Step 3: Click on 'Notebooks' section
    print("🔹 Step 3: Navigating to 'NOTEBOOKS' section...")
    notebooks_link = wait.until(EC.element_to_be_clickable(
        (By.XPATH, "//div[@class='page-body']//a[contains(text(),'Notebooks')]")))
    notebooks_link.click()
    time.sleep(2)

    # Step 4: Add first two notebooks to comparison list
    for i in range(2):
        print(f"🔹 Step 4.{i + 1}: Adding notebook {i + 1} to comparison list...")

        # Re-fetch buttons each time to avoid stale elements
        compare_buttons = wait.until(EC.presence_of_all_elements_located(
            (By.XPATH, "//button[@title='Add to compare list']")))
        assert len(compare_buttons) >= 2, "❌ Less than 2 products available to compare"

        # Click compare icon for product i
        compare_buttons[i].click()
        time.sleep(2)

        # Wait for success message popup
        success_msg = wait.until(EC.visibility_of_element_located(
            (By.XPATH, "//div[contains(@class,'bar-notification') and contains(@class,'success')]")))
        assert "The product has been added to your product comparison" in success_msg.text
        print("✅ Product successfully added to comparison list.")

        # Click on "product comparison" link in success popup
        compare_link = wait.until(EC.element_to_be_clickable(
            (By.XPATH, "//div[contains(@class,'bar-notification') and contains(@class,'success')]//a[contains(text(),'product comparison')]")))
        compare_link.click()
        time.sleep(2)

        # After first product, go back to add the second one
        if i == 0:
            print("🔙 Returning to Notebooks page to add second product...")
            driver.back()
            wait.until(EC.presence_of_element_located((By.XPATH, "//h1[contains(text(),'Notebooks')]")))
            time.sleep(2)

    # Step 5: Verify both products appear in comparison table
    print("🔹 Step 5: Verifying comparison table contains both products...")
    comparison_table = wait.until(EC.presence_of_element_located(
        (By.XPATH, "//table[@class='compare-products-table']")))

    # Extract product names from comparison table
    products = comparison_table.find_elements(By.XPATH, ".//tr[contains(@class,'product-name')]//a")
    product_names = [p.text.strip() for p in products if p.text.strip()]
    print(f"🧾 Products listed for comparison: {product_names}")

    # Assertion to ensure two products are compared
    assert len(product_names) >= 2, f"❌ Expected 2 products, but found {len(product_names)}"
    print("✅ Both products successfully displayed in the comparison table.")

    print("\n🎉 Test Completed Successfully!")
