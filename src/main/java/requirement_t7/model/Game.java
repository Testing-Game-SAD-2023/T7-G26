package requirement_t7.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import requirement_t7.model.util.FileCreator;
import requirement_t7.model.util.FileDeletor;

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
    private FileDeletor fileDeletor;

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
        res+=compileClass();
        res+=compileTest();

        if(res.equals("")) {
            compiled = true;
            res="Compiled";
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

        fileDeletor.deleteFile("src/main/java/requirement_t7/"+inputClassName+".java");
        fileDeletor.deleteFile("src/test/java/requirement_t7/"+inputTestClassName+".java");

        return res;
    }

    private String compileTest(){
        return Compilation.compileTest(inputTestClassName, inputTestClassCode);
    }

    private String compileClass(){
        return Compilation.compileClass(inputClassName,inputClassCode);
    }

    public void setInputTestClassName(String inputTestClassName) {
        this.inputTestClassName = inputTestClassName;
    }

    public void setInputClassName(String inputClassName) {
        this.inputClassName = inputClassName;
    }
}
