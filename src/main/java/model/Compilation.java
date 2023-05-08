package model;

import model.util.CharSequenceJavaFileObject;
import model.util.ClassFileManager;

import javax.tools.*;
import java.util.ArrayList;
import java.util.List;
import javax.tools.JavaCompiler;

public class Compilation {

    public static Class<?> compileClass(String className, String code) throws Exception {

        // Compile the class
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileManager manager = new ClassFileManager(compiler.getStandardFileManager(null, null, null));

        List<JavaFileObject> files = new ArrayList<JavaFileObject>();
        files.add(new CharSequenceJavaFileObject(className, code));

        boolean success = compiler.getTask(null, manager, null, null, null, files).call();

        //Load and instantiate the class
        Class<?> clazz = manager.getClassLoader(null).loadClass(className);

        // Call the CompilationTask's call method to compile the code

        if (success) {
            // Load the compiled class
            return clazz;
        } else {
            throw new Exception("Compilation failed.");
        }

        //return clas;
    }
}
