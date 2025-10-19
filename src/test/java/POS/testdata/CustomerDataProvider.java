package POS.testdata;

import helpers.PropertiesHelper;

public class CustomerDataProvider {
    
    private static final String CUSTOMER_DATA_FILE = "testdata/customer-testdata.properties";
    
    // Load data from properties file based on environment
    public static String[] getEnvironmentCustomerData(String environment) {
        PropertiesHelper.setFile(CUSTOMER_DATA_FILE);
        
        String customerName = PropertiesHelper.getValue(environment + ".customer.name");
        String customerPhone = PropertiesHelper.getValue(environment + ".customer.phone");
        String customerEmail = PropertiesHelper.getValue(environment + ".customer.email");
        String dateOfBirth = PropertiesHelper.getValue("default.date.of.birth");
        String identification = PropertiesHelper.getValue("default.identification");
        String taxCode = PropertiesHelper.getValue("default.tax.code");
        String address = PropertiesHelper.getValue("default.address");
        
        return new String[]{customerName, customerPhone, customerEmail, dateOfBirth, identification, taxCode, address};
    }
    
    // Get data based on current environment
    public static String[] getCurrentEnvironmentCustomerData() {
        String currentEnv = System.getProperty("test.environment", "dev");
        return getEnvironmentCustomerData(currentEnv);
    }
    
    // Combine multiple data sources
    public static Object[][] getCombinedTestData() {
        // Combine data from CustomerTestData class and properties file
        Object[][] staticData = CustomerTestData.INDIVIDUAL_CUSTOMERS;
        String[] envData = getCurrentEnvironmentCustomerData();
        
        // Create combined array
        Object[][] combinedData = new Object[staticData.length + 1][];
        
        // Copy static data
        System.arraycopy(staticData, 0, combinedData, 0, staticData.length);
        
        // Add environment data
        combinedData[staticData.length] = envData;
        
        return combinedData;
    }
}