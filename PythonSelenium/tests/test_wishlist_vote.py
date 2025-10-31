import pytest
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
from webdriver_manager.chrome import ChromeDriverManager


@pytest.fixture(scope="function")
def driver():
    """Setup and teardown Chrome WebDriver."""
    chrome_options = Options()
    chrome_options.add_argument("--start-maximized")
    chrome_options.add_argument("--disable-infobars")
    chrome_options.add_argument("--disable-dev-shm-usage")
    chrome_options.add_argument("--no-sandbox")
    # chrome_options.add_argument("--headless=new")  # enable if needed

    service = Service(ChromeDriverManager().install())
    drv = webdriver.Chrome(service=service, options=chrome_options)

    yield drv
    drv.quit()


def test_add_to_wishlist_and_vote(driver):
    driver.get("https://demo.nopcommerce.com/")
    wait = WebDriverWait(driver, 10)

    # --- Step 1: Scroll down to Wishlist button and click ---
    wishlist_btn = wait.until(
        EC.element_to_be_clickable((By.XPATH, "(//button[@title='Add to wishlist'])[3]"))
    )
    driver.execute_script("arguments[0].scrollIntoView(true);", wishlist_btn)
    wishlist_btn.click()

    # --- Step 2: Verify success added to Wishlist ---
    wishlist_link = wait.until(
        EC.visibility_of_element_located((By.XPATH, "(//a[@href='/wishlist'])[2]"))
    )
    assert wishlist_link.is_displayed()
    print("✅ Successfully added to Wishlist")

    # --- Step 2.1: Wait for the success notification and close it (if present) ---
    notif_locator = (By.CSS_SELECTOR, "div.bar-notification.success")
    close_btn_locator = (By.CSS_SELECTOR, "div.bar-notification.success span.close")

    try:
        wait_small = WebDriverWait(driver, 5)
        wait_small.until(EC.visibility_of_element_located(notif_locator))
        close_btn = driver.find_element(*close_btn_locator)
        close_btn.click()
        wait_small.until(EC.invisibility_of_element_located(notif_locator))
    except TimeoutException:
        pass  # Notification not found or already disappeared

    # --- Step 3: Scroll down to 'Excellent' radio button and select it ---
    excellent_radio = wait.until(
        EC.element_to_be_clickable((By.CSS_SELECTOR, "input#pollanswers-1"))
    )
    driver.execute_script("arguments[0].scrollIntoView(true);", excellent_radio)
    excellent_radio.click()

    # --- Step 4: Click the Vote button ---
    vote_btn = wait.until(EC.element_to_be_clickable((By.ID, "vote-poll-1")))
    vote_btn.click()
    print("✅ Voted successfully")

    # --- Step 5: (Optional) Wait for poll results confirmation ---
    try:
        # pass the locator tuple directly (not inside a list)
        results_locator = (By.CSS_SELECTOR, "div.poll-results, div.poll-vote-result, #poll-1")
        WebDriverWait(driver, 5).until(EC.visibility_of_any_elements_located(results_locator))
    except TimeoutException:
        # Non-fatal: results element may not appear in the short timeout
        pass
