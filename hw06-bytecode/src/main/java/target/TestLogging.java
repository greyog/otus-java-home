package target;

import annotation.MyLog;

import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;

public class TestLogging {

    @MyLog
    public void calculation(int param) {
        System.out.println("I've got param: " + param);
        int a = 5;
        int b = 6;
        double div = (double) a / b;
    }

    @MyLog
    public String printSomethingNew( int p1, double p2, String p3) {
        String result = "Here we have " + p1 + ", " + p2 + ", " + p3;
        System.out.println(result);
        return result;
    }

    public void printMe() {
        System.out.println("------------------");
    }

    @MyLog
    public static void staticMethod(int p1, String s1, Object o) {
        System.out.println("Hello from static method. p1 = " + p1 + ", s1 = " + s1);
    }
}
