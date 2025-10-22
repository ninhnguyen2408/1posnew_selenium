package POS.tests.sales;

import POS.pages.common.LoginPage;
import POS.pages.sales.CustomerPage;
import POS.testdata.CustomerTestData;
import common.BaseTest;
import keyworks.ActionKeywords;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import reports.ExtentTestManager;


import org.openqa.selenium.By;

import java.lang.reflect.Method;

public class CustomerTest extends BaseTest {
    
    private LoginPage loginPage;
    private CustomerPage customerPage;
    
    @BeforeMethod
    public void setup(Method method) {
        loginPage = new LoginPage();
        customerPage = new CustomerPage();
        ExtentTestManager.saveToReport(method.getName(), "Test cho module Khách hàng: " + method.getName());
        // Login trước khi test
        loginPage.loginWithManagementSystem();
    }
    
    @Test
    public void testNavigateToCustomerPage() {
        customerPage.navigateToCustomerPage();
        customerPage.verifyCustomerPageTitle();
    }

    @Test
    public void testAddCustomerValid() {
        customerPage.navigateToCustomerPage();
        customerPage.addValidCustomer();
        customerPage.verifyCustomerExists(CustomerTestData.VALID_CUSTOMER_NAME);
    }
    
    @Test
    public void testAddCompanyValid() {
        customerPage.navigateToCustomerPage();
        customerPage.addValidCustomer();
        customerPage.verifyCustomerExists(CustomerTestData.VALID_CUSTOMER_NAME);
    }

    @Test
    public void testCheckRadioButton() {
        // Điều hướng đến trang khách hàng
        customerPage.navigateToCustomerPage();
        
        // Nhấn nút "Tạo mới" để mở form thêm khách hàng
        customerPage.clickAddCustomerButton();
        
        // Định nghĩa XPath cho radio button "Nữ"
        By RadioButton = By.xpath("//label[contains(@class, 'ant-radio-wrapper')]//span[normalize-space()='Công ty']");
        
        // Chờ element xuất hiện
        ActionKeywords.waitForElementVisible(RadioButton);
        
        // Kiểm tra element có hiển thị không
        ActionKeywords.checkElementDisplayed(RadioButton);
        
        // Click vào radio button
        ActionKeywords.clickElement(RadioButton);
        
        // Chờ 3 giây để quan sát kết quả
        ActionKeywords.sleep(3);
        
        System.out.println("✅ Radio button 'Nữ' đã được click thành công!");
    }

}
