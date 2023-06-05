package requirement_t7.model;

import requirement_t7.model.util.CharSequenceJavaFileObject;
import requirement_t7.model.util.ClassFileManager;

import javax.tools.*;
import java.util.ArrayList;
import java.util.List;
import javax.tools.JavaCompiler;
import javassist.*;


public class Compilation {

    public static Class<?> compileClass(String className, String code) throws Exception {

        // Compile the class
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileManager manager = new ClassFileManager(compiler.getStandardFileManager(null, null, null));

        List<JavaFileObject> files = new ArrayList<JavaFileObject>();
        files.add(new CharSequenceJavaFileObject(className, code));
        List<String> options = new ArrayList<>();
        options.add("-classpath");
        options.add(System.getProperty("java.class.path"));

        boolean success = compiler.getTask(null, manager, null, options, null, files).call();

        //Load and instantiate the class
        Class<?> clazz = manager.getClassLoader(null).loadClass(className);


        if (success) {
            // Load the compiled class
            return clazz;
        } else {
            throw new Exception("Compilation failed.");
        }

        //return clas;
    }

    public static Class<?> compileClass2() throws Exception {
        ClassPool pool = ClassPool.getDefault();

        // Crea la clase InputClass
        CtClass inputClass = pool.makeClass("requirement_t7.classLoaded.InputClass");

        // Añade el método evenOrOdd a la clase InputClass
        CtMethod evenOrOddMethod = CtMethod.make(
                "public static String evenOrOdd(int num) {\n" +
                        "    if (num % 2 == 0) {\n" +
                        "        return \"even\";\n" +
                        "    } else {\n" +
                        "        return \"odd\";\n" +
                        "    }\n" +
                        "}", inputClass);
        inputClass.addMethod(evenOrOddMethod);

        return inputClass.toClass();
    }

    public static Class<?> compileTest() throws Exception {
        ClassPool pool = ClassPool.getDefault();

        // Crea la clase InputTestClass
        CtClass inputTestClass = pool.makeClass("requirement_t7.classLoaded.InputTestClass");
        inputTestClass.setSuperclass(pool.get("org.junit.Test"));

        // Añade el método testEvenNumber a la clase InputTestClass
        CtMethod testEvenNumberMethod = CtMethod.make(
                "public void testEvenNumber() {\n" +
                        "    int num = 4;\n" +
                        "    String result = InputClass.evenOrOdd(num);\n" +
                        "    assertEquals(\"even\", result);\n" +
                        "}", inputTestClass);
        inputTestClass.addMethod(testEvenNumberMethod);

        // Añade el método testOddNumber a la clase InputTestClass
        CtMethod testOddNumberMethod = CtMethod.make(
                "public void testOddNumber() {\n" +
                        "    int num = 7;\n" +
                        "    String result = InputClass.evenOrOdd(num);\n" +
                        "    assertEquals(\"odd\", result);\n" +
                        "}", inputTestClass);
        inputTestClass.addMethod(testOddNumberMethod);

        return inputTestClass.toClass();
    }
}
