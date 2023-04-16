package controller;

import javax.tools.*;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

public class Compilation {

    public static boolean compileTestClass(String className, String code) throws Exception {

        // Create a new JavaCompiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        // Create a new in-memory Java file from the source code
        JavaFileObject javaFile = new StringJavaFileObject(className, code);

        // Create a new CompilationTask from the compiler
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, Arrays.asList(javaFile));

        // Call the CompilationTask's call method to compile the code
        boolean success = task.call();

        if (success) {
            // Load the compiled class
            //return Class.forName(className);
            return success;
        } else {
            throw new Exception("Compilation failed.");
        }
    }

    // Inner class to represent a Java source file as a String
    static class StringJavaFileObject extends SimpleJavaFileObject {

        private final String code;

        protected StringJavaFileObject(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return code;
        }
    }

}
