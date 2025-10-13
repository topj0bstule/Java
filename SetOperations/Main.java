import StudentPackage.Student;
import SetOperationPackage.SetOperation;
import FilePackage.FileProcessor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter file path to the first input file: ");
            String fileA = scanner.nextLine();

            System.out.print("Enter file path to the second input file: ");
            String fileB = scanner.nextLine();

            System.out.print("Enter file path to the output file: ");
            String outputFile = scanner.nextLine();

            Set<Student> setA = FileProcessor.readFromFile(fileA);
            Set<Student> setB = FileProcessor.readFromFile(fileB);

            System.out.println("Chooce operation:");
            System.out.println("1 — union");
            System.out.println("2 — intersection");
            System.out.println("3 — difference");

            int choice = Integer.parseInt(scanner.nextLine());
            Set<Student> result;

            if(choice == 1) result = SetOperation.union(setA, setB);
            else if (choice == 2) result = SetOperation.intersection(setA, setB);
            else if(choice == 3) result = SetOperation.difference(setA, setB);
            else throw new IllegalArgumentException("Wrong number");
            FileProcessor.writeToFile(outputFile, result);
            System.out.println("Operation completed. Result is recorded to the file " + outputFile);

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}

