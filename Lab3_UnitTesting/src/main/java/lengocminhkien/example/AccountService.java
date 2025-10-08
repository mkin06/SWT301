package lengocminhkien.example;

import java.util.*;
import java.util.regex.Pattern;

public class AccountService {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,}$");

    private Set<String> existingUsernames = new HashSet<>();

    public AccountService() {
        existingUsernames.add("alice");

    }

    public boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public boolean isValidPassword(String password) {
        if (password == null) return false;
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public boolean registerAccount(String username, String password, String email) {

        if (username == null || username.isBlank() ||
                password == null || password.isBlank() ||
                email == null || email.isBlank()) {
            return false;
        }
        username = username.trim();
        email = email.trim();

        if (username.length() <= 3) {
            return false;
        }

        if (existingUsernames.contains(username.toLowerCase())) {
            return false;
        }

        if (!isValidPassword(password)) {
            return false;
        }

        if (!isValidEmail(email)) {
            return false;
        }

        existingUsernames.add(username.toLowerCase());
        return true;
    }
}
