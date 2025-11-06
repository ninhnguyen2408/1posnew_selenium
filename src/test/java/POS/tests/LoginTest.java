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


    @Test
    @Story("Valid Login")
    @Description("Verify that user can login successfully with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    public void testLoginSuccess() {
        LogUtils.info("Executing testLoginSuccess - Testing successful login");
        loginPage.loginCMS();
        LogUtils.info("testLoginSuccess completed successfully");
        AllureManager.stepTestPassed("User logged in successfully");
    }
    
    @Test
    @Story("System Selection")
    @Description("Verify that user can login and select management system")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    public void testLoginAndSelectManagementSystem() {
        LogUtils.info("Executing testLoginAndSelectManagementSystem - Testing login and select management system");
        loginPage.loginWithManagementSystem();
        LogUtils.info("testLoginAndSelectManagementSystem completed successfully");
        AllureManager.stepTestPassed("User successfully selected management system");
    }
    

    @Test
    @Story("Invalid Login")
    @Description("Verify that user cannot login with invalid email address")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testLoginWithInvalidEmail() {
        LogUtils.info("Executing testLoginWithInvalidEmail - Testing login with invalid email");
        loginPage.loginCMS("admin@example111.com", ConfigData.Password);
        loginPage.verifyLoginFail();
        LogUtils.info("testLoginWithInvalidEmail completed successfully");
        AllureManager.stepTestPassed("Invalid email login attempt verified successfully");
    }

    @Test
    @Story("Invalid Login")
    @Description("Verify that user cannot login with invalid password")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testLoginWithInvalidPassword() {
        LogUtils.info("Executing testLoginWithInvalidPassword - Testing login with invalid password");
        loginPage.loginCMS(ConfigData.Email, "568690");
        loginPage.verifyLoginFail();
        LogUtils.info("testLoginWithInvalidPassword completed successfully");
        AllureManager.stepTestPassed("Invalid password login attempt verified successfully");
    }
    
    @Test
    @Story("Invalid Login")
    @Description("Verify that user cannot login with both invalid email and password")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testLoginWithBothInvalidCredentials() {
        LogUtils.info("Executing testLoginWithBothInvalidCredentials - Testing login with both invalid credentials");
        loginPage.loginCMS("invalid@email.com", "wrongpassword");
        loginPage.verifyLoginFail();
        LogUtils.info("testLoginWithBothInvalidCredentials completed successfully");
        AllureManager.stepTestPassed("Both invalid credentials login attempt verified successfully");
    }

    @Test
    @Story("Empty Field Validation")
    @Description("Verify that user cannot login with empty email field")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testLoginWithEmptyEmail() {
        LogUtils.info("Executing testLoginWithEmptyEmail - Testing login with empty email field");
        loginPage.testEmptyEmail();
        loginPage.verifyNullEmail();
        LogUtils.info("testLoginWithEmptyEmail completed successfully");
        AllureManager.stepTestPassed("Empty email field validation verified successfully");
    }

    @Test
    @Story("Empty Field Validation")
    @Description("Verify that user cannot login with empty password field")
    @Severity(SeverityLevel.NORMAL)
    @Owner("QA Team")
    public void testLoginWithEmptyPassword() {
        LogUtils.info("Executing testLoginWithEmptyPassword - Testing login with empty password field");
        loginPage.testEmptyPassword();
        LogUtils.info("testLoginWithEmptyPassword completed successfully");
        AllureManager.stepTestPassed("Empty password field validation verified successfully");
    }
    
    @Test
    @Story("Empty Field Validation")
    @Description("Verify that user cannot login with both email and password fields empty")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("QA Team")
    public void testLoginWithBothEmptyFields() {
        LogUtils.info("Executing testLoginWithBothEmptyFields - Testing login with both empty fields");
        loginPage.testBothEmptyFields();
        LogUtils.info("testLoginWithBothEmptyFields completed successfully");
        AllureManager.stepTestPassed("Both empty fields validation verified successfully");
    }
}
