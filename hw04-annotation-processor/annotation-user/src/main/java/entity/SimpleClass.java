package entity;

import annotation.CustomToString;

@CustomToString
public class SimpleClass {
    private int anInt = 213;
    private double aDouble = 0.4213;
    private String txt = "Some thext here";

    public void foo() {
        System.out.println("foo, and i'm public");
    }

    private void bar() {
        System.out.println("bar. hey i'm private!");
    }
}
