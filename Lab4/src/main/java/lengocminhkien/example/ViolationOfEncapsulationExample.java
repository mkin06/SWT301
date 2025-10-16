package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViolationOfEncapsulationExample {

    private static final Logger logger = LoggerFactory.getLogger(ViolationOfEncapsulationExample.class);

    public static class User {
        private static final Logger logger = LoggerFactory.getLogger(User.class);

        private final String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.setAge(age);
        }

        public void setAge(int age) {
            if (age > 0 && age < 120) {
                this.age = age;
            } else {
                logger.warn("Tuổi không hợp lệ: {}. Giá trị tuổi không được thay đổi.", age);
            }
        }

        public void display() {
            logger.info("Người dùng: Tên = {}, Tuổi = {}", this.name, this.age);
        }
    }

    public static void main(String[] args) {
        User user1 = new User("Kien", 25);
        user1.display();

        logger.info("\n--- Cập nhật tuổi hợp lệ ---");
        user1.setAge(30);
        user1.display();

        logger.info("\n--- Cập nhật tuổi không hợp lệ ---");
        user1.setAge(-5);
        user1.display();
    }
}