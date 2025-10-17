package POS.tests;

import POS.pages.DashboardPage;
import POS.pages.LoginPage;
import common.BaseTest;
import constants.ConfigData;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reports.ExtentTestManager;

import java.lang.reflect.Method;

public class LoginTest extends BaseTest {
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();


    @BeforeMethod
    public void setup(Method method) {
        // Tạo test mới cho mỗi test case
        ExtentTestManager.saveToReport(method.getName(), "Mô tả test " + method.getName());
    }


    @Test
    public void testLoginSuccess() {
        loginPage.loginCMS();
    }
    
    @Test
    public void testLoginAndSelectManagementSystem() {
        loginPage.loginWithManagementSystem();
    }
    
    @Test
    public void testLoginAndSelectSalesSystem() {
        loginPage.loginWithSalesSystem();
    }
    
    @Test
    public void testVerifySystemSelectionPopup() {
        loginPage.loginCMS();
        loginPage.verifySystemSelectionPopup();
    }

    @Test
    public void testLoginWithInvalidEmail() {
        loginPage.loginCMS("admin@example111.com", ConfigData.Password);
        loginPage.verifyLoginFail();
    }

    @Test
    public void testLoginWithInvalidPassword() {
        loginPage.loginCMS(ConfigData.Email, "568690");
        loginPage.verifyLoginFail();
    }
    
    @Test
    public void testLoginWithBothInvalidCredentials() {
        loginPage.loginCMS("invalid@email.com", "wrongpassword");
        loginPage.verifyLoginFail();
    }

    @Test
    public void testLoginWithEmptyEmail() {
        loginPage.testEmptyEmail();
    }

    @Test
    public void testLoginWithEmptyPassword() {
        loginPage.testEmptyPassword();
    }
    
    @Test
    public void testLoginWithBothEmptyFields() {
        loginPage.testBothEmptyFields();
    }
}
