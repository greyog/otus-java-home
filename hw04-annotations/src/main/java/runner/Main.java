package runner;

import annotations.After;
import annotations.Before;
import annotations.Test;
import test.MyTestClass;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {
    public static void main(String... args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        runTestsFrom(MyTestClass.class);
    }

    private static void runTestsFrom(Class<?> testClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Method[] allMethods = testClass.getDeclaredMethods();
        List<Method> beforeMethods = getMethodsWithAnnotation(allMethods, Before.class, Test.class, After.class);
        List<Method> testMethods = getMethodsWithAnnotation(allMethods, Test.class, After.class, Before.class);
        List<Method> afterMethods = getMethodsWithAnnotation(allMethods, After.class, Before.class, Test.class);

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

    @SafeVarargs
    private static List<Method> getMethodsWithAnnotation(Method[] methods,
                                                         Class<? extends Annotation> required,
                                                         Class<? extends Annotation>... restricted) {
        List<Method> result = Arrays.stream(methods).filter(method -> method.isAnnotationPresent(required)).toList();
        for (Class<? extends Annotation> restr : restricted) {
            List<Method> list = new ArrayList<>();
            for (Method method : result) {
                if (!method.isAnnotationPresent(restr)) {
                    list.add(method);
                } else {
                    throw new IllegalArgumentException(String.format("Only one annotation allowed. " +
                            "At method %s found annotations %s and %s.", method.getName(), required.getSimpleName(), restr.getSimpleName()));
                }
            }
            result = list;
        }
        return result;
    }


}
