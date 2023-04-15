package controller;

import util.FileToStringReader;

import java.io.File;
import java.io.IOException;

public class Controller {
    private File inputClassFile;
    private File inputTestClassFile;

    public Controller(){
        this.inputClassFile = new File("inputClass.txt");
        this.inputTestClassFile = new File("inputTestClass.txt");
    }

    public void test(){
        String content1;
        String content2;
        try {
            content1 = FileToStringReader.convert(inputClassFile);
            content2 = FileToStringReader.convert(inputTestClassFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(content1 + content2);
    }

}
