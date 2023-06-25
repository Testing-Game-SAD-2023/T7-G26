package requirement_t7.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import requirement_t7.model.util.FileCreator;

import java.io.BufferedReader;
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

    private boolean compiled;

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
        String res="";
        compiled=false;
        //Compile input class
        try {
            res+=compileClass();
        } catch (Exception e) {
            //Return String with error if something fails
            return "Error in compiling" + inputClassName + " => " + e.getMessage();
        }
        try {
            res+=compileTest();
        } catch (Exception e) {
            return "Error in compiling" + inputTestClassName + " => " + e.getMessage();
        }
        if(res.equals("")) {
            compiled = true;
            res="Compiled!";
        }
        return res;
    }

    public String execute(){
        String res;
        if(compiled){
        //Run the test
        res= Execution.runTests();
       }
        else{
            res = "Cannot execute because you have not compiled";
        }
        return res;
    }

    private String compileTest() throws Exception {
        fileCreator.createFile("src/test/java/requirement_t7/classLoaded/"+inputTestClassName,inputTestClassCode);
        String res = Compilation.compileTest();
        return res;
    }

    private String compileClass() throws Exception {
        fileCreator.createFile("src/main/java/requirement_t7/classLoaded/"+inputClassName,inputClassCode);
        String res= Compilation.compileClass();
        return res;
    }

    public void setInputTestClassName(String inputTestClassName) {
        this.inputTestClassName = inputTestClassName;
    }

    public void setInputClassName(String inputClassName) {
        this.inputClassName = inputClassName;
    }
}
