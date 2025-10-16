package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResourceLeakExample {

    private static final Logger logger = LoggerFactory.getLogger(ResourceLeakExample.class);

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }
        } catch (IOException e) {
            logger.error("Lỗi khi đọc tệp data.txt", e);
        }
    }
}