package POS.tests.sales;

import POS.pages.common.LoginPage;
import POS.pages.sales.CustomerPage;
import POS.testdata.CustomerTestData;
import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import reports.ExtentTestManager;

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
    public void testAddValidIndividualCustomer() {
        customerPage.navigateToCustomerPage();
        customerPage.addValidIndividualCustomer();
        customerPage.verifyCustomerExists(CustomerTestData.VALID_CUSTOMER_NAME);
    }
    
    @Test
    public void testAddRandomCustomer() {
        customerPage.navigateToCustomerPage();
        String randomName = CustomerTestData.getRandomCustomerName();
        customerPage.addNewCustomer(
            randomName,
            CustomerTestData.getRandomCustomerPhone(),
            CustomerTestData.getRandomCustomerEmail(),
            CustomerTestData.VALID_DATE_OF_BIRTH,
            CustomerTestData.VALID_IDENTIFICATION,
            CustomerTestData.VALID_TAX_CODE,
            CustomerTestData.VALID_ADDRESS
        );
        customerPage.verifyCustomerExists(randomName);
    }
    
    @Test(dataProvider = "individualCustomerData")
    public void testAddMultipleIndividualCustomers(String name, String phone, String email, String dob, String id, String tax, String address) {
        customerPage.navigateToCustomerPage();
        customerPage.addNewCustomer(name, phone, email, dob, id, tax, address);
        customerPage.verifyCustomerExists(name);
    }
    
    @Test(dataProvider = "searchKeywords")
    public void testSearchCustomer(String keyword) {
        customerPage.navigateToCustomerPage();
        customerPage.searchCustomer(keyword);
        // Add verification for search results
    }
    
    // Data providers using TestData class
    @DataProvider(name = "individualCustomerData")
    public Object[][] getIndividualCustomerData() {
        return CustomerTestData.INDIVIDUAL_CUSTOMERS;
    }
    
    @DataProvider(name = "searchKeywords")
    public Object[][] getSearchKeywords() {
        Object[][] data = new Object[CustomerTestData.SEARCH_KEYWORDS.length][1];
        for (int i = 0; i < CustomerTestData.SEARCH_KEYWORDS.length; i++) {
            data[i][0] = CustomerTestData.SEARCH_KEYWORDS[i];
        }
        return data;
    }
}
