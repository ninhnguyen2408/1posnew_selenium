package POS.pages.common;

import constants.ConfigData;
import keyworks.ActionKeywords;

import java.awt.Desktop.Action;

import org.openqa.selenium.By;
import org.testng.Assert;
import drivers.DriverManager;
import utils.LogUtils;

public class LoginPage extends  BasePage{

    private By inputEmail = By.xpath("//input[@name='email']");
    private By inputPassword = By.xpath("//input[@name='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Đăng nhập']");
    private By alertMessage = By.xpath("//div[@role='alert']");
    private By errorMessageRequiredEmail = By.xpath("//p[normalize-space(text())='Vui lòng nhập tên đăng nhập']");
    private By errorMessageRequiredPassword = By.xpath("//p[normalize-space(text())='Vui lòng nhập mật khẩu']");

    

    
    // Popup system selection elements
    private By titlePopup = By.xpath("//span[normalize-space(text())='Chọn hệ thống bạn muốn truy cập']");
    private By systemManagement = By.xpath("//div[normalize-space(text())='Hệ thống quản trị']");
    private By systemSales = By.xpath("//div[normalize-space(text())='Hệ thống bán hàng']");

    private void getBrowser(){
        ActionKeywords.openURL(ConfigData.URL);
        ActionKeywords.waitForPageLoaded();
        ActionKeywords.waitForElementVisible(buttonLogin);
        LogUtils.info("Opening browser and navigating to URL: " + ConfigData.URL);
    }

    private void enterEmail(String email) {
        ActionKeywords.sendKeys(inputEmail, email);
        LogUtils.info("Entered email: " + (email.isEmpty() ? "[EMPTY]" : email));
    }

    private void enterPassword(String password) {
        ActionKeywords.sendKeys(inputPassword, password);
        LogUtils.info("Entered password: " + (password.isEmpty() ? "[EMPTY]" : "****"));
    }

    private void clickLoginButton() {
        ActionKeywords.clickElement(buttonLogin);
        LogUtils.info("Clicked on Login button");
    }

    public DashboardPage loginCMS(String email, String password) {
        LogUtils.info("Attempting to login with email: " + email);
        getBrowser();
        ActionKeywords.sendKeys(inputEmail, email);
        ActionKeywords.sendKeys(inputPassword, password);
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        LogUtils.info("Login completed successfully");
        return new DashboardPage();
    }

    public DashboardPage loginCMS() {
        getBrowser();
        enterEmail(ConfigData.Email);
        enterPassword(ConfigData.Password);
        clickLoginButton();
        ActionKeywords.waitForPageLoaded();
        LogUtils.info("Login with default credentials completed successfully");
        return new DashboardPage();
    }
    
    public DashboardPage loginWithManagementSystem() {
        LogUtils.info("Attempting to login and select Management System");
        getBrowser();
        enterEmail(ConfigData.Email);
        enterPassword(ConfigData.Password);
        clickLoginButton();
        ActionKeywords.waitForPageLoaded();
        
        // Wait for popup and select Management System
        LogUtils.info("Selecting Management System from popup");
        ActionKeywords.waitForElementVisible(systemManagement);
        ActionKeywords.clickElement(systemManagement);
        ActionKeywords.waitForPageLoaded();
        
        return new DashboardPage();
    }
    
    public void loginWithSalesSystem() {
        LogUtils.info("Attempting to login and select sales System");
        getBrowser();
        ActionKeywords.sendKeys(inputEmail, ConfigData.Email);
        ActionKeywords.sendKeys(inputPassword, ConfigData.Password);
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        
        // Wait for popup and select Sales System  
        ActionKeywords.waitForElementVisible(systemSales);
        ActionKeywords.clickElement(systemSales);
        ActionKeywords.waitForPageLoaded();
    }

    // kiểm tra lại test case

    public void verifyPopupNavigate(){
        ActionKeywords.waitForPageLoaded();
        boolean check = ActionKeywords.checkElementDisplayed(titlePopup);
        Assert.assertTrue(check, "Popup is not display");
    }

    public void verifyLoginManager() {
        boolean check = ActionKeywords.checkElementDisplayed(salesManagerDashboard);
        Assert.assertTrue(check, "Login manager failed or Dashboard not displayed.");
    }

    public void verifyLoginSale() {
        boolean check = ActionKeywords.checkElementDisplayed(selectStore);
        Assert.assertTrue(check, "Login sales failed or select store not displayed.");
        
        ActionKeywords.sleep(2);
    }

    public void verifyNullEmail() {
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        boolean check = ActionKeywords.checkElementExist(errorMessageRequiredEmail);
        Assert.assertTrue(check, "Error message for required email not displayed.");
    }

    public void verifyNullPassword(){
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        boolean check = ActionKeywords.checkElementDisplayed(errorMessageRequiredPassword);
        Assert.assertTrue(check, "Error message for required password not displayed.");
    }

    private void verifySalesSystemURL() {
        ActionKeywords.assertEquals(DriverManager.getDriver().getCurrentUrl(), "https://hdseafood.1erp.vn/choose-store", "Not on Sales System URL");
    }
    
    public void verifySystemSelectionPopup() {
        ActionKeywords.waitForElementVisible(systemManagement);
        ActionKeywords.waitForElementVisible(systemSales);
        ActionKeywords.checkElementDisplayed(systemManagement);
        ActionKeywords.checkElementDisplayed(systemSales);
        ActionKeywords.assertEquals(ActionKeywords.getTextElement(systemManagement), "Hệ thống quản trị", "Management system option text not match");
        ActionKeywords.assertEquals(ActionKeywords.getTextElement(systemSales), "Hệ thống bán hàng", "Sales system option text not match");
    }
    
    public void testEmptyEmail() {
        getBrowser();
        ActionKeywords.sendKeys(inputEmail, "");
        ActionKeywords.sendKeys(inputPassword, ConfigData.Password);
        verifyNullEmail();
    }
    
    public void testEmptyPassword() {
        getBrowser();
        ActionKeywords.sendKeys(inputEmail, ConfigData.Email);
        ActionKeywords.sendKeys(inputPassword, "");
        verifyNullPassword();
    }
    
    public void testBothEmptyFields() {
        getBrowser();
        ActionKeywords.sendKeys(inputEmail, "");
        ActionKeywords.sendKeys(inputPassword, "");
        verifyNullEmail();
    }

    public void verifyLoginFail() {
        ActionKeywords.checkElementDisplayed(alertMessage);
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("login"), "Fail, NOT on the Login page");
        ActionKeywords.assertEquals(ActionKeywords.getTextElement(alertMessage), "Đăng nhập thất bại\n" +
                "Email/Số điện thoại hoặc mật khẩu không đúng. Tài khoản của bạn sẽ bị khóa nếu nhập sai 5 lần", "Content of alert message not match");
        ActionKeywords.sleep(2);
    }
}