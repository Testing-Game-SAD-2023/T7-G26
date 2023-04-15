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

}
