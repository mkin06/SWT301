package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatchGenericExceptionExample {

    private static final Logger logger = LoggerFactory.getLogger(CatchGenericExceptionExample.class);

    public static void main(String[] args) {
        String s = "Đây là một chuỗi hợp lệ";

        logger.info("Độ dài của chuỗi là: {}", s.length());
        }
}
