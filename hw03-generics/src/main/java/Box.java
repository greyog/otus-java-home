import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private final List<T> contents = new ArrayList<>();
    private final double EPSILON = 0.01;

    public void add(T fruit) {
        contents.add(fruit);
    }

    public double weight() {
        double result = 0.0;
        for (T fruit : contents) {
            result += fruit.getWeight();
        }
        return result;
    }

    public boolean compare(Box other) {
        if (other == null) {
            return false;
        }
        return Math.abs(this.weight() - other.weight()) < EPSILON;
    }

    public void transferTo(Box<? super T> consumer) {
        if (consumer == null) {
            throw new IllegalArgumentException("Consumer should not be null");
        }
        for (T f : this.contents) {
            consumer.add(f);
        }
        this.contents.clear();
    }
}
