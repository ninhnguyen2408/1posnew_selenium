package POS.pages;

import constants.ConfigData;
import keyworks.ActionKeywords;
import org.openqa.selenium.By;
import org.testng.Assert;
import drivers.DriverManager;

public class LoginPage {

//    private By headerPage = By.xpath("//h1[normalize-space()='Đăng nhập']");
    private By inputEmail = By.xpath("//input[@name='email']");
    private By inputPassword = By.xpath("//input[@name='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Đăng nhập']");
    private By alertMessage = By.xpath("//div[@role='alert']");

    private void getBrowser(){
        ActionKeywords.openURL(ConfigData.URL);
        ActionKeywords.waitForPageLoaded();
        ActionKeywords.assertEquals(ActionKeywords.getTextElement(buttonLogin), "Đăng nhập", "NOT the Login page");
    }

    public DashboardPage loginCMS(String email, String password) {
        getBrowser();
        ActionKeywords.sendKeys(inputEmail, email);
        ActionKeywords.sendKeys(inputPassword, password);
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        return new DashboardPage();
    }

    public DashboardPage loginCMS() {
        getBrowser();
        ActionKeywords.sendKeys(inputEmail, ConfigData.Email);
        ActionKeywords.sendKeys(inputPassword, ConfigData.Password);
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        verifyLoginSucess();
        return new DashboardPage();
    }

    public void verifyNullEmail() {
        Assert.assertTrue(ActionKeywords.verifyHTML5RequiredField(inputEmail), "Email is NOT a required field");
        ActionKeywords.assertEquals(ActionKeywords.getHTML5MessageField(inputEmail), "Vui lòng nhập tên đăng nhập", "Validation message of Email not match");
        ActionKeywords.sleep(2);
    }

    public void verifyNullPassword() {
        Assert.assertTrue(ActionKeywords.verifyHTML5RequiredField(inputPassword), "Password  is NOT a required field");
        ActionKeywords.assertEquals(ActionKeywords.getHTML5MessageField(inputPassword), "Vui lòng nhập mật khẩu", "Validation message of Password not match");
        ActionKeywords.sleep(2);
    }

    public void incorrectFormatEmail() {
        Assert.assertTrue(ActionKeywords.verifyHTML5RequiredField(inputEmail), "Validation message of incorrect format Email NOT exists");
        ActionKeywords.assertEquals(ActionKeywords.getHTML5MessageField(inputEmail), "Đăng nhập thất bại\n" +
                "Email/Số điện thoại hoặc mật khẩu không đúng. Tài khoản của bạn sẽ bị khóa nếu nhập sai 5 lần", "Validation message of incorrect format Email not match");
        ActionKeywords.sleep(2);
    }

    private void verifyLoginSucess() {
        Assert.assertFalse(DriverManager.getDriver().getCurrentUrl().contains("login"), "Login fail");
    }

    public void verifyLoginFail() {
        ActionKeywords.checkElementDisplayed(alertMessage);
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("login"), "Fail, NOT on the Login page");
        ActionKeywords.assertEquals(ActionKeywords.getTextElement(alertMessage), "Đăng nhập thất bại\n" +
                "Email/Số điện thoại hoặc mật khẩu không đúng. Tài khoản của bạn sẽ bị khóa nếu nhập sai 5 lần", "Content of alert message not match");
        ActionKeywords.sleep(2);
    }

}
