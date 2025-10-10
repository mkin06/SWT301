import lengocminhkien.example.AccountService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    private static AccountService accountService;

    @BeforeAll
    static void setUp() {
        accountService = new AccountService();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testRegisterAccountWithCSVData(String username, String password, String email, boolean expected) {
        boolean result = accountService.registerAccount(username, password, email);

        assertEquals(expected, result,
                String.format("Test failed for username='%s', password='%s', email='%s'",
                        username, password, email));
    }
}