package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/*
This class represents a text file and is responsible for creating and writing in it.
 */
public class FileParser {

    String filename;
    //this method creates the file in the project directory

    public void createFile(String filename){
        try {
            File myObj = new File(filename);
            this.filename = filename;
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //this method writes its parameter data to the end of the file
    public void writeToFile(List<String> listToWrite){
        try {
            FileWriter myWriter = new FileWriter(filename,true);
            for (String s: listToWrite) {
                myWriter.write(s);
                myWriter.write("\n");
            }
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}

