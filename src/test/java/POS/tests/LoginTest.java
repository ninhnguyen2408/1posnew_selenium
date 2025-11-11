package POS.tests;

import POS.pages.common.DashboardPage;
import POS.pages.common.LoginPage;
import common.BaseTest;
import constants.ConfigData;
import listeners.TestListener;
import reports.AllureManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reports.ExtentTestManager;
import utils.LogUtils;

import io.qameta.allure.*;
import java.lang.reflect.Method;

@Listeners(TestListener.class)
@Epic("POS System")
@Feature("User Authentication")
public class LoginTest extends BaseTest {
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    @BeforeMethod
    public void setup(Method method) {
        LogUtils.info("=== STARTING TEST: " + method.getName() + " ===");
        // Tạo test mới cho mỗi test case
        ExtentTestManager.saveToReport(method.getName(), "Mô tả test " + method.getName());
    }

    @Test(priority = 1)
    @Story("Login successfully")
    @Description("Verify that user can login successfully with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    public void testLoginSuccess() {
        LogUtils.info("Executing testLoginSuccess - Testing successful login");
        loginPage.loginCMS();
        loginPage.verifyPopupNavigate();
        LogUtils.info("testLoginSuccess completed successfully");
        AllureManager.stepTestPassed("User logged in successfully");
    }

    @Test(priority = 2)
    @Story("Login successfully")
    @Description("Verify that user can login and select management system")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team ")
    public void testLoginAndSelectManagementSystem() {
        LogUtils.info("Executing testLoginAndSelectManagementSystem - Testing login and select management system");
        loginPage.loginWithManagementSystem();
        loginPage.verifyLoginManager();
        LogUtils.info("testLoginAndSelectManagementSystem completed successfully");
        AllureManager.stepTestPassed("User successfully selected management system");
    }

    @Test(priority = 3)
    @Story("Login successfully")
    @Description("Verify that user can login and select sales system")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    public void testLoginAndSelectSalesSystem() {
        LogUtils.info("Executing testLoginAndSelectSalesSystem - Testing login and select sales system");
        loginPage.loginWithSalesSystem();
        loginPage.verifyLoginSale();
        LogUtils.info("testLoginAndSelectSalesSystem completed successfully");
        AllureManager.stepTestPassed("User successfully selected sales system");
    }

    @Test(priority = 4)
    @Story("Login invalid")
    @Description("Verify that user cannot login with invalid email")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testLoginWithInvalidEmail() {
        LogUtils.info("Executing testLoginWithInvalidEmail - Testing login with invalid email");
        loginPage.loginCMS(ConfigData.InvalidEmail, ConfigData.Password);
        loginPage.verifyLoginFail();
        LogUtils.info("testLoginWithInvalidEmail completed successfully");
        AllureManager.stepTestPassed("Invalid email login attempt verified successfully");
    }

    @Test(priority = 5)
    @Story("Login invalid")
    @Description("Verify that user cannot login with invalid password")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testLoginWithInvalidPassword() {
        LogUtils.info("Executing testLoginWithInvalidPassword - Testing login with invalid password");
        loginPage.loginCMS(ConfigData.Email, ConfigData.InvalidPassword);
        loginPage.verifyLoginFail();
        LogUtils.info("testLoginWithInvalidPassword completed successfully");
        AllureManager.stepTestPassed("Invalid password login attempt verified successfully");
    }

    @Test(priority = 6)
    @Story("Login invalid")
    @Description("Verify that user cannot login with both invalid email and password")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testLoginWithBothInvalidCredentials() {
        LogUtils.info("Executing testLoginWithBothInvalidCredentials - Testing login with both invalid credentials");
        loginPage.loginCMS(ConfigData.InvalidEmail, ConfigData.InvalidPassword);
        loginPage.verifyLoginFail();
        LogUtils.info("testLoginWithBothInvalidCredentials completed successfully");
        AllureManager.stepTestPassed("Both invalid credentials login attempt verified successfully");
    }

    @Test(priority = 7)
    @Story("Empty Field Validation")
    @Description("Verify that user cannot login with empty email field")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testLoginWithEmptyEmail() {
        LogUtils.info("Executing testLoginWithEmptyEmail - Testing login with empty email field");
        loginPage.loginCMS("", ConfigData.Password);
        loginPage.verifyNullEmail();
        LogUtils.info("testLoginWithEmptyEmail completed successfully");
        AllureManager.stepTestPassed("Empty email field validation verified successfully");
    }

    @Test(priority = 8)
    @Story("Empty Field Validation")
    @Description("Verify that user cannot login with empty password field")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testLoginWithEmptyPassword() {
        LogUtils.info("Executing testLoginWithEmptyPassword - Testing login with empty password field");
        loginPage.loginCMS(ConfigData.Email, "");
        loginPage.verifyNullPassword();
        LogUtils.info("testLoginWithEmptyPassword completed successfully");
        AllureManager.stepTestPassed("Empty password field validation verified successfully");
    }

    @Test(priority = 9)
    @Story("Empty Field Validation")
    @Description("Verify that user cannot login with both email and password fields empty")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testLoginWithBothEmptyFields() {
        LogUtils.info("Executing testLoginWithBothEmptyFields - Testing login with both empty fields");
        loginPage.loginCMS("", "");
        loginPage.verifyBothEmptyFields();
        LogUtils.info("testLoginWithBothEmptyFields completed successfully");
        AllureManager.stepTestPassed("Both empty fields validation verified successfully");
    }
}
