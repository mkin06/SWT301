package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PathTraversalExample {

    private static final Logger logger = LoggerFactory.getLogger(PathTraversalExample.class);

    private static final String BASE_DIRECTORY = "safe_directory";

    public static void main(String[] args) {
        setupSafeDirectory();

        processFileRequest("legit_file.txt");

        logger.info("\n-----------------------------------\n");

        processFileRequest("../secret.txt");
    }

    public static void processFileRequest(String userInput) {
        File baseDir = new File(BASE_DIRECTORY);
        File requestedFile = new File(baseDir, userInput);

        logger.info("Đang xử lý yêu cầu cho tệp: '{}'", userInput);

        try {
            String baseDirPath = baseDir.getCanonicalPath();
            String requestedFilePath = requestedFile.getCanonicalPath();

            if (requestedFilePath.startsWith(baseDirPath)) {
                if (requestedFile.exists() && !requestedFile.isDirectory()) {
                    logger.info("Thành công: Truy cập tệp '{}' được cho phép.", requestedFilePath);
                } else {
                    logger.warn("Tệp '{}' không tồn tại hoặc là một thư mục.", requestedFilePath);
                }
            } else {
                logger.error("LỖI BẢO MẬT: Phát hiện tấn công Path Traversal! Yêu cầu truy cập '{}' đã bị từ chối.", requestedFilePath);
            }
        } catch (IOException e) {
            logger.error("Đã xảy ra lỗi I/O khi đang chuẩn hóa đường dẫn cho '{}'", userInput, e);
        }
    }
    private static void setupSafeDirectory() {
        try {
            Files.createDirectories(Paths.get(BASE_DIRECTORY));
            Files.write(Paths.get(BASE_DIRECTORY, "legit_file.txt"), "this is a safe file.".getBytes());
            Files.write(Paths.get("secret.txt"), "this is a secret file!".getBytes());
        } catch (IOException e) {
            // Bỏ qua nếu đã tồn tại
        }
    }
}