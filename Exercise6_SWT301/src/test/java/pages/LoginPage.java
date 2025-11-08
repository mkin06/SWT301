package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // === Locators ===
    private By username = By.id("username");
    private By password = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By errorAlert = By.cssSelector(".alert--error");
    private By infoAlert = By.cssSelector(".alert--info");

    // Sau khi đăng nhập thành công: có thể bị redirect sang /home hoặc /dashboard
    private String successUrlPart = "/home";

    // === Actions ===
    public void navigate() {
        navigateTo("http://localhost:8080/login");

        ((JavascriptExecutor) driver).executeScript("""
            document.querySelector('#fixedban')?.remove();
            document.querySelector('footer')?.remove();
        """);
    }

    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginButton);
    }

    // === Kiểm tra kết quả ===
    public boolean isLoginSuccess() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains(successUrlPart),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'LuxDine')]"))
            ));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isLoginFailed() {
        try {
            return isElementVisible(errorAlert);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoggedOutMessageVisible() {
        return isElementVisible(infoAlert);
    }
}
