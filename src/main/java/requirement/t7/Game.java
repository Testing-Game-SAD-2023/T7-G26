package requirement.t7;

import requirement.t7.model.Compilation;
import requirement.t7.model.Execution;
import requirement.t7.model.util.FileCreator;
import requirement.t7.model.util.FileToStringReader;

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
        //Compile class
        compileClass();
        //Ask if you want to compile the code
        Scanner sc = new Scanner(System.in);
        System.out.print("¿Do you want to compile the code? (y/n): ");
        String response = sc.next();
        if (response.equalsIgnoreCase("y")) {
            compileTest(sc);
        } else if (response.equalsIgnoreCase("n")) {
            System.out.println("No compilation");
        } else {
            System.out.println("Inavalid response");
        }
        sc.close();
    }

    private void compileTest(Scanner sc) throws Exception {
        boolean success = true;
        String response;
        if(success) {
            //Compilation and return of an instance of the class
            Class<?> clazz = Compilation.compileClass("model."+ inputTestClassName,inputTestClassCode);
            //Ask if you want to execute the code
            System.out.print("¿Do you want to execute the code? (y/n): ");
            response = sc.next();
            if (response.equalsIgnoreCase("y")) {
                //Run the test
                Execution.runTests(clazz);
            }else if (response.equalsIgnoreCase("n")) {
                System.out.println("No execution");
            } else {
                System.out.println("Inavalid response");
            }
        }
    }

    private void compileClass() throws Exception {
        Compilation.compileClass("model." + inputClassName,inputClassCode);
        FileCreator.createFile(inputClassName,inputClassCode);
    }
}
