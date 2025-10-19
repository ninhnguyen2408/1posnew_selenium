package POS.tests.sales;

import POS.pages.common.LoginPage;
import POS.pages.sales.ProductPage;
import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reports.ExtentTestManager;

import java.lang.reflect.Method;

public class ProductTest extends BaseTest {
    
    private LoginPage loginPage;
    private ProductPage productPage;
    
    @BeforeMethod
    public void setup(Method method) {
        loginPage = new LoginPage();
        productPage = new ProductPage();
        ExtentTestManager.saveToReport(method.getName(), "Test cho module Sản phẩm: " + method.getName());
        
        // Login trước khi test
        loginPage.loginWithManagementSystem();
    }
    
    @Test
    public void testNavigateToProductPage() {
        productPage.navigateToProductPage();
        productPage.verifyProductPageTitle();
    }
    
    @Test
    public void testAddNewProduct() {
        productPage.navigateToProductPage();
        productPage.addNewProduct("Sản phẩm test", "100000");
        productPage.verifyProductExists("Sản phẩm test");
    }
    
    @Test
    public void testSearchProduct() {
        productPage.navigateToProductPage();
        productPage.searchProduct("Sản phẩm test");
    }
}