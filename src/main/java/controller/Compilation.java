package controller;

import util.CharSequenceJavaFileObject;
import util.ClassFileManager;

import javax.tools.*;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Compilation {

    public static Class<?> compileClass(String className, String code) throws Exception {

        // Compile the class
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileManager manager = new ClassFileManager(compiler.getStandardFileManager(null, null, null));

        List<JavaFileObject> files = new ArrayList<JavaFileObject>();
        files.add(new CharSequenceJavaFileObject(className, code));

        compiler.getTask(Writer.nullWriter(), manager, null, null, null, files).call();

        //Load and instantiate the class
        Class<?> clas = manager.getClassLoader(null).loadClass(className);

        return clas;
    }
}
