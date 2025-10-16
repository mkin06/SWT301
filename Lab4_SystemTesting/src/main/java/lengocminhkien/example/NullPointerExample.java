package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NullPointerExample {

    private static final Logger logger = LoggerFactory.getLogger(NullPointerExample.class);

    public static void main(String[] args) {
        String text1 = "Một chuỗi hợp lệ";
        String text2 = "";
        String text3 = "Hello";

        processText(text1);
        processText(text2);
        processText(text3);
    }

    public static void processText(String text) {

        if (text != null && !text.isEmpty()) {
            logger.info("Text is not empty: {}", text);
        } else {
            logger.warn("Text is null or empty.");
        }
    }
}