import stringCalcPackage.stringCalc;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Main{

    private Scanner scanner;
    public static void main(String args[]){
        Main main = new Main();
        main.run();
    }
    public void run(){
        try{
            File inputFile = new File("input.txt");
            scanner = new Scanner(inputFile);
            scanner.useDelimiter("\\Z");
            String data = scanner.next();
            String[] tokens = data.split("[ ,.;:!?\\r\\n\\t]+");
            System.out.println("Scanned string without delimiters: ");
            for(int i = 0; i < tokens.length; i++) {
                System.out.printf(tokens[i]);
                if(i != tokens.length - 1) 
                    System.out.printf(" ");
                else System.out.println(" ");
            }
            stringCalc calc = new stringCalc(tokens);
            ArrayList<String> result = calc.findPal(tokens);
            if(result.size() == 0) System.out.println("No matches.");
            else{
                System.out.printf("Found %d matches:\n", result.size());
                System.out.println(result);
            }

        }
        catch(FileNotFoundException exception){
            System.out.println("Failed to open file.");
        }
    }
}