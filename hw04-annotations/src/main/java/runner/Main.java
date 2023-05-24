package runner;

import annotations.After;
import annotations.Before;
import annotations.Test;
import test.MyTestClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String... args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        runTestsFrom(MyTestClass.class);
    }

    private static void runTestsFrom(Class<?> testClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Method[] allMethods = testClass.getDeclaredMethods();
        List<Method> beforeMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();
        for (Method method : allMethods) {
            if(method.isAnnotationPresent(Before.class)) beforeMethods.add(method);
            else if (method.isAnnotationPresent(Test.class)) testMethods.add(method);
            else if (method.isAnnotationPresent(After.class)) afterMethods.add(method);
        }

        int totalTestsCount = testMethods.size();
        int passedTestsCount = 0;
        int failedTestsCount = 0;
        long totalTime = 0;

        for (Method testMethod : testMethods) {
            long beginTime = System.currentTimeMillis();
            Object testClassInstance = testClass.getDeclaredConstructor().newInstance();
            for (Method beforeMethod : beforeMethods) {
                beforeMethod.invoke(testClassInstance);
            }
            try {
                testMethod.invoke(testClassInstance);
                passedTestsCount++;
            } catch (Exception e) {
                System.out.println("Test #" + (passedTestsCount + 1) +
                        " failed with the following exception message:");
                System.out.println(e.getCause().toString());
                failedTestsCount++;
            } finally {
                for (Method afterMethod : afterMethods) {
                    afterMethod.invoke(testClassInstance);
                }
            }
            totalTime += System.currentTimeMillis() - beginTime;
        }

        System.out.println("-----------------------------");
        System.out.println("Total tests:  " + totalTestsCount);
        System.out.println("Passed tests: " + passedTestsCount);
        System.out.println("Failed tests: " + failedTestsCount);
        System.out.println("Time elapsed: " + totalTime + " ms");

    }


}
