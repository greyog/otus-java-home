import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void addValidFruit() {
        Box<Apple> appleBox = new Box<>();
        Box<Fruit> fruitBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        int boxSize = 13;
        for (int i = 0; i < boxSize; i++) {
            appleBox.add(new Apple());
            orangeBox.add(new Orange());

            fruitBox.add(i % 2 == 0 ? new Apple() : new Orange());
        }

    }

    @Test
    void weight() {
        double oneAppleWeight = new Apple().getWeight();
        double oneOrangeWeight = new Orange().getWeight();

        Box<Apple> appleBox = new Box<>();
        Box<Fruit> fruitBox = new Box<>();

        int fruitCount = 10;

        for (int i = 0; i < fruitCount; i++) {
            appleBox.add(new Apple());
            fruitBox.add(new Apple());
            fruitBox.add(new Orange());
        }

        double DELTA = 0.001;

        assertEquals(oneAppleWeight * fruitCount, appleBox.weight(), DELTA);
        assertEquals((oneAppleWeight + oneOrangeWeight) * fruitCount, fruitBox.weight(), DELTA);
    }

    @Test
    void compare() {
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        for (int i = 0; i < 20; i++) {
            appleBox.add(new Apple());
        }

        for (int i = 0; i < 13; i++) {
            orangeBox.add(new Orange());
        }

        assertTrue(orangeBox.compare(appleBox));
    }

    @Test
    void transferToValidBox() {
        Box<Apple> appleBox = new Box<>();
        Box<Fruit> fruitBox = new Box<>();


        for (int i = 0; i < 20; i++) {
            appleBox.add(new Apple());
        }

        fruitBox.add(new Orange());

        double sourceBoxWeightBefore = appleBox.weight();
        double destBoxWeightBefore = fruitBox.weight();

        appleBox.transferTo(fruitBox);

        assertEquals(0.0, appleBox.weight(), 0.001);
        assertEquals(sourceBoxWeightBefore + destBoxWeightBefore,
                fruitBox.weight(), 0.001);

        Box<Apple> secondAppleBox = new Box<>();
        secondAppleBox.add(new Apple());

        secondAppleBox.transferTo(appleBox);

        assertEquals(0.0, secondAppleBox.weight(), 0.001);
        assertEquals(new Apple().getWeight(),
                appleBox.weight(), 0.001);


    }
}