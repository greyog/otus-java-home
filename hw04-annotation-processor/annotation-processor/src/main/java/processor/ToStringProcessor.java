package processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Set;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@SupportedAnnotationTypes("annotation.CustomToString")
public class ToStringProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
                "============Processing annotations" + annotations.toString()+"============");
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
                "All elements: " + roundEnv.getRootElements());
        for (TypeElement annotation : annotations) {
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Elements annotated with " + annotation.toString());
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, elements.toString());
            for (Element element : elements) {
//                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
//                        "Simple name "+element.getSimpleName());
//                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
//                        "Enclosing element "+element.getEnclosingElement());
//                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
//                        "Enclosed elements "+element.getEnclosedElements());
//
                try {
                    writeToFile(element, annotation);
                } catch (IOException e) {
                    e.printStackTrace();
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            e.getMessage(),
                            element);
                }
            }
        }

        return false;
    }

    private void writeToFile(Element element, TypeElement annotation) throws IOException {
        String packageName = ((PackageElement) element.getEnclosingElement()).getQualifiedName().toString();
        String fullClassName = ((TypeElement) element).getQualifiedName().toString();
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
                "fullClassName: " + fullClassName);

        JavaFileObject file = processingEnv.getFiler().
                createSourceFile(fullClassName + annotation.getSimpleName(), element);
        try (PrintWriter writer = new PrintWriter(file.openWriter())) {
            writer.println("// Created at " + LocalDateTime.now().toString() + " by " +
                    this.getClass().getCanonicalName());
            writer.println("package " + packageName +";");
            writer.println("public class " +
                    element.getSimpleName() + annotation.getSimpleName() +
                    " extends " + element.getSimpleName() +
                    "{");
            writer.println("public String toString() {");
            writer.println(" return String.valueOf(42); ");

//            element.getKind()

            writer.println("}");
            writer.println("}");

            writer.close();
        }
    }
}
