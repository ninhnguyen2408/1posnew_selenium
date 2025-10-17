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
    
    // Popup system selection elements
    private By systemManagement = By.xpath("//div[normalize-space(text())='Hệ thống quản trị']");
    private By systemSales = By.xpath("//div[normalize-space(text())='Hệ thống bán hàng']");

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
        return new DashboardPage();
    }
    
    public DashboardPage loginWithManagementSystem() {
        getBrowser();
        ActionKeywords.sendKeys(inputEmail, ConfigData.Email);
        ActionKeywords.sendKeys(inputPassword, ConfigData.Password);
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        
        // Wait for popup and select Management System
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
        // Click login button first to trigger validation
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        
        // Check if we're still on login page (validation failed)
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("login"), "Should stay on login page when email is empty");
        
        // Try to check for validation message or required attribute
        if (ActionKeywords.verifyHTML5RequiredField(inputEmail)) {
            ActionKeywords.assertEquals(ActionKeywords.getHTML5MessageField(inputEmail), "Vui lòng nhập tên đăng nhập", "Validation message of Email not match");
        }
        ActionKeywords.sleep(2);
    }

    public void verifyNullPassword() {
        // Click login button first to trigger validation
        ActionKeywords.clickElement(buttonLogin);
        ActionKeywords.waitForPageLoaded();
        
        // Check if we're still on login page (validation failed)
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("login"), "Should stay on login page when password is empty");
        
        // Try to check for validation message or required attribute
        if (ActionKeywords.verifyHTML5RequiredField(inputPassword)) {
            ActionKeywords.assertEquals(ActionKeywords.getHTML5MessageField(inputPassword), "Vui lòng nhập mật khẩu", "Validation message of Password not match");
        }
        ActionKeywords.sleep(2);
    }

    private void verifyLoginSuccess() {
        Assert.assertFalse(DriverManager.getDriver().getCurrentUrl().contains("login"), "Login fail");
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
