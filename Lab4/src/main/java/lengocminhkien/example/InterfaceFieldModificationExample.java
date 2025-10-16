package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class AppConstants {

    private AppConstants() {
    }
    public static final int MAX_USERS = 100;
}

public class InterfaceFieldModificationExample {
    private static final Logger logger = LoggerFactory.getLogger(InterfaceFieldModificationExample.class);

    public static void main(String[] args) {
        logger.info("Số lượng người dùng tối đa được cấu hình là: {}", AppConstants.MAX_USERS);
    }
}