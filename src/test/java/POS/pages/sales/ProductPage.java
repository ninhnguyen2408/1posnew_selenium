package POS.pages.sales;

import POS.pages.common.BasePage;
import keyworks.ActionKeywords;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {
    
    // Product page elements
    private By productTitle = By.xpath("//h1[normalize-space()='Sản phẩm']");
    private By addProductButton = By.xpath("//button[contains(text(),'Thêm sản phẩm')]");
    private By productNameInput = By.xpath("//input[@name='product_name']");
    private By productPriceInput = By.xpath("//input[@name='price']");
    private By saveProductButton = By.xpath("//button[contains(text(),'Lưu')]");
    private By productTable = By.xpath("//table[@class='product-table']");
    private By searchProductInput = By.xpath("//input[@placeholder='Tìm kiếm sản phẩm']");
    
    public void navigateToProductPage() {
        navigateToProduct();
    }
    
    public void verifyProductPageTitle() {
        ActionKeywords.waitForElementVisible(productTitle);
        ActionKeywords.checkElementDisplayed(productTitle);
        ActionKeywords.assertEquals(ActionKeywords.getTextElement(productTitle), "Sản phẩm", "Product page title not match");
    }
    
    public void clickAddProductButton() {
        ActionKeywords.waitForElementVisible(addProductButton);
        ActionKeywords.clickElement(addProductButton);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void addNewProduct(String productName, String price) {
        clickAddProductButton();
        ActionKeywords.sendKeys(productNameInput, productName);
        ActionKeywords.sendKeys(productPriceInput, price);
        ActionKeywords.clickElement(saveProductButton);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void searchProduct(String productName) {
        ActionKeywords.sendKeys(searchProductInput, productName);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void verifyProductExists(String productName) {
        By productRow = By.xpath("//table//td[contains(text(),'" + productName + "')]");
        ActionKeywords.checkElementDisplayed(productRow);
    }
    
    public void verifyProductNotExists(String productName) {
        By productRow = By.xpath("//table//td[contains(text(),'" + productName + "')]");
        ActionKeywords.checkElementNotDisplayed(productRow);
    }
}