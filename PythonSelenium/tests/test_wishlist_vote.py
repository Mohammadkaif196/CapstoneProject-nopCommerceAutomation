import time
import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service


@pytest.fixture
def driver():
    # Setup Chrome using webdriver-manager
    driver = webdriver.Chrome()
    driver.maximize_window()
    yield driver
    driver.quit()


def test_add_to_wishlist_and_vote(driver):
    driver.get("https://demo.nopcommerce.com/")
    time.sleep(2)

    # --- Step 1: Scroll down to Wishlist button ---
    wishlist_btn = driver.find_element(By.XPATH, "(//button[@title='Add to wishlist'])[3]")
    driver.execute_script("arguments[0].scrollIntoView(true);", wishlist_btn)
    time.sleep(1)
    wishlist_btn.click()
    time.sleep(2)

    # --- Step 2: Verify success added to Wishlist ---
    wishlist_link = driver.find_element(By.XPATH, "(//a[@href='/wishlist'])[2]")
    assert wishlist_link.is_displayed()
    print("✅ Successfully added to Wishlist")

    # --- Step 3: Scroll down to 'Excellent' radio button ---
    excellent_radio = driver.find_element(By.CSS_SELECTOR, "input#pollanswers-1")
    driver.execute_script("arguments[0].scrollIntoView(true);", excellent_radio)
    time.sleep(1)
    excellent_radio.click()
    time.sleep(1)

    # --- Step 4: Click the Vote button ---
    vote_btn = driver.find_element(By.ID, "vote-poll-1")
    vote_btn.click()
    print("✅ Voted successfully")
    time.sleep(2)

    # --- Step 5: Close Wishlist success message ---
    close_success = driver.find_element(By.XPATH, "//div[@class='bar-notification success']//span[1]")
    close_success.click()
    print("✅ Closed success notification")
    time.sleep(1)

    # --- Step 6: Scroll up to Wishlist button in header ---
    wishlist_top = driver.find_element(By.XPATH, "//span[text()='Wishlist']")
    driver.execute_script("arguments[0].scrollIntoView(true);", wishlist_top)
    time.sleep(1)
    wishlist_top.click()

    # --- Step 7: View Wishlist items for 10 seconds ---
    print("🕒 Viewing Wishlist items for 10 seconds...")
    time.sleep(10)
    print("✅ Test completed successfully!")