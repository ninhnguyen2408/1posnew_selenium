package POS.tests;

import POS.pages.common.DashboardPage;
import POS.pages.common.LoginPage;
import common.BaseTest;
import constants.ConfigData;
import listeners.TestListener;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reports.ExtentTestManager;
import utils.LogUtils;

import java.lang.reflect.Method;


@Listeners(TestListener.class)
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
    public void testLoginSuccess() {
        LogUtils.info("Executing testLoginSuccess - Testing successful login");
        loginPage.loginCMS();
        LogUtils.info("testLoginSuccess completed successfully");
    }
    
    @Test
    public void testLoginAndSelectManagementSystem() {
        LogUtils.info("Executing testLoginAndSelectManagementSystem - Testing login and select management system");
        loginPage.loginWithManagementSystem();
        LogUtils.info("testLoginAndSelectManagementSystem completed successfully");
    }
    
    @Test
    public void testLoginAndSelectSalesSystem() {
        LogUtils.info("Executing testLoginAndSelectSalesSystem - Testing login and select sales system");
        loginPage.loginWithSalesSystem();
        LogUtils.info("testLoginAndSelectSalesSystem completed successfully");
    }
    
    @Test
    public void testVerifySystemSelectionPopup() {
        LogUtils.info("Executing testVerifySystemSelectionPopup - Testing system selection popup display");
        loginPage.loginCMS();
        loginPage.verifySystemSelectionPopup();
        LogUtils.info("testVerifySystemSelectionPopup completed successfully");
    }

    @Test
    public void testLoginWithInvalidEmail() {
        LogUtils.info("Executing testLoginWithInvalidEmail - Testing login with invalid email");
        loginPage.loginCMS("admin@example111.com", ConfigData.Password);
        loginPage.verifyLoginFail();
        LogUtils.info("testLoginWithInvalidEmail completed successfully");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        LogUtils.info("Executing testLoginWithInvalidPassword - Testing login with invalid password");
        loginPage.loginCMS(ConfigData.Email, "568690");
        loginPage.verifyLoginFail();
        LogUtils.info("testLoginWithInvalidPassword completed successfully");
    }
    
    @Test
    public void testLoginWithBothInvalidCredentials() {
        LogUtils.info("Executing testLoginWithBothInvalidCredentials - Testing login with both invalid credentials");
        loginPage.loginCMS("invalid@email.com", "wrongpassword");
        loginPage.verifyLoginFail();
        LogUtils.info("testLoginWithBothInvalidCredentials completed successfully");
    }

    @Test
    public void testLoginWithEmptyEmail() {
        LogUtils.info("Executing testLoginWithEmptyEmail - Testing login with empty email field");
        loginPage.testEmptyEmail();
        LogUtils.info("testLoginWithEmptyEmail completed successfully");
    }

    @Test
    public void testLoginWithEmptyPassword() {
        LogUtils.info("Executing testLoginWithEmptyPassword - Testing login with empty password field");
        loginPage.testEmptyPassword();
        LogUtils.info("testLoginWithEmptyPassword completed successfully");
    }
    
    @Test
    public void testLoginWithBothEmptyFields() {
        LogUtils.info("Executing testLoginWithBothEmptyFields - Testing login with both empty fields");
        loginPage.testBothEmptyFields();
        LogUtils.info("testLoginWithBothEmptyFields completed successfully");
    }
}
