package POS.pages.common;

import keyworks.ActionKeywords;
import org.openqa.selenium.By;

public class BasePage {
    
    // Common navigation elements - Main modules
    protected By salesModule = By.xpath("//span[normalize-space()='Điểm bán hàng']");
    protected By staffModule = By.xpath("//span[normalize-space()='Nhân viên']");
    protected By settingsModule = By.xpath("//span[normalize-space()='Thiết lập']");
    protected By warehouseModule = By.xpath("//span[normalize-space()='Kho vận']");
    protected By financeModule = By.xpath("//span[normalize-space()='Tài chính']");
    
    // Sales module sub-menu items
    protected By salesDashboard = By.xpath("//a[normalize-space()='Dashboard']");
    protected By salesPoint = By.xpath("//a[normalize-space()='Điểm bán hàng']");
    protected By product = By.xpath("//a[normalize-space()='Sản phẩm']");
    protected By order = By.xpath("//a[normalize-space()='Đơn hàng']");
    protected By customer = By.xpath("//a[normalize-space()='Khách hàng']");
    
    // Settings module sub-menu items
    protected By generalSettings = By.xpath("//a[normalize-space()='Thiết lập chung']");
    protected By salesPointSettings = By.xpath("//a[normalize-space()='Thiết lập điểm bán']");
    
    // Warehouse module sub-menu items
    protected By warehouse = By.xpath("//a[normalize-space()='Kho vận']");
    protected By batch = By.xpath("//a[normalize-space()='Lô hàng hoá']");
    protected By supplier = By.xpath("//a[normalize-space()='Nhà cung cấp']");
    protected By importGoods = By.xpath("//a[normalize-space()='Nhập hàng']");
    protected By returnGoods = By.xpath("//a[normalize-space()='Trả hàng']");
    protected By inventory = By.xpath("//a[normalize-space()='Tồn kho']");
    
    // Finance module sub-menu items
    protected By financeManagement = By.xpath("//a[normalize-space()='Quản lý tài chính']");
    protected By expense = By.xpath("//a[normalize-space()='Chi phí']");
    
    // Navigate to main modules
    public void navigateToSalesModule() {
        ActionKeywords.clickElement(salesModule);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void navigateToStaffModule() {
        ActionKeywords.clickElement(staffModule);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void navigateToSettingsModule() {
        ActionKeywords.clickElement(settingsModule);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void navigateToWarehouseModule() {
        ActionKeywords.clickElement(warehouseModule);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void navigateToFinanceModule() {
        ActionKeywords.clickElement(financeModule);
        ActionKeywords.waitForPageLoaded();
    }
    
    // Navigate to sales sub-modules
    public void navigateToSalesDashboard() {
        ActionKeywords.mouseHover(salesModule);
        ActionKeywords.waitForElementVisible(salesDashboard);
        ActionKeywords.clickElement(salesDashboard);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void navigateToSalesPoint() {
        ActionKeywords.mouseHover(salesModule);
        ActionKeywords.waitForElementVisible(salesPoint);
        ActionKeywords.clickElement(salesPoint);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void navigateToProduct() {
        ActionKeywords.mouseHover(salesModule);
        ActionKeywords.waitForElementVisible(product);
        ActionKeywords.clickElement(product);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void navigateToOrder() {
        ActionKeywords.mouseHover(salesModule);
        ActionKeywords.waitForElementVisible(order);
        ActionKeywords.clickElement(order);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void navigateToCustomer() {
        ActionKeywords.clickElement(customer);
        ActionKeywords.waitForPageLoaded();
    }
    
    // Navigate to settings sub-modules
    public void navigateToGeneralSettings() {
        ActionKeywords.mouseHover(settingsModule);
        ActionKeywords.waitForElementVisible(generalSettings);
        ActionKeywords.clickElement(generalSettings);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void navigateToSalesPointSettings() {
        ActionKeywords.mouseHover(settingsModule);
        ActionKeywords.waitForElementVisible(salesPointSettings);
        ActionKeywords.clickElement(salesPointSettings);
        ActionKeywords.waitForPageLoaded();
    }
    
    // Navigate to warehouse sub-modules
    public void navigateToWarehouse() {
        ActionKeywords.mouseHover(warehouseModule);
        ActionKeywords.waitForElementVisible(warehouse);
        ActionKeywords.clickElement(warehouse);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void navigateToSupplier() {
        ActionKeywords.mouseHover(warehouseModule);
        ActionKeywords.waitForElementVisible(supplier);
        ActionKeywords.clickElement(supplier);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void navigateToInventory() {
        ActionKeywords.mouseHover(warehouseModule);
        ActionKeywords.waitForElementVisible(inventory);
        ActionKeywords.clickElement(inventory);
        ActionKeywords.waitForPageLoaded();
    }
    
    // Navigate to finance sub-modules  
    public void navigateToFinanceManagement() {
        ActionKeywords.mouseHover(financeModule);
        ActionKeywords.waitForElementVisible(financeManagement);
        ActionKeywords.clickElement(financeManagement);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void navigateToExpense() {
        ActionKeywords.mouseHover(financeModule);
        ActionKeywords.waitForElementVisible(expense);
        ActionKeywords.clickElement(expense);
        ActionKeywords.waitForPageLoaded();
    }
}