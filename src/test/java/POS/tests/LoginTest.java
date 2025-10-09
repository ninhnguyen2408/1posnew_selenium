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
    public void testLoginSucess() {
        loginPage.loginCMS();
    }

    @Test
    public void testLoginWithEmailInvalid() {
        loginPage.loginCMS("admin@example111.com", ConfigData.Password);
        loginPage.verifyLoginFail();
    }

    @Test
    public void testLoginWithPasswordInvalid() {
        loginPage.loginCMS(ConfigData.Email, "568690");
        loginPage.verifyLoginFail();
    }

    @Test
    public void testEmailNull() {
        loginPage.loginCMS("", ConfigData.Password);
        loginPage.verifyNullEmail();
    }

    @Test
    public void testIncorrectFormatEmail() {
        loginPage.loginCMS("abc", ConfigData.Password);
        loginPage.incorrectFormatEmail();
    }

    @Test
    public void testPasswordNull() {
        loginPage.loginCMS(ConfigData.Email, "");
        loginPage.verifyNullPassword();
    }
}
