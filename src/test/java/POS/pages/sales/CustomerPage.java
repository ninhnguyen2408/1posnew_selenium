package POS.pages.sales;

import POS.pages.common.BasePage;
import POS.testdata.CustomerTestData;
import io.reactivex.rxjava3.functions.Action;
import keyworks.ActionKeywords;
import org.openqa.selenium.By;

public class CustomerPage extends BasePage {
    
    // Customer page elements
    private By customerTitle = By.xpath("//h2[normalize-space(text())='Quản lý khách hàng']");
    private By searchCustomerInput = By.xpath("//input[@placeholder='Tìm kiếm khách hàng...']");
    private By addCustomerButton = By.xpath("//button[span[normalize-space(text())='Tạo mới']]");
    private By subTaskButton = By.xpath("//button[span[normalize-space(text())='Tác vụ']]");
    private By subTaskExportButton = By.xpath("//li[span[normalize-space(text())='Xuất khách hàng']]");

    // Add Customer form elements
    private By cancelButton = By.xpath("//button[span[normalize-space(text())='Huỷ']]");
    private By saveCustomerButton = By.xpath("(//button[span[normalize-space(text())='Lưu thông tin']])[1]");
    private By personRadioButton = By.xpath("//label[contains(@class, 'ant-radio-wrapper')]//span[normalize-space()='Cá nhân']");
    private By companyRadioButton = By.xpath("//label[contains(@class, 'ant-radio-wrapper')]//span[normalize-space()='Công ty']");
    private By imageUploadInput = By.xpath("//button[@type='button' and .//span[@aria-label='plus']]");
    private By customerNameInput = By.xpath("//input[@placeholder='Nhập tên']");
    private By companyNameInput = By.xpath("//input[@placeholder='Nhập tên công ty']");
    private By maleRadioButton = By.xpath("//label[.//span[normalize-space(text())='Nam']]");
    private By femaleRadioButton = By.xpath("//label[.//span[normalize-space(text())='Nữ']]");
    private By identificationInput = By.xpath("//input[@name='identification']");
    private By selectDateOfBirth = By.xpath("//input[@placeholder='Chọn ngày sinh']");
    private By phoneNumberInput = By.xpath("//input[@name='phoneNumber']");
    private By emailInput = By.xpath("//input[@name='email']");
    private By taxCodeInput = By.xpath("//input[@name='taxCode']");
    private By addressInput = By.xpath("//input[@name='address']");
    private By cityCombobox = By.xpath("//span[normalize-space(text())='Tỉnh/thành phố']");
    private By wardCombobox = By.xpath("//input[@id='rc_select_1']");
    private By confirmButton = By.xpath("//button[span[text()='Xác nhận']]");
    private By closeButton = By.xpath("//button[span[text()='Đóng']]");

    

    public void navigateToCustomerPage() {
        navigateToCustomer();
    }
    
    public void verifyCustomerPageTitle() {
        ActionKeywords.waitForElementVisible(customerTitle);
        ActionKeywords.checkElementDisplayed(customerTitle);
        ActionKeywords.assertEquals(ActionKeywords.getTextElement(customerTitle), "Quản lý khách hàng", "Customer page title not match");
    }
    
    public void clickAddCustomerButton() {
        ActionKeywords.waitForElementVisible(addCustomerButton);
        ActionKeywords.clickElement(addCustomerButton);
        ActionKeywords.waitForPageLoaded();
    }

    public void addNewCustomer(String customerName, String phone, String email, String dateOfBirth, String identification, String taxCode, String address) {
        clickAddCustomerButton();
        ActionKeywords.clickElement(personRadioButton);
        ActionKeywords.sendKeys(customerNameInput, customerName);
        ActionKeywords.clickElement(femaleRadioButton);
        ActionKeywords.sendKeys(phoneNumberInput, phone);
        ActionKeywords.sendKeys(emailInput, email);
        ActionKeywords.sendKeys(selectDateOfBirth, dateOfBirth);
        ActionKeywords.sendKeys(identificationInput, identification);
        ActionKeywords.sendKeys(taxCodeInput, taxCode);
        ActionKeywords.sendKeys(addressInput, address);
        
        // ActionKeywords.selectDynamicDropdown(cityCombobox, cityCombobox, "Hồ Chí Minh");
        // ActionKeywords.selectDynamicDropdown(wardCombobox, wardCombobox, "Phường 1");

        ActionKeywords.clickElement(saveCustomerButton);
        ActionKeywords.clickElement(confirmButton);
        ActionKeywords.sleep(2);
    }
    
    // Method using TestData for valid individual customer
    public void addValidCustomer() {
        String[] customerData = CustomerTestData.getValidIndividualCustomerData();
        addNewCustomer(customerData[0], customerData[1], customerData[2], customerData[3], customerData[4], customerData[5], customerData[6]);
    }
    
    // Method using TestData for random customer
    public void addRandomCustomer() {
        addNewCustomer(
            CustomerTestData.getRandomCustomerName(),
            CustomerTestData.getRandomCustomerPhone(),
            CustomerTestData.getRandomCustomerEmail(),
            CustomerTestData.VALID_DATE_OF_BIRTH,
            CustomerTestData.VALID_IDENTIFICATION,
            CustomerTestData.VALID_TAX_CODE,
            CustomerTestData.VALID_ADDRESS
        );
    }
    
    
    public void searchCustomer(String customerName) {
        ActionKeywords.sendKeys(searchCustomerInput, customerName);
        ActionKeywords.waitForPageLoaded();
    }
    
    public void verifyCustomerExists(String customerName) {
        By customerRow = By.xpath("//table//td[contains(text(),'" + customerName + "')]");
        ActionKeywords.checkElementDisplayed(customerRow);
    }

}