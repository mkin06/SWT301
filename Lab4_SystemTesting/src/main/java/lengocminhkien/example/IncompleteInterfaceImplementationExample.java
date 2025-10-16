package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Shape {
    void draw();
    void resize();
}

class Square implements Shape {
    private static final Logger logger = LoggerFactory.getLogger(Square.class);

    @Override
    public void draw() {
        logger.info("Đang vẽ hình vuông.");
    }

    @Override
    public void resize() {
        logger.info("Đang thay đổi kích thước hình vuông.");
    }
}

public class IncompleteInterfaceImplementationExample {
    public static void main(String[] args) {
        Shape mySquare = new Square();
        mySquare.draw();
        mySquare.resize();
    }
}