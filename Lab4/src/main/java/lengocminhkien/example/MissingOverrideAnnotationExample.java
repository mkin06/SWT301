package lengocminhkien.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Animal {
    private static final Logger logger = LoggerFactory.getLogger(Animal.class);

    public void speak() {
        logger.info("Động vật kêu.");
    }
}

class Dog extends Animal {
    private static final Logger logger = LoggerFactory.getLogger(Dog.class);

    @Override
    public void speak() {
        logger.info("Chó sủa Gâu Gâu.");
    }
}

public class MissingOverrideAnnotationExample {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        myDog.speak();
    }
}