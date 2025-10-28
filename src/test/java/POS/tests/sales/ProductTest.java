package POS.tests.sales;

import POS.pages.common.LoginPage;
import POS.pages.sales.ProductPage;
import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reports.ExtentTestManager;
import utils.LogUtils;

import java.lang.reflect.Method;

public class ProductTest extends BaseTest {
    
    private LoginPage loginPage;
    private ProductPage productPage;
    
    @BeforeMethod
    public void setup(Method method) {
        LogUtils.info("=== STARTING TEST: " + method.getName() + " ===");
        loginPage = new LoginPage();
        productPage = new ProductPage();
        ExtentTestManager.saveToReport(method.getName(), "Test cho module Sản phẩm: " + method.getName());
        
        // Login trước khi test
        LogUtils.info("Performing login before test execution");
        loginPage.loginWithManagementSystem();
    }
    
    @Test
    public void testNavigateToProductPage() {
        LogUtils.info("Executing testNavigateToProductPage - Testing navigation to product page");
        productPage.navigateToProductPage();
        productPage.verifyProductPageTitle();
        LogUtils.info("testNavigateToProductPage completed successfully");
    }
    
    @Test
    public void testAddNewProduct() {
        LogUtils.info("Executing testAddNewProduct - Testing adding new product");
        productPage.navigateToProductPage();
        productPage.addNewProduct("Sản phẩm test", "100000");
        productPage.verifyProductExists("Sản phẩm test");
        LogUtils.info("testAddNewProduct completed successfully");
    }
    
    @Test
    public void testSearchProduct() {
        LogUtils.info("Executing testSearchProduct - Testing product search functionality");
        productPage.navigateToProductPage();
        productPage.searchProduct("Sản phẩm test");
        LogUtils.info("testSearchProduct completed successfully");
    }
}