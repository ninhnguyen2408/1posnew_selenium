package POS.pages.common;

import constants.ConfigData;
import constants.ErrorMessages;
import keyworks.ActionKeywords;

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
        performLoginAction(email, password);
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        if (currentUrl.contains("login")) {
            LogUtils.warn("Login failed");
        } else {
            LogUtils.info("Login completed successfully");
        }
        return new DashboardPage();
    }
    
    private void performLoginAction(String email, String password) {
        getBrowser();
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        ActionKeywords.waitForPageLoaded();
        LogUtils.info("Login form submitted and page loaded");
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
    
    public void verifyBothEmptyFields() {
        LogUtils.info("Performing strict verification of both required field errors...");
        boolean emailErrorDisplayed = ActionKeywords.checkElementDisplayed(errorMessageRequiredEmail);
        boolean passwordErrorDisplayed = ActionKeywords.checkElementDisplayed(errorMessageRequiredPassword);     
        Assert.assertTrue(emailErrorDisplayed, "Email required error message not displayed");
        Assert.assertTrue(passwordErrorDisplayed, "Password required error message not displayed");
        LogUtils.info("Both email and password required errors verified successfully");
        ActionKeywords.sleep(1);
    }

    
    public void verifyLoginFail() {
        ActionKeywords.checkElementDisplayed(alertMessage);
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"), "Fail, NOT on the Login page");
        String actualErrorMessage = ActionKeywords.getTextElement(alertMessage);

        ActionKeywords.assertEquals(actualErrorMessage, ErrorMessages.LOGIN_FAILED, "Content of alert message not match");
        LogUtils.info("Error message content verified successfully");
        ActionKeywords.sleep(1);
    }
}
