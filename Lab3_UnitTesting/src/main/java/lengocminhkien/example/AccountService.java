package lengocminhkien.example;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class AccountService {
    private Set<String> registeredUsernames;
    private Set<String> registeredEmails;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public AccountService() {
        this.registeredUsernames = new HashSet<>();
        this.registeredEmails = new HashSet<>();
    }

    public boolean registerAccount(String username, String password, String email) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        if (username.length() < 3) {
            return false;
        }
        if (registeredUsernames.contains(username)) {
            return false;
        }

        if (password == null || password.isEmpty()) {
            return false;
        }
        if (password.length() < 7) {
            return false;
        }
        if (!hasUpperCase(password)) {
            return false;
        }
        if (!hasLowerCase(password)) {
            return false;
        }
        if (!hasDigit(password)) {
            return false;
        }
        if (!hasSpecialCharacter(password)) {
            return false;
        }

        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return false;
        }
        if (registeredEmails.contains(email)) {
            return false;
        }

        registeredUsernames.add(username);
        registeredEmails.add(email);
        return true;
    }

    private boolean hasUpperCase(String password) {
        return password.chars().anyMatch(Character::isUpperCase);
    }

    private boolean hasLowerCase(String password) {
        return password.chars().anyMatch(Character::isLowerCase);
    }

    private boolean hasDigit(String password) {
        return password.chars().anyMatch(Character::isDigit);
    }

    private boolean hasSpecialCharacter(String password) {
        return password.chars().anyMatch(ch -> "!@#$%^&*()_+-=[]{}|;:,.<>?".indexOf(ch) >= 0);
    }

    public boolean isUsernameRegistered(String username) {
        return registeredUsernames.contains(username);
    }

    public int getRegisteredAccountCount() {
        return registeredUsernames.size();
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean isEmailRegistered(String email) {
        return registeredEmails.contains(email);
    }
}