package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnreachableCodeExample {

    private static final Logger logger = LoggerFactory.getLogger(UnreachableCodeExample.class);

    public static int getNumber() {
        logger.info("Chuẩn bị trả về một số...");
        return 42;
    }

    public static void main(String[] args) {
        int number = getNumber();
        logger.info("Số nhận được là: {}", number);
    }
}