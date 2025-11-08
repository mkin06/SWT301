package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Login Tests using Page Object Model")
public class LoginTest extends BaseTest {

    static LoginPage loginPage;

    @BeforeAll
    static void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Should login successfully with valid credentials")
    void testLoginSuccess() {
        loginPage.navigate();
        loginPage.login("tomsmith", "SuperSecretPassword!");

        // Hãy để Page Object lấy text
        String successMessage = loginPage.getSuccessMessageText();
        assertTrue(successMessage.contains("You logged into a secure area!"));
    }

    @Test
    @Order(2)
    @DisplayName("Should show error for invalid credentials")
    void testLoginFail() {
        loginPage.navigate();
        loginPage.login("wronguser", "wrongpassword");

        String errorMessage = loginPage.getErrorMessageText();
        assertTrue(errorMessage.toLowerCase().contains("invalid"));
    }

    @ParameterizedTest(name = "CSV Inline: {0} / {1}")
    @Order(3)
    @CsvSource({
            "tomsmith, SuperSecretPassword!, success",
            "wronguser, SuperSecretPassword!, error",
            "tomsmith, wrongpassword, error",
            "'', '', error"
    })
    void testLoginCsvInline(String username, String password, String expected) {
        loginPage.navigate();
        username = (username == null) ? "" : username.trim();
        password = (password == null) ? "" : password.trim();

        loginPage.login(username, password);

        if (expected.equals("success")) {
            String msg = loginPage.getSuccessMessageText();
            assertTrue(msg.contains("You logged into a secure area!"));
        } else {
            String msg = loginPage.getErrorMessageText();
            assertTrue(msg.toLowerCase().contains("invalid"));
        }
    }

    @ParameterizedTest(name = "CSV File: {0} / {1}")
    @Order(4)
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    void testLoginFromCSV(String username, String password, String expected) {
        loginPage.navigate();
        username = (username == null) ? "" : username.trim();
        password = (password == null) ? "" : password.trim();

        loginPage.login(username, password);

        if (expected.equals("success")) {
            String msg = loginPage.getSuccessMessageText();
            assertTrue(msg.contains("You logged into a secure area!"));
        } else {
            String msg = loginPage.getErrorMessageText();
            assertTrue(msg.toLowerCase().contains("invalid"));
        }
    }


}