package pages;

import org.openqa.selenium.*;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private By username = By.id("username");
    private By email = By.id("email");
    private By password = By.id("password");
    private By confirmPassword = By.id("confirmPassword");
    private By registerButton = By.cssSelector("button[type='submit']");
    private By successMessage = By.cssSelector(".alert-success");

    public void navigate() {
        navigateTo("http://localhost:8080/register");
    }

    public void register(String user, String mail, String pass, String confirm) {
        type(username, user);
        type(email, mail);
        type(password, pass);
        type(confirmPassword, confirm);
        click(registerButton);
    }

    public boolean isSuccessMessageVisible() {
        try {
            if (isElementVisible(successMessage)) {
                return true;
            }

            String pageSource = driver.getPageSource().toLowerCase();
            String currentUrl = driver.getCurrentUrl().toLowerCase();

            return pageSource.contains("otp") ||
                    pageSource.contains("xác minh") ||
                    pageSource.contains("verify") ||
                    pageSource.contains("check your email") ||
                    currentUrl.contains("otp") ||
                    currentUrl.contains("verify") ||
                    currentUrl.contains("confirmation");
        } catch (Exception e) {
            return false;
        }
    }

}
