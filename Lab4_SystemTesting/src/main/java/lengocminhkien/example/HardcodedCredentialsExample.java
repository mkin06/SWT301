package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HardcodedCredentialsExample {

    private static final Logger logger = LoggerFactory.getLogger(HardcodedCredentialsExample.class);

    private static final String CORRECT_USERNAME = getEnvOrDefault("APP_USER", "admin");
    private static final String CORRECT_PASSWORD = getEnvOrDefault("APP_PASSWORD", "123456");

    public static void main(String[] args) {
        String inputUsername = "admin";
        String inputPassword = "123456";

        if (authenticate(inputUsername, inputPassword)) {
            logger.info("Authentication successful for user '{}'", inputUsername);
        } else {
            logger.warn("Authentication failed for user '{}'", inputUsername);
        }
    }

    private static boolean authenticate(String user, String pass) {
        return CORRECT_USERNAME.equals(user) && CORRECT_PASSWORD.equals(pass);
    }

    private static String getEnvOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value != null) ? value : defaultValue;
    }
}