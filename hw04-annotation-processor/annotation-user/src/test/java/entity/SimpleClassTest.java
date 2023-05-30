package entity;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SimpleClassTest {

    @Test
    void foo() {
        SimpleClass simple = new SimpleClass();
        simple.foo();
//        /home/greyog/Projects/otus-java-home/hw04-annotation-processor/annotation-user/src/main/java/entity/SimpleClass.java
//        System.out.println(simple.getClass().getProtectionDomain().getCodeSource().getLocation());
        Class clazz = simple.getClass();
        String path = clazz.getPackage().getName().replaceAll("\\.","/");
        File sourceFile = new File(path, clazz.getSimpleName() + ".java");
        System.out.println(sourceFile.getAbsolutePath());
        System.out.println(Arrays.toString(sourceFile.list()));
    }
}