package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationResultPage extends BasePage {

    // Locators
    private By modalTitle = By.id("example-modal-sizes-label-lg");
    private By closeButton = By.id("closeLargeModal");

    private String tableValueXpath = "//td[text()='%s']/following-sibling::td";

    public RegistrationResultPage(WebDriver driver) {
        super(driver);
        waitForVisibility(modalTitle);
    }


    public String getModalTitle() {
        return getText(modalTitle);
    }


    public String getSubmittedData(String label) {
        By locator = By.xpath(String.format(tableValueXpath, label));
        return getText(locator);
    }

    public void closeModal() {
        click(closeButton);
    }
}