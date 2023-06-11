package agent;

import annotation.MyLog;
import transformer.MyClassTransformer;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;

public class MyLogAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("hello from agent");
        inst.addTransformer(new MyClassTransformer());
    }
}
