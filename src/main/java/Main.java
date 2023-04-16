import ClassesToTest.HelloWorld;
import Util.CharSequenceJavaFileObject;
import Util.ClassFileManager;
import controller.Compilation;
import controller.Execution;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Java code to compile and run
        String code = "package controller;\n" +
                "\n" +
                "import ClassesToTest.HelloWorld;\n" +
                "import org.junit.*;\n" +
                "import static org.junit.Assert.*;\n" +
                "\n" +
                "public class HelloWorldTest {\n" +
                "\n" +
                "  @Test\n" +
                "  public void testSayHello() {\n" +
                "    HelloWorld hello = new HelloWorld();\n" +
                "    String result = hello.sayHello(\"John\");\n" +
                "    assertEquals(\"Hello John\", result);\n" +
                "  }\n" +
                "}";

        Scanner sc = new Scanner(System.in);
        System.out.print("¿Do you want to compile the code? (y/n): ");
        String respuesta = sc.next();
        if (respuesta.equalsIgnoreCase("y")) {
            boolean succes = true;
            if(succes) {
                //FileCreator.createFile("HelloWorldTest", code);

                String name = "controller.HelloWorldTest";

                Class<?> clas =Compilation.compileTestClass(name,code);
                System.out.print("¿Do you want to execute the code? (y/n): ");
                respuesta = sc.next();
                if (respuesta.equalsIgnoreCase("y")) {
                    HelloWorld h = new HelloWorld();
                    Execution.runTests(clas,h);
                    //Class.forName("controller.HelloWorldTest");
                }else if (respuesta.equalsIgnoreCase("n")) {
                    System.out.println("No execution");
                } else {
                    System.out.println("Inavalid response");
                }
            }
        } else if (respuesta.equalsIgnoreCase("n")) {
            System.out.println("No compilation");
        } else {
            System.out.println("Inavalid response");
        }
        sc.close();
    }

}
