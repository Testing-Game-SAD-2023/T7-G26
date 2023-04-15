import Util.FileCreator;

public class Main {
    public static void main(String[] args) throws Exception {
        // Java code to compile and run
        String code = "import org.junit.Test;\r\n"
                + "import static org.junit.Assert.*;\r\n"
                + "\r\n"
                + "public class HelloWorldTest {\r\n"
                + "\r\n"
                + "  @Test\r\n"
                + "  public void testSayHello() {\r\n"
                + "    HelloWorld hello = new HelloWorld();\r\n"
                + "    String result = hello.sayHello(\"John\");\r\n"
                + "    assertEquals(\"Hello John\", result);\r\n"
                + "  }\r\n"
                + "}";

        FileCreator.createFile("HelloWorldTest", code);
        Class<?> compiledClass = Compilation.compileTestClass("HelloWorldTest", code);
    }
}
