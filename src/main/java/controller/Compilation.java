package controller;

import Util.CharSequenceJavaFileObject;
import Util.ClassFileManager;

import javax.tools.*;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Compilation {

    public static Class<?> compileTestClass(String className, String code) throws Exception {

        // Compilar la clase
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileManager manager = new ClassFileManager(compiler.getStandardFileManager(null, null, null));

        List<JavaFileObject> files = new ArrayList<JavaFileObject>();
        files.add(new CharSequenceJavaFileObject(className, code));

        compiler.getTask(Writer.nullWriter(), manager, null, null, null, files).call();

        // Cargar e instanciar la clase
        Class<?> clas = manager.getClassLoader(null).loadClass(className);

        return clas;
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
