package test;

import annotations.After;
import annotations.Before;
import annotations.Test;
import entity.SimpleClass;

public class MyTestClass {
    private SimpleClass simple;

    @Before
    public void setup() {
        System.out.println("-----------------------------");
        System.out.println("Preparing for test case...");
        simple = new SimpleClass(124);
    }

    @Test
    public void testPrintValue() {
        System.out.println("Performing testPrintValue....");
        simple.printValue();
    }

    @Test
    public void testThrowingMethod() {
        System.out.println("Performing testThrowingMethod....");
        simple.iThrowSometimes();
    }

    @After
    public void cooldown() {
        System.out.println("Cooling things down...");
        simple.releaseTheSquirrel();
        simple = null;
        System.out.println("Cooling things down is done.");
    }

}
