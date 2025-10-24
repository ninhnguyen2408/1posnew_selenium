package POS.testdata;

public class CustomerTestData {
    
    // ===== INDIVIDUAL CUSTOMER DATA =====
    public static final String VALID_CUSTOMER_NAME = "Nguyễn Văn A";
    public static final String VALID_CUSTOMER_PHONE = "0123456789";
    public static final String VALID_CUSTOMER_EMAIL = "customer@test.com";
    public static final String VALID_DATE_OF_BIRTH = "01/01/1990";
    public static final String VALID_IDENTIFICATION = "123456789012";
    public static final String VALID_TAX_CODE = "1234567890";
    public static final String VALID_ADDRESS = "123 Đường ABC, Quận 1, TP.HCM";
    
    // ===== COMPANY CUSTOMER DATA =====
    public static final String VALID_COMPANY_NAME = "Công ty TNHH ABC";
    public static final String VALID_COMPANY_PHONE = "0987654321";
    public static final String VALID_COMPANY_CUSTOMER_NAME = "Nguyễn Văn Ninh";
    public static final String VALID_COMPANY_EMAIL = "company@test.com";
    public static final String VALID_COMPANY_TAX_CODE = "0123456789";
    public static final String VALID_COMPANY_ADDRESS = "456 Đường XYZ, Quận 2, TP.HCM";
    
    // ===== INVALID DATA FOR TESTING =====
    public static final String INVALID_CUSTOMER_NAME = "";
    public static final String INVALID_CUSTOMER_PHONE = "123";
    public static final String INVALID_CUSTOMER_EMAIL = "invalid-email";
    public static final String INVALID_DATE_OF_BIRTH = "32/13/2025";
    public static final String INVALID_IDENTIFICATION = "123";
    public static final String INVALID_TAX_CODE = "abc";
    
    // ===== MULTIPLE TEST DATA ARRAYS =====
    public static final Object[][] INDIVIDUAL_CUSTOMERS = {
        {"Trần Thị B", "0987654321", "tranb@email.com", "15/03/1985", "987654321098", "2345678901", "789 Đường DEF, Quận 3, TP.HCM"},
        {"Lê Văn C", "0912345678", "lec@email.com", "20/07/1992", "456789012345", "3456789012", "321 Đường GHI, Quận 4, TP.HCM"},
        {"Phạm Thị D", "0934567890", "phamd@email.com", "10/12/1988", "789012345678", "4567890123", "654 Đường JKL, Quận 5, TP.HCM"}
    };
    
    public static final Object[][] COMPANY_CUSTOMERS = {
        {"Công ty TNHH XYZ", "0911222333", "xyz@company.com", "5678901234", "987 Đường MNO, Quận 6, TP.HCM"},
        {"Công ty CP ABC", "0922333444", "abc@corp.com", "6789012345", "159 Đường PQR, Quận 7, TP.HCM"}
    };
    
    // ===== HELPER METHODS =====
    public static String[] getValidIndividualCustomerData() {
        return new String[]{VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE, VALID_CUSTOMER_EMAIL, 
                           VALID_DATE_OF_BIRTH, VALID_IDENTIFICATION, VALID_TAX_CODE, VALID_ADDRESS};
    }
    
    public static String[] getValidCompanyCustomerData() {
        return new String[]{VALID_COMPANY_NAME, VALID_COMPANY_PHONE, VALID_COMPANY_EMAIL, VALID_COMPANY_CUSTOMER_NAME,
                           VALID_COMPANY_TAX_CODE, VALID_COMPANY_ADDRESS};
    }
    
    // ===== RANDOM DATA GENERATORS =====
    public static String getRandomCustomerName() {
        return "Khách hàng " + System.currentTimeMillis();
    }
    
    public static String getRandomCustomerPhone() {
        return "09" + String.valueOf((long)(Math.random() * 100000000L + 10000000L));
    }
    
    public static String getRandomCustomerEmail() {
        return "customer" + System.currentTimeMillis() + "@test.com";
    }
}