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
    public void testAddIndividualCustomerValid() {
        customerPage.navigateToCustomerPage();
        customerPage.addValidCustomer();
        customerPage.verifyCustomerExists(CustomerTestData.VALID_CUSTOMER_NAME);
    }
    
    @Test
    public void testAddCompanyValid() {
        customerPage.navigateToCustomerPage();
        customerPage.addValidCompany();
        // customerPage.verifyCustomerExists(CustomerTestData.VALID_COMPANY_NAME);
    }
    

}
