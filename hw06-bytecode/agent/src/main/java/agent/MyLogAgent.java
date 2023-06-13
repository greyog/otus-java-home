package agent;

import instrumentation.MyClassTransformer;

import java.lang.instrument.Instrumentation;

public class MyLogAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("hello from agent");
        inst.addTransformer(new MyClassTransformer());
    }
}
