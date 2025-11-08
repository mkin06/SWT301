package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    protected void navigateTo(String url) {
        driver.get(url);
    }

    public void jsScrollToElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);

            Thread.sleep(300); // Đợi cuộn xong
        } catch (Exception e) {
            System.err.println("Không thể cuộn đến element: " + locator.toString());
            e.printStackTrace();
        }
    }

    public void jsRemoveElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            js.executeScript("arguments[0].remove();", element);
        } catch (Exception e) {
            System.out.println("Bỏ qua: Không tìm thấy element để xóa: " + locator.toString());
        }
    }

    protected void type(By locator, String text) {
        jsScrollToElement(locator);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        element.sendKeys(text);
    }

    protected void click(By locator) {
        jsScrollToElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    protected void jsClick(By locator) {
        jsScrollToElement(locator);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        js.executeScript("arguments[0].click();", element);
    }

    protected String getText(By locator) {
        jsScrollToElement(locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected void waitForVisibility(By locator) {
        jsScrollToElement(locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}