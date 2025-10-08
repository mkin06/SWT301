import lengocminhkien.example.AccountService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountServiceTest {

    private AccountService service;
    private static FileWriter resultWriter;

    @BeforeAll
    static void setupFile() throws IOException {
        // Đảm bảo thư mục target tồn tại
        Files.createDirectories(Paths.get("target"));
        resultWriter = new FileWriter("target/UnitTest.csv", false);
        resultWriter.write("username,password,email,expected,actual,result\n");
    }

    @AfterAll
    static void closeFile() throws IOException {
        if (resultWriter != null) {
            resultWriter.flush();
            resultWriter.close();
        }
    }

    @BeforeEach
    void setUp() {
        service = new AccountService();
    }

    @Order(1)
    @ParameterizedTest(name = "Register {0} / {1} / {2} => expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testRegisterAccount(String username, String password, String email, boolean expected) throws IOException {
        boolean actual = service.registerAccount(username, password, email);

        String result = (expected == actual) ? "PASS" : "FAIL";

        resultWriter.write(String.format(
                "%s,%s,%s,%s,%s,%s\n",
                username == null ? "" : username,
                password == null ? "" : password,
                email == null ? "" : email,
                expected,
                actual,
                result
        ));
        resultWriter.flush();

        assertEquals(expected, actual,
                String.format("Test failed for username=%s, password=%s, email=%s", username, password, email));
    }

    @Order(2)
    @ParameterizedTest(name = "Check email validity: {2}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testIsValidEmailFromTestData(String username, String password, String email, boolean expectedRegisterResult) {
        boolean validEmail = service.isValidEmail(email);
        System.out.printf("Email: %-25s => %s%n", email, validEmail ? "VALID" : "INVALID");
        assertNotNull(email, "Email must not be null");
    }

    @Order(3)
    @ParameterizedTest(name = "Check password validity: {1}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testIsValidPasswordFromTestData(String username, String password, String email, boolean expectedRegisterResult) {
        boolean validPassword = service.isValidPassword(password);
        System.out.printf("Password: %-15s => %s%n", password, validPassword ? "VALID" : "INVALID");

        if (expectedRegisterResult) {
            assertTrue(validPassword,
                    () -> "Expected valid password for registration: " + password);
        } else {
            assertNotNull(password, "Password must not be null");
        }
    }
}
