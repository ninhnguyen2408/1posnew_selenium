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
import utils.LogUtils;

import org.openqa.selenium.By;

import java.lang.reflect.Method;

public class CustomerTest extends BaseTest {
    
    private LoginPage loginPage;
    private CustomerPage customerPage;
    
    @BeforeMethod
    public void setup(Method method) {
        LogUtils.info("=== STARTING TEST: " + method.getName() + " ===");
        loginPage = new LoginPage();
        customerPage = new CustomerPage();
        ExtentTestManager.saveToReport(method.getName(), "Test cho module Khách hàng: " + method.getName());
        // Login trước khi test
        LogUtils.info("Performing login before test execution");
        loginPage.loginWithManagementSystem();
    }
    
    @Test
    public void testNavigateToCustomerPage() {
        LogUtils.info("Executing testNavigateToCustomerPage - Testing navigation to customer page");
        customerPage.navigateToCustomerPage();
        customerPage.verifyCustomerPageTitle();
        LogUtils.info("testNavigateToCustomerPage completed successfully");
    }

    @Test
    public void testAddIndividualCustomerValid() {
        LogUtils.info("Executing testAddIndividualCustomerValid - Testing adding valid individual customer");
        customerPage.navigateToCustomerPage();
        customerPage.addValidCustomer();
        customerPage.verifyCustomerExists(CustomerTestData.VALID_CUSTOMER_NAME);
        LogUtils.info("testAddIndividualCustomerValid completed successfully");
    }
    
    @Test
    public void testAddCompanyValid() {
        LogUtils.info("Executing testAddCompanyValid - Testing adding valid company customer");
        customerPage.navigateToCustomerPage();
        customerPage.addValidCompany();
        // customerPage.verifyCustomerExists(CustomerTestData.VALID_COMPANY_NAME);
        LogUtils.info("testAddCompanyValid completed successfully");
    }
    

}
