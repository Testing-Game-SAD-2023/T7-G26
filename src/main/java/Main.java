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
        String name = "controller.HelloWorldTest";
        Game g = new Game(name,code);

        g.startGame();
    }

}