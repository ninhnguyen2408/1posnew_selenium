package POS.testdata;

public class ProductTestData {
    
    // Valid product data
    public static final String VALID_PRODUCT_NAME = "Sản phẩm test";
    public static final String VALID_PRODUCT_PRICE = "100000";
    public static final String VALID_PRODUCT_CATEGORY = "Danh mục A";
    
    // Invalid product data
    public static final String INVALID_PRODUCT_NAME = "";
    public static final String INVALID_PRODUCT_PRICE = "-1000";
    public static final String INVALID_PRODUCT_PRICE_TEXT = "abc";
    
    // Test products array
    public static final String[][] TEST_PRODUCTS = {
        {"Bánh mì", "25000"},
        {"Cà phê", "35000"},
        {"Nước ngọt", "15000"},
        {"Trà sữa", "45000"}
    };
    
    public static String[] getValidProductData() {
        return new String[]{VALID_PRODUCT_NAME, VALID_PRODUCT_PRICE};
    }
    
    public static String[] getInvalidProductData() {
        return new String[]{INVALID_PRODUCT_NAME, INVALID_PRODUCT_PRICE};
    }
    
    public static String getRandomProductName() {
        return "Sản phẩm " + System.currentTimeMillis();
    }
    
    public static String getRandomProductPrice() {
        return String.valueOf((int)(Math.random() * 100000) + 10000);
    }
}