package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Drawable {
    void draw();
}

class Circle implements Drawable {
    private static final Logger logger = LoggerFactory.getLogger(Circle.class);

    @Override
    public void draw() {
        logger.info("Vẽ một hình tròn.");
    }
}

public class UnimplementedInterfaceExample {
    public static void main(String[] args) {
        Drawable myShape = new Circle();

        myShape.draw();
    }
}