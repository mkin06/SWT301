package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface LoginHandler {
    void login(String username, String password);
}

class SimpleLoginManager implements LoginHandler {
    private static final Logger logger = LoggerFactory.getLogger(SimpleLoginManager.class);

    @Override
    public void login(String username, String password) {
        if ("admin".equals(username) && "password123".equals(password)) {
            logger.info("Người dùng '{}' đăng nhập thành công.", username);
        } else {
            logger.warn("Đăng nhập thất bại cho người dùng '{}'.", username);
        }
    }
}

public class InterfaceNamingInconsistencyExample {
    public static void main(String[] args) {
        LoginHandler handler = new SimpleLoginManager();
        handler.login("admin", "password123");
        handler.login("user", "wrongpass");
    }
}