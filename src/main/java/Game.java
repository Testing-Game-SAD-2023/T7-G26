import controller.Compilation;
import controller.Execution;
import util.FileToStringReader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Game {

    private String inputTestClassName;
    private String inputClassName;
    private String inputClassCode;
    private String inputTestClassCode;

    public Game(String inputClassName, String inputTestClassName){
        this.inputClassName = inputClassName;
        this.inputTestClassName = inputTestClassName;
        inputClassCode = obtainCode(inputClassName);
        inputTestClassCode = obtainCode(inputTestClassName);
    }

    private String obtainCode(String input){
        String res;
        try {
            res = FileToStringReader.convert(new File(input + ".txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public void startGame() throws Exception {
        //Ask if you want to compile the code
        Scanner sc = new Scanner(System.in);
        System.out.print("¿Do you want to compile the code? (y/n): ");
        String response = sc.next();
        if (response.equalsIgnoreCase("y")) {
            boolean success = true;
            if(success) {
                //Compilation and return of an instance of the class
                Class<?> clazz1 = Compilation.compileClass("model." + inputClassName,inputClassCode);
                Class<?> clazz2 = Compilation.compileClass(inputTestClassName,inputTestClassCode);
                //Ask if you want to execute the code
                System.out.print("¿Do you want to execute the code? (y/n): ");
                response = sc.next();
                if (response.equalsIgnoreCase("y")) {
                    //Run the test
                    Execution.runTests(clazz2);
                }else if (response.equalsIgnoreCase("n")) {
                    System.out.println("No execution");
                } else {
                    System.out.println("Inavalid response");
                }
            }
        } else if (response.equalsIgnoreCase("n")) {
            System.out.println("No compilation");
        } else {
            System.out.println("Inavalid response");
        }
        sc.close();
    }
}
