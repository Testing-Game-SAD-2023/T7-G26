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

        compiler.getTask(null, manager, null, null, null, files).call();

        //Load and instantiate the class
        return manager.getClassLoader(null).loadClass(className);
    }
}
