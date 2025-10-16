package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLInjectionExample {
    private static final Logger logger = LoggerFactory.getLogger(SQLInjectionExample.class);

    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String DB_USER = "sa";

    private static final String DB_PASSWORD = loadPasswordFromSecrets();

    public static void main(String[] args) {
        String userInput = "some_user";

        String query = "SELECT user_id, username, full_name, email FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, userInput);
            logger.info("Thực thi truy vấn cho người dùng: {}", userInput);


        } catch (SQLException e) {
            logger.error("Lỗi cơ sở dữ liệu.", e);
        }
    }

    private static String loadPasswordFromSecrets() {
        return System.getenv("DB_PASSWORD");
    }}