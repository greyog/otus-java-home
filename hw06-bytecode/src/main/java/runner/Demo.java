package runner;

import target.TestLogging;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        new TestLogging().calculation(37);
        new TestLogging().printSomethingNew(1, 2.0, "bu");
        TestLogging.staticMethod(1234, "456547oppp", List.of(1, 2, 3));
    }
}
