package requirement_t7.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import requirement_t7.model.util.FileCreator;
import requirement_t7.model.util.FileToStringReader;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class Game {

    private String inputTestClassName;
    private String inputClassName;
    private String inputClassCode;
    private String inputTestClassCode;

    @Autowired
    private FileCreator fileCreator;

    private Class<?> clazz;

    public Game(){
    }

    private String obtainCode(String input){
        String res;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(input+".txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            res = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public String compile(){
        inputClassCode = obtainCode(inputClassName);
        inputTestClassCode = obtainCode(inputTestClassName);
        //Compile input class
        try {
            compileClass();
        } catch (Exception e) {
            //Return String with error if something fails
            return "Error in compiling" + inputClassName + " => " + e.getMessage();
        }
        try {
            compileTest();
        } catch (Exception e) {
            return "Error in compiling" + inputTestClassName + " => " + e.getMessage();
        }
        return "Compiled";
    }

    public String execute(){
        String res;
        if(clazz != null){
            //Run the test
            res = Execution.runTests(clazz);
        }
        else{
            res = "Cannot execute because you have not compiled";
        }
        return res;
    }

    private void compileTest() throws Exception {
        clazz = Compilation.compileClass("requirement_t7.classLoaded."+ inputTestClassName,inputTestClassCode);
    }

    private void compileClass() throws Exception {
        Compilation.compileClass("requirement_t7.classLoaded." + inputClassName,inputClassCode);
        fileCreator.createFile(inputClassName,inputClassCode);
    }

    public void setInputTestClassName(String inputTestClassName) {
        this.inputTestClassName = inputTestClassName;
    }

    public void setInputClassName(String inputClassName) {
        this.inputClassName = inputClassName;
    }
}
