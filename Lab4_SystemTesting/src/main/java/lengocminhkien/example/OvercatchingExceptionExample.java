package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OvercatchingExceptionExample {

    private static final Logger logger = LoggerFactory.getLogger(OvercatchingExceptionExample.class);

    public static void main(String[] args) {
        // Khởi tạo mảng với một vài giá trị
        int[] arr = {10, 20, 30, 40, 50};

        logger.info("--- Thử truy cập hợp lệ ---");
        getValueAtIndex(arr, 3);

        logger.info("\n--- Thử truy cập không hợp lệ ---");
        getValueAtIndex(arr, 10);
    }

    public static void getValueAtIndex(int[] array, int index) {
        try {
            if (index >= 0 && index < array.length) {
                logger.info("Thành công! Giá trị tại chỉ số {} là: {}", index, array[index]);
            } else {
                throw new ArrayIndexOutOfBoundsException(
                        "Chỉ số không hợp lệ: " + index + " cho mảng có kích thước " + array.length
                );
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("Lỗi: Không thể truy cập mảng.", e);
        }
    }
}