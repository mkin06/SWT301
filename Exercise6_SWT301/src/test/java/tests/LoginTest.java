    package tests;

    import org.junit.jupiter.api.*;
    import org.junit.jupiter.params.ParameterizedTest;
    import org.junit.jupiter.params.provider.CsvSource;
    import pages.LoginPage;

    import static org.junit.jupiter.api.Assertions.assertTrue;

    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("LuxDine Login Tests – Localhost")
    public class LoginTest extends BaseTest {

        static LoginPage loginPage;

        @BeforeAll
        static void initPage() {
            loginPage = new LoginPage(driver);
        }

        @ParameterizedTest(name = "Login with {0} / {1}")
        @CsvSource({
                // Sai mật khẩu
                "kienle, 999999, fail",
                // Username không tồn tại
                "unknown, 123456, fail",
                // Tài khoản hợp lệ
                "kienle, 123456, success"
        })
        @Order(1)
        void testLogin(String username, String password, String expected) {
            loginPage.navigate();
            loginPage.login(username, password);

            if (expected.equals("success")) {
                assertTrue(loginPage.isLoginSuccess(), " Expected success but login failed!");
            } else {
                assertTrue(loginPage.isLoginFailed(), "Expected failure but no error message shown!");
            }
        }

        @AfterAll
        public static void tearDownBase() {
            if (driver != null) {
                try {
                    System.out.println("⏳ Waiting 15s before closing browser (for manual verify)...");
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                driver.quit();
            }
        }
    }
