# 🛍️ CapstoneProject-nopCommerce Test Automation Suite

[![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)](https://www.python.org/)
[![TestNG](https://img.shields.io/badge/TestNG-FF6C37?style=for-the-badge&logo=testing-library&logoColor=white)](https://testng.org/)

> A comprehensive end-to-end test automation framework for nopCommerce e-commerce platform, featuring parallel test execution, data-driven testing, and detailed reporting capabilities.

---

## 📋 Table of Contents

- [About The Project](#about-the-project)
- [Key Features](#key-features)
- [Technology Stack](#technology-stack)
- [Test Scenarios](#test-scenarios)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [Contributing](#contributing)
- [Team](#team)

---

## 🎯 About The Project

This repository contains a **production-ready test automation suite** designed for the [nopCommerce demo store](https://demo.nopcommerce.com). The framework validates critical e-commerce workflows including user management, product operations, checkout processes, and advanced features like product comparison and jewelry rentals.

### Why This Project?

- ✅ **Comprehensive Coverage**: Tests span across user authentication, product management, and transaction workflows
- ✅ **Dual-Language Support**: Leverages both Java and Python for maximum flexibility
- ✅ **Enterprise-Ready**: Built with industry best practices including POM, data-driven testing, and CI/CD compatibility
- ✅ **Detailed Reporting**: Integrated with Allure for actionable test insights

---

## ✨ Key Features

| Feature | Description |
|---------|-------------|
| 🔄 **Cross-Browser Testing** | Supports Chrome and Firefox with easy extension to other browsers |
| 📊 **Data-Driven Testing** | Excel-based test data management for scalable test execution |
| 📝 **Comprehensive Reporting** | Allure Reports with screenshots, execution timelines, and failure analysis |
| 🏗️ **Page Object Model** | Maintainable and scalable test architecture |
| 🔀 **Parallel Execution** | Reduced test execution time through concurrent test runs |
| 🎯 **End-to-End Workflows** | Real-world user journey validation from registration to checkout |

---

## 🛠️ Technology Stack

### Core Technologies
```
Java 11+                    Python 3.8+
├── Selenium WebDriver      ├── Selenium WebDriver
├── TestNG                  ├── PyTest
├── Maven                   ├── pip
└── Apache POI              └── openpyxl
```

### Development Tools
- **IDEs**: Eclipse, IntelliJ IDEA, PyCharm, VS Code
- **Build Tools**: Maven (Java), pip (Python)
- **Reporting**: Allure Framework
- **Version Control**: Git

---

## 🧪 Test Scenarios

### Java-Based Scenarios

#### 1️⃣ User Registration & Session Management
**Validates**: New user onboarding and secure logout functionality
```
Registration Flow → Email Verification → Dashboard Redirect → Logout
```

**Test Coverage:**
- Form validation with valid/invalid inputs
- Success message verification
- Post-registration page navigation
- Session cleanup on logout

---

#### 2️⃣ Data-Driven Login Validation
**Validates**: Authentication mechanism with multiple credential sets

**Features:**
- Excel-based test data management
- Positive and negative test scenarios
- Error message validation
- Dashboard accessibility checks

**Test Matrix:**
| Credential Type | Expected Outcome |
|----------------|------------------|
| Valid credentials | Successful login |
| Invalid email | Error message displayed |
| Invalid password | Authentication failure |
| Empty fields | Validation warnings |

---

#### 3️⃣ Address Management
**Validates**: User profile address CRUD operations

**Workflow:**
```
Login → My Account → Addresses → Add New → Form Submission → Verification
```

**Validations:**
- Mandatory field enforcement
- Data persistence
- Multiple address handling
- Edit/Delete functionality

---

#### 4️⃣ Complete Purchase Workflow
**Validates**: End-to-end e-commerce transaction

**Journey Map:**
```
Product Search → Add to Cart → Cart Review → Checkout → Payment → Order Confirmation
```

**Coverage Areas:**
- Product search and filtering
- Cart operations (add/remove/update)
- Billing and shipping information
- Payment method selection
- Order summary validation
- Confirmation email trigger

---

### Python-Based Scenarios

#### 5️⃣ Product Comparison Engine
**Validates**: Multi-product comparison functionality

**Process Flow:**
```
Category Selection → Product Addition → Comparison View → Specification Analysis
```

**Test Points:**
- Comparison list management
- Side-by-side specification display
- Feature highlighting
- Price comparison accuracy

---

#### 6️⃣ Jewelry Rental Workflow
**Validates**: Specialized rental product handling

**Rental Journey:**
```
Login → Category Browse → Product Selection → Date Range Selection → 
Cart Addition → Checkout → Rental Confirmation
```

**Unique Validations:**
- Date picker functionality
- Rental period calculation
- Availability verification
- Terms and conditions acceptance
- Rental-specific checkout flow

---

## 🚀 Getting Started

### Prerequisites

**For Java Tests:**
```bash
Java JDK 11 or higher
Maven 3.6+
Chrome/Firefox browser
ChromeDriver/GeckoDriver
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

4. **Configure test properties**
```properties
# config.properties
base.url=https://demo.nopcommerce.com
browser=chrome
implicit.wait=10
explicit.wait=20
```

---

## 📁 Project Structure
```
CapstoneProject-nopCommerce/
│
├── SeleniumJava/                    # Java-based automation project
│   ├── pom.xml                      # Maven configuration
│   ├── .gitignore                   # Java-specific ignores
│   ├── testng.xml                   # TestNG suite configuration
│   ├── src/
│   │   ├── main/java/
│   │   │   ├── pages/               # Page Object Models
│   │   │   ├── utilities/           # Helper utilities
│   │   │   └── base/                # Base test classes
│   │   └── test/java/
│   │       └── Testcases/
│   │           ├── LoginTest.java
│   │           ├── RegisterTest.java
│   │           ├── SearchTest.java
│   │           └── LogoutTest.java
│   ├── target/
│   │   ├── allure-results/          # Allure raw results
│   │   └── allure-report/           # Generated HTML reports
│   └── README.md                    # Java project documentation
│
├── PythonTest/                      # Python-based automation project
│   ├── tests/
│   │   ├── test_login.py
│   │   ├── test_register.py
│   │   ├── test_search.py
│   │   └── test_logout.py
│   ├── requirements.txt             # Python dependencies
│   └── README.md                    # Python project documentation
│
├── README.md                        # Main project documentation
└── .gitignore                       # Global repository ignores
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

![Allure Dashboard](https://docs.qameta.io/allure/images/overview.png)

---

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Coding Standards
- Follow PEP 8 for Python code
- Follow Java naming conventions
- Add comments for complex logic
- Update documentation for new features
- Ensure all tests pass before submitting PR

---

## 👥 Team

**GROUP 3 - Test Automation Engineers**

| Name | GitHub |
|------|--------|
| Anuvrat Verma | [@anuvratverma](https://github.com/anuvratverma) |
| Mohammad Kaif Shaikh | [@mohammadkaif](https://github.com/mohammadkaif) |
| Lavanya Donga | [@lavanyadonga](https://github.com/lavanyadonga) |
| Suchismita Dutta | [@suchismitadutta](https://github.com/suchismitadutta) |
| Likhitha Yada | [@likhithayada](https://github.com/likhithayada) |
| Sai Lakshmi Anupama Gopavarapu | [@sailakshmianupama](https://github.com/sailakshmianupama) |

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 🙏 Acknowledgments

- [nopCommerce](https://www.nopcommerce.com/) for the demo platform
- [Selenium](https://www.selenium.dev/) community for excellent documentation
- [Allure Framework](https://docs.qameta.io/allure/) for reporting capabilities

---

<div align="center">

**⭐ I hope you find this project helpful**

Made with ❤️ by GROUP 3

</div>
