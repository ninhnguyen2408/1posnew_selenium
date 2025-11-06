package POS.pages.common;

import constants.ConfigData;
import keyworks.ActionKeywords;
import org.openqa.selenium.By;
import org.testng.Assert;
import drivers.DriverManager;
import utils.LogUtils;

public class LoginPage {

    private By inputEmail = By.xpath("//input[@name='email']");
    private By inputPassword = By.xpath("//input[@name='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Đăng nhập']");
    private By alertMessage = By.xpath("//div[@role='alert']");
    private By errorMessageRequiredEmail = By.xpath("//p[normalize-space(text())='Vui lòng nhập tên đăng nhập']");
    private By errorMessageRequiredPassword = By.xpath("//p[normalize-space(text())='Vui lòng nhập mật khẩu']");
    
    // Popup system selection elements
    private By systemManagement = By.xpath("//div[normalize-space(text())='Hệ thống quản trị']");
    private By systemSales = By.xpath("//div[normalize-space(text())='Hệ thống bán hàng']");

    private void getBrowser(){
        LogUtils.info("Opening browser and navigating to URL: " + ConfigData.URL);
        ActionKeywords.openURL(ConfigData.URL);
        ActionKeywords.waitForPageLoaded();
        ActionKeywords.assertEquals(ActionKeywords.getTextElement(buttonLogin), "Đăng nhập", "NOT the Login page");
        LogUtils.info("Successfully loaded login page");
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
        LogUtils.info("Attempting to login with default credentials");
        getBrowser();
        ActionKeywords.sendKeys(inputEmail, ConfigData.Email);
        ActionKeywords.sendKeys(inputPassword, ConfigData.Password);
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        LogUtils.info("Login with default credentials completed successfully");
        return new DashboardPage();
    }
    
    public DashboardPage loginWithManagementSystem() {
        LogUtils.info("Attempting to login and select Management System");
        getBrowser();
        ActionKeywords.sendKeys(inputEmail, ConfigData.Email);
        ActionKeywords.sendKeys(inputPassword, ConfigData.Password);
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        
        // Wait for popup and select Management System
        LogUtils.info("Selecting Management System from popup");
        ActionKeywords.waitForElementVisible(systemManagement);
        ActionKeywords.clickElement(systemManagement);
        ActionKeywords.waitForPageLoaded();
        
        // Wait 3 seconds after selecting management system
        ActionKeywords.sleep(3);
        
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.verifyDashboardURL();
        dashboardPage.verifyDashboardElement();
        return dashboardPage;
    }
    
    public DashboardPage loginWithSalesSystem() {
        getBrowser();
        ActionKeywords.sendKeys(inputEmail, ConfigData.Email);
        ActionKeywords.sendKeys(inputPassword, ConfigData.Password);
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        
        // Wait for popup and select Sales System  
        ActionKeywords.waitForElementVisible(systemSales);
        ActionKeywords.clickElement(systemSales);
        ActionKeywords.waitForPageLoaded();
        verifySalesSystemURL();
        return new DashboardPage();
    }


    public void verifyNullEmail() {
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();

        boolean check = ActionKeywords.checkElementExist(errorMessageRequiredEmail);
        Assert.assertTrue(check, "Error message for required email not displayed.");
    }

    public void verifyNullPassword(){
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