package instrumentation;

import annotation.MyLog;
import javassist.*;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;

public class MyClassTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        String targetClassName = className.replaceAll("/", ".");
        if (loader != null) {
            try {
                return addParametersLogging(targetClassName);
            } catch (IOException | CannotCompileException | NotFoundException e) {
                e.printStackTrace();
            }
        }

        return ClassFileTransformer.super.transform(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);

    }

    private byte[] addParametersLogging(String targetClassName) throws NotFoundException, CannotCompileException, IOException {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get(targetClassName);

        var annotatedMethods = Arrays.stream(cc.getDeclaredMethods()).filter(
                ctMethod -> ctMethod.hasAnnotation(MyLog.class)).toList();

        for (CtMethod method : annotatedMethods) {
            MethodInfo methodInfo = method.getMethodInfo();
            LocalVariableAttribute localVarsTable = (LocalVariableAttribute) methodInfo.getCodeAttribute().getAttribute(LocalVariableAttribute.tag);
            var vars = new ArrayList<String>();

//            for instance methods first parameter is "this" so starting from 1
//            for static methods "this" is absent so starting from 0
            int startIndex = Modifier.isStatic(method.getModifiers()) ? 0 : 1;
            for (int i = startIndex; i < localVarsTable.tableLength(); i++) {
                String varName = localVarsTable.variableName(i);
                vars.add( varName );
            }

            StringBuilder before = new StringBuilder("{ java.util.List metadata = new java.util.ArrayList(); ");
            for (String v : vars) {
                before.append("metadata.add(\"").append(v).append(": \");");
            }
            before.append("System.out.println(\"--------------------------------------------\");");
            before.append("System.out.print(\"Executed method: ").append(method.getName()).append(", \");");
            before.append("for (int i = 0; i < $args.length; i++)" + "{ metadata.set(i, metadata.get(i) + $args[i]) ; }");
            before.append("System.out.println(String.join(\", \", metadata.subList(0, $args.length)));");
            before.append("System.out.println(\"--------------------------------------------\");");
            before.append("}");
            method.insertBefore(before.toString());
        }

        byte[] result = cc.toBytecode();
        cc.detach();
        return result;
    }
}
