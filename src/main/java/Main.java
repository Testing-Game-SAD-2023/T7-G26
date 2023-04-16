import Util.FileCreator;
import controller.Compilation;

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
            boolean succes = Compilation.compileTestClass("HelloWorldTest", code);
            if(succes) {
                FileCreator.createFile("HelloWorldTest", code);
                System.out.print("¿Do you want to execute the code? (y/n): ");
                respuesta = sc.next();
                if (respuesta.equalsIgnoreCase("y")) {

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
