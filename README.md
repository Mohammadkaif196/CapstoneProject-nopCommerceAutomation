## 👥 Team

**GROUP 3 - Test Automation Engineers**

| Name | GitHub |
|------|--------|
| Anuvrat Verma | [@anuvratverma](https://github.com/Anuvrat-Verma) |
| Mohammad Kaif Shaik | [@mohammadkaif](https://github.com/Mohammadkaif196) |
| Lavanya Donga | [@lavanyadonga](https://github.com/lavanyasatya08) |
| Suchismita Dutta | [@suchismitadutta](https://github.com/crack-head22) |
| Likhitha Yada | [@likhithayada](https://github.com/likhitha-yada) |
| Sai Lakshmi Anupama Gopavarapu | [@sailakshmianupama](https://github.com/GopavarapuAnupama) |

---
# 🛍️ nopCommerce Test Automation Suite

[![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)](https://www.python.org/)
[![TestNG](https://img.shields.io/badge/TestNG-FF6C37?style=for-the-badge&logo=testing-library&logoColor=white)](https://testng.org/)
[![Jira](https://img.shields.io/badge/Jira-0052CC?style=for-the-badge&logo=jira&logoColor=white)](https://www.atlassian.com/software/jira)
[![BrowserStack](https://img.shields.io/badge/BrowserStack-FF8800?style=for-the-badge&logo=browserstack&logoColor=white)](https://www.browserstack.com/)

> A comprehensive end-to-end test automation framework for the **nopCommerce e-commerce platform**, integrated with **Jira + Xray** for Agile test management and **BrowserStack** for cross-platform cloud testing.  
> Supports parallel execution, data-driven testing, and detailed Allure reporting.

---


## 📋 Table of Contents

- [About The Project](#-about-the-project)
- [Key Features](#-key-features)
- [Technology Stack](#-technology-stack)
- [Jira Project & Test Management](#-jira-project--test-management)
- [Cloud & Mobile Testing](#-cloud--mobile-testing)
- [Test Scenarios Overview (Total 9)](#-test-scenarios-overview-total-9)
- [Getting Started](#-getting-started)
- [Project Structure](#-project-structure)
- [Running Tests](#-running-tests)
- [Test Reports](#-test-reports)
- [Team](#-team)
- [Acknowledgments](#-acknowledgments)


---

## 🎯 About The Project

This project represents a **comprehensive end-to-end automation framework** developed to ensure the reliability, scalability, and efficiency of the **nopCommerce e-commerce website** and the **Wikipedia mobile application**.  
The framework automates major user workflows using **Selenium WebDriver** with **Java** and **Python**, while enabling advanced test management through **Jira + Xray** and real-device execution via **BrowserStack**.


### Application Under Test

![nopCommerce Homepage](https://raw.githubusercontent.com/Mohammadkaif196/CapstoneProject-nopCommerceAutomation/main/Screenshot%202025-10-29%20105921.png)

### Why This Project?

- ✅ **Comprehensive Coverage**: Covers registration to checkout  
- ✅ **Cross-Language Support**: Uses Java (TestNG) and Python (PyTest)  
- ✅ **Enterprise-Ready**: POM, data-driven design, Maven & pip  
- ✅ **Cloud & Mobile Testing**: Integrated BrowserStack & Appium  
- ✅ **Agile Test Management**: Jira + Xray dashboards  
- ✅ **Detailed Reporting**: Allure + Log4j insights  

---

## ✨ Key Features

| Feature | Description |
|---------|-------------|
| 🔄 **Cross-Browser Testing** | Supports Chrome and Firefox with easy extensibility |
| ☁️ **Cloud Testing** | Runs tests on BrowserStack cloud for real devices |
| 📊 **Data-Driven Testing** | Excel-based test data for dynamic validation |
| 📝 **Comprehensive Reporting** | Allure Reports with screenshots & execution timelines |
| 🏗️ **Page Object Model (POM)** | Scalable and maintainable test architecture |
| 🔀 **Parallel Execution** | Multi-threaded testing using TestNG & pytest-xdist |
| 🧠 **Agile Terminology** | Screenshots of Jira + Xray for particular scenarios |

---

## 🛠️ Technology Stack

### Core Technologies
```
Java 17+                                              |          Python 3.8+
├── Selenium WebDriver                                |          ├── Selenium WebDriver
├── TestNG                                            |          ├── PyTest
├── Maven                                             |          ├── pip
└── Apache POI                                        |          └── openpyxl

```



### Tools & Frameworks
- **IDEs:** Eclipse, IntelliJ IDEA, PyCharm, VS Code  
- **Reporting:** Allure Framework, Log4j  
- **Cloud Testing:** BrowserStack  
- **Mobile Automation:** Appium  
- **Test Management:** Jira + Xray  
- **Version Control:** Git & GitHub  

---

## 📈 Jira Project & Test Management

The **EcommApp Jira Project** was managed using the **Agile SCRUM** framework with three main epics — *User Management*, *Product Catalog*, and *Cart & Checkout*.  
Sprint 1 (25 Sep – 8 Oct) was tracked through **Backlog**, **Active Sprint**, and **Dashboard** views for better visibility.  

The **Dashboard** highlighted key bugs like **EC-8**, **EC-84**, and **EC-85**, along with sprint progress and QA metrics.  
Using the **Xray plugin**, both manual and automated test cases were tracked, including a key test validating **secure JWT storage**, which passed successfully.  
This setup ensured complete traceability and improved collaboration across the team.


---

## ☁️ Cloud & Mobile Testing

### Web Testing (BrowserStack - Safari)
- Device: **iPhone 12 Pro (iOS 14)**  
- Scenario: JavaScript prompt alert verification  
- Tools: Selenium Java + TestNG + RemoteWebDriver  

### Mobile Testing (BrowserStack - Android)
- App: **Wikipedia Android Application**  
- Framework: Appium + TestNG  
- Features Tested:
  - Search functionality  
  - Text verification  
  - Screenshot capture on failure  
  - Remote session management  

---
## 🧪 Test Scenarios Overview (Total 9)

### Java-Based Scenarios

#### 1️⃣ User Registration & Logout
**Flow:** Register → Redirect → Logout  
**Validations:** Registration success, redirection, session termination.  
🎥 **Execution Video:** [Watch Here](https://drive.google.com/file/d/1QilJz1vVejfrxxjBr7CHJMOmneclj5bO/view?usp=drivesdk)

---

#### 2️⃣ Data-Driven Login Validation
**Flow:** Login with multiple credentials from Excel.  
**Validations:** Positive/Negative login, error message handling.  
🎥 **Execution Video:** [Watch Here](https://drive.google.com/file/d/19phq7DvFLFVgdizN3HCqaO_tI2n7N5Io/view?usp=sharing)

---

#### 3️⃣ Address Management
**Flow:** Login → My Account → Add Address → Verify  
**Validations:** CRUD operations on address section.  
🎥 **Execution Video:** [Watch Here](https://drive.google.com/file/d/1Bv9r0FlYzb0O5s3TmCvYG22y3ia-5twb/view?usp=drivesdk)

---

#### 4️⃣ Complete Checkout Flow
**Flow:** Search → Add to Cart → Checkout → Payment → Confirmation  
**Validations:** Order success message, billing info, terms agreement.
🎥 **Execution Video:** [Watch Here](https://drive.google.com/file/d/1HcyC_8DaNLJ5H_kRbK4Zs-NPgKoQD2bL/view?usp=sharing)

---

### Python-Based Scenarios

#### 5️⃣ Product Comparison
**Flow:** Select two laptops → Add to compare → View Comparison  
**Validations:** Comparison table content and price accuracy.  
🎥 **Execution Video:** [Watch Here](https://drive.google.com/file/d/1EjNc_1jDjFxMkv2mF0O-eYPwKJ1l1XQm/view?usp=sharing)

---

#### 6️⃣ Jewelry Rental Workflow
**Flow:** Login → Jewelry → Rent Item → Checkout  
**Validations:** Date picker, rental price, confirmation message.  
🎥 **Execution Video:** [Watch Here](https://drive.google.com/file/d/1eWooD2ZtkxlyJ6MvMUQGYTTs7ohAkrjP/view?usp=sharing)

---

#### 7️⃣ Wishlist & Voting
**Flow:** Add product to wishlist → Vote website → Verify Wishlist  
**Validations:** Success messages, UI verification.  
🎥 **Execution Video:** [Watch Here](https://drive.google.com/file/d/1gsSAG2dNpzwTWwVfgZ_EmSbzxA2mb_bQ/view?usp=sharing)

---

### BrowserStack Scenarios

#### 8️⃣ JavaScript Alerts (Safari on iPhone)
**Flow:** Open Heroku JS alert page → Click prompt → Send text → Verify message.  
**Tools:** Selenium Java + TestNG + BrowserStack Cloud.  
🎥 **Execution Video:** [Watch Here](https://drive.google.com/file/d/1H4sKO__kugRrXfm7D97_cepB1jdk0eO8/view?usp=sharing)

---

#### 9️⃣ Wikipedia Mobile Automation
**Flow:** Launch app → Verify text → Verify Search input → Search term → Validate results  
**Tools:** Appium + TestNG + BrowserStack Android.  
🎥 **Execution Video:** [Watch Here](https://drive.google.com/file/d/1Yw0XQtO0TM0rh3vU0gnbDuXZhwdx0KXA/view?usp=sharing)

---

## 🚀 Getting Started

### Prerequisites
**Java:**
```bash
Java 17+
Maven 3.6+
Chrome/Firefox
```

**For Python Tests:**
```bash
Python 3.8+
pip package manager
Chrome/Firefox browser
```

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/CapstoneProject-nopCommerce.git
cd CapstoneProject-nopCommerce
```

2. **Set up Java environment**
```bash
cd SeleniumJava
mvn clean install
```

3. **Set up Python environment**
```bash
cd PythonTest
pip install -r requirements.txt
```

---

## 📁 Project Structure
```
CapstoneProject-nopCommerce/
│
├── SeleniumJava/                              # Java-based automation project
│ ├── pom.xml                                   # Maven configuration
│ ├── .gitignore                                # Java-specific ignores
│ ├── testng.xml                                # TestNG suite configuration
│ ├── src/
│ │   └── test/java/
│ │       ├── MobileAutomation/                 # Mobile automation test scripts
│ │       │   ├── WikipediaAppTests.java
│ │       │   └── browserstackTest.java
│ │
│ │       ├── PageObjects/                      # Page Object Model classes
│ │       │   ├── BasePage.java
│ │       │   ├── CheckoutPage.java
│ │       │   ├── HomePage.java
│ │       │   ├── LoginPage.java
│ │       │   ├── LogoutPage.java
│ │       │   └── RegisterPage.java
│ │
│ │       ├── TestCases/                        # All test case classes
│ │       │   ├── BaseTest.java
│ │       │   ├── LoginPageTest.java
│ │       │   ├── RegisterLogoutTest.java
│ │       │   ├── addAddressTest.java
│ │       │   └── purchaseTest.java
│ │
│ │       └── utils/                            # Utility/helper classes
│ │           └── ExcelUtils.java
│ │
│ ├── screenshot/                               # Captured test screenshots
│ │   └── (saved screenshots during BrowserStack test runs)
│ │
│ ├── AllureReportScreenShot/                   # Screenshots attached in Allure reports
│ │   └── (Allure screenshots for failed/passed tests)
│ │
│ ├── target/
│ │   ├── allure-results/                       # Allure raw results
│ │   └── allure-report/                        # Generated HTML reports
│ │
│ └── README.md                                 # Java project documentation
│
├── PythonTest/                                 # Python-based automation project
│ ├── tests/
│ │   ├── test_login.py
│ │   ├── test_register.py
│ │   ├── test_search.py
│ │   └── test_logout.py
│ │
│ ├── AllureReportsScreenShot/                  # Screenshots for Allure reports (Python)
│ │   └── (Allure screenshots for test results)
│ │
│ ├── requirements.txt                          # Python dependencies
│ └── README.md                                 # Python project documentation
│
├── README.md                                   # Main project documentation
└── .gitignore                                  # Global repository ignores


```

---

## ▶️ Running Tests

### Execute Java Tests

**Navigate to Java project:**
```bash
cd SeleniumJava
```

**Run all tests:**
```bash
mvn clean test
```

**Run specific test suite:**
```bash
mvn test -DsuiteXmlFile=testng.xml
```

**Run with specific browser:**
```bash
mvn test -Dbrowser=firefox
```

### Execute Python Tests

**Navigate to Python project:**
```bash
cd PythonTest
```

**Run all tests:**
```bash
pytest tests/ -v
```

**Run specific test file:**
```bash
pytest tests/test_login.py -v
```

**Run with HTML report:**
```bash
pytest tests/ --html=reports/report.html
```

### Parallel Execution

**Java (TestNG):**
```xml
<suite name="Parallel Suite" parallel="methods" thread-count="4">
<!-- Test definitions -->
</suite>
```

**Python (pytest-xdist):**
```bash
pytest tests/ -n 4
```

---

## 📊 Test Reports

### Allure Reports

Generate comprehensive Allure reports with:

**Java:**
```bash
cd SeleniumJava
mvn allure:serve
```

**Python:**
```bash
cd PythonTest
allure serve allure-results/
```

**Report Features:**
- ✅ Test execution timeline
- ✅ Pass/Fail statistics with trends
- ✅ Failure screenshots and logs
- ✅ Step-by-step execution details
- ✅ Historical data comparison

### Sample Report View

![Allure Report Overview](https://github.com/yourusername/CapstoneProject-nopCommerce/assets/allure-report-sample.png)

*Example of Allure Report showing 100% test pass rate with detailed test execution metrics*

---

## 🙏 Acknowledgments

- [nopCommerce](https://www.nopcommerce.com/) for the demo platform
- [Selenium](https://www.selenium.dev/) community for excellent documentation
- [Allure Framework](https://docs.qameta.io/allure/) for reporting capabilities

---

<div align="center">

**⭐ If you find this project helpful, please consider giving it a star!**

Made with ❤️ by GROUP 3

</div>
