# POS Selenium Automation Testing

> **Automated testing framework for POS (Point of Sale) system using Selenium WebDriver, TestNG, and Allure reporting.**

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [Configuration](#configuration)
- [Contributing](#contributing)

## 🎯 Overview

This project provides comprehensive automated testing for a POS system, focusing on:
- **User Authentication** - Login/logout functionality
- **System Selection** - Management vs Sales system selection
- **Form Validation** - Email/password field validation
- **Error Handling** - Invalid credentials and empty field scenarios

## ✨ Features

- 🚀 **Selenium WebDriver 4.25.0** - Latest browser automation
- 🧪 **TestNG 7.11.0** - Powerful testing framework with parallel execution
- 📊 **Allure Reports** - Beautiful and interactive test reports
- 📈 **ExtentReports** - Additional HTML reporting
- 🔄 **Page Object Model** - Maintainable test architecture
- 🎭 **Cross-browser Support** - Chrome, Firefox, Edge
- ⚡ **Parallel Execution** - Faster test execution
- 🔒 **Data-driven Testing** - Externalized test data
- 📝 **Comprehensive Logging** - Detailed execution logs

## 🛠 Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 11+ | Programming language |
| **Maven** | 3.6+ | Build and dependency management |
| **Selenium WebDriver** | 4.25.0 | Browser automation |
| **TestNG** | 7.11.0 | Testing framework |
| **Allure** | 2.29.0 | Test reporting |
| **ExtentReports** | 5.1.2 | HTML reports |
| **WebDriverManager** | 5.9.2 | Driver management |
| **Jackson** | 2.18.2 | JSON processing |

## 📁 Project Structure

```
PosSelenium/
├── src/
│   ├── main/java/
│   │   ├── common/           # Base test classes
│   │   ├── constants/        # Configuration constants
│   │   ├── drivers/          # WebDriver management
│   │   ├── keyworks/         # Action keywords (wrapper methods)
│   │   ├── listeners/        # TestNG listeners
│   │   ├── reports/          # Report managers
│   │   └── utils/            # Utility classes
│   └── test/
│       ├── java/
│       │   └── POS/
│       │       ├── pages/    # Page Object classes
│       │       └── tests/    # Test classes
│       └── resources/
│           ├── config/       # Configuration files
│           ├── suites/       # TestNG suite XML files
│           └── testdata/     # Test data files
├── target/                   # Build output (ignored)
├── reports/                  # Generated reports (ignored)
├── pom.xml                   # Maven configuration
├── testng.xml               # Main TestNG suite
└── README.md                # This file
```

## 📋 Prerequisites

- **Java 11** or higher
- **Maven 3.6+**
- **Chrome Browser** (latest version)
- **Git** for version control

## 🚀 Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/ninhnguyen2408/1posnew_selenium.git
   cd 1posnew_selenium
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Verify installation:**
   ```bash
   mvn test -Dtest=LoginTest#testLoginSuccess
   ```

## ▶️ Running Tests

### **Run All Tests:**
```bash
mvn clean test
```

### **Run Specific Test Suite:**
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/suites/SuiteLoginTest.xml
```

### **Run Specific Test Class:**
```bash
mvn clean test -Dtest=LoginTest
```

### **Run Specific Test Method:**
```bash
mvn clean test -Dtest=LoginTest#testLoginSuccess
```

### **Run with Parallel Execution:**
```bash
mvn clean test -Dparallel=methods -DthreadCount=3
```

### **Run with Custom Browser:**
```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
```

## 📊 Test Reports

### **Allure Reports:**
1. **Generate report:**
   ```bash
   mvn allure:report
   ```
2. **Serve report:**
   ```bash
   mvn allure:serve
   ```
3. **View at:** `http://localhost:8080`

### **ExtentReports:**
- **Location:** `reports/ExtentReport/ExtentReport.html`
- **Auto-generated** after test execution

### **TestNG Reports:**
- **Location:** `target/surefire-reports/index.html`

## ⚙️ Configuration

### **Test Configuration:**
Edit `src/test/resources/config/ConfigData.java`:
```java
public class ConfigData {
    public static String URL = "https://hdseafood.1erp.vn/login";
    public static String Email = "your-email@example.com";
    public static String Password = "your-password";
}
```

### **Browser Configuration:**
- **Default:** Chrome
- **Change in:** `testng.xml` or pass `-Dbrowser=firefox`

### **Parallel Execution:**
Edit `src/test/resources/suites/SuiteLoginTest.xml`:
```xml
<suite name="LoginTestSuite" parallel="methods" thread-count="3">
```

## 🧪 Test Cases

### **Login Test Scenarios:**

| Test Case | Description | Priority |
|-----------|-------------|----------|
| `testLoginSuccess` | Valid credentials login | Critical |
| `testLoginAndSelectManagementSystem` | Login + Management system selection | Critical |
| `testLoginAndSelectSalesSystem` | Login + Sales system selection | Critical |
| `testLoginWithInvalidEmail` | Invalid email validation | Normal |
| `testLoginWithInvalidPassword` | Invalid password validation | Normal |
| `testLoginWithEmptyEmail` | Empty email field validation | Normal |
| `testLoginWithEmptyPassword` | Empty password field validation | Normal |
| `testLoginWithBothEmptyFields` | Both fields empty validation | Critical |

### **Test Data Coverage:**
- ✅ Valid credentials
- ✅ Invalid email formats
- ✅ Invalid passwords
- ✅ Empty field scenarios
- ✅ SQL injection attempts
- ✅ XSS prevention testing

## 🎯 Best Practices

### **Page Object Model:**
```java
public class LoginPage {
    private By inputEmail = By.xpath("//input[@name='email']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Đăng nhập']");
    
    public void login(String email, String password) {
        ActionKeywords.sendKeys(inputEmail, email);
        ActionKeywords.clickElement(buttonLogin);
    }
}
```

### **Test Data Management:**
- Use `ConfigData.java` for environment-specific data
- Externalize test data in JSON/CSV files
- Never hardcode sensitive information

### **Assertions:**
```java
// Good
ActionKeywords.assertEquals(actualResult, expectedResult, "Login should succeed with valid credentials");

// Better with Allure
AllureManager.stepTestPassed("User successfully logged in");
```

## 🤝 Contributing

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/new-test-case`
3. **Commit** changes: `git commit -m "Add new login validation test"`
4. **Push** to branch: `git push origin feature/new-test-case`
5. **Submit** a Pull Request

### **Code Standards:**
- Follow **Page Object Model** pattern
- Add **Allure annotations** for better reporting
- Include **meaningful assertions** with custom messages
- Write **clear commit messages**

## 📞 Support

- **Issues:** [GitHub Issues](https://github.com/ninhnguyen2408/1posnew_selenium/issues)
- **Documentation:** See `/docs` folder (coming soon)
- **Email:** ninhnguyen2408@example.com

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 📈 Project Statistics

![Tests](https://img.shields.io/badge/tests-10-brightgreen)
![Coverage](https://img.shields.io/badge/coverage-85%25-yellow)
![Build](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

---

**Happy Testing! 🚀**

> Built with ❤️ by [Ninh Nguyen](https://github.com/ninhnguyen2408)