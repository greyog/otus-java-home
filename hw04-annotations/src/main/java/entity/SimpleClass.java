package entity;

public class SimpleClass {
    private int value = 123;

    public SimpleClass(int value) {
        this.value = value;
    }

    public SimpleClass() {
    }

    public void printValue() {
        System.out.println("My value is: " + value);
    }

    public void iThrowSometimes() {
        if(this.value != 123) throw new IllegalStateException("Someone changed my lovely value");
    }
}
