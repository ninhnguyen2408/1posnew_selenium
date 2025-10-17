package POS.pages;

import keyworks.ActionKeywords;
import org.openqa.selenium.By;

public class DashboardPage {
    
    // Dashboard elements
    private By dashboardRevenueReport = By.xpath("(//div[h2[normalize-space(text())='Báo cáo theo dõi doanh thu']])[1]");
    
    public void verifyDashboardElement() {
        ActionKeywords.waitForElementVisible(dashboardRevenueReport);
        ActionKeywords.checkElementDisplayed(dashboardRevenueReport);
        // Verify the revenue report element exists on dashboard
        By revenueReportH2 = By.xpath("(//div[h2[normalize-space(text())='Báo cáo theo dõi doanh thu']])[1]//h2");
        ActionKeywords.assertEquals(ActionKeywords.getTextElement(revenueReportH2), "Báo cáo theo dõi doanh thu", "Dashboard revenue report element not found or text not match");
    }
    
    public void verifyDashboardURL() {
        ActionKeywords.assertEquals(drivers.DriverManager.getDriver().getCurrentUrl(), "https://hdseafood.1erp.vn/dashboard", "Not on Management System URL");
    }
}
