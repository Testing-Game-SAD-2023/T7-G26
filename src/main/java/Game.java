import ClassesToTest.HelloWorld;
import controller.Compilation;
import controller.Execution;

import java.util.Scanner;

public class Game {

    private String code = null;
    private String name = null;

    public Game(String name, String code){
        this.name=name;
        this.code=code;
    }

    public void startGame() throws Exception {
        //Ask if you want to compile the code
        Scanner sc = new Scanner(System.in);
        System.out.print("¿Do you want to compile the code? (y/n): ");
        String respuesta = sc.next();
        if (respuesta.equalsIgnoreCase("y")) {
            boolean succes = true;
            if(succes) {
                //Compilation and return of an instance of the class
                Class<?> clas = Compilation.compileClass(name,code);
                //Ask if you want to execute the code
                System.out.print("¿Do you want to execute the code? (y/n): ");
                respuesta = sc.next();
                if (respuesta.equalsIgnoreCase("y")) {
                    //Run the test
                    Execution.runTests(clas);
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
