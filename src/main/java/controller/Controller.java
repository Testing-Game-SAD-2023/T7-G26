package controller;

import util.FileToStringReader;

import java.io.File;
import java.io.IOException;

public class Controller {
    private File inputClassFile;
    private File inputTestClassFile;

    public Controller(){
        this.inputClassFile = new File("InputClass.txt");
        this.inputTestClassFile = new File("InputTestClass.txt");
    }

}
