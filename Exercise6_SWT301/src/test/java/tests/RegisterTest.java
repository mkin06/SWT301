package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Registration Form Tests – Localhost")
public class RegisterTest extends BaseTest {

    static RegisterPage registerPage;

    @BeforeAll
    static void initPage() {
        registerPage = new RegisterPage(driver);
    }

    @ParameterizedTest(name = "Register with {0} / {1}")
    @CsvSource({
        "kienle, lengocminhkien06@gmail.com, 123456, 123456",
    })
    @Order(1)
    void testRegister(String username, String email, String password, String confirm) {
        registerPage.navigate();
        registerPage.register(username, email, password, confirm);

        assertTrue(registerPage.isSuccessMessageVisible(),
                "Form submission failed or success message not visible");
    }

    @AfterAll
    public static void tearDownBase() {
        if (driver != null) {
            try {
                System.out.println("⏳ Waiting 30s before closing browser for OTP input...");
                Thread.sleep(30000); // 30 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }

}
