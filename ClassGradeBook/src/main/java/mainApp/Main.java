package mainApp;

import gradebook.GradeBook;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        try {
            ArrayList<GradeBook> gradeBooks = GradeBook.createFromJsonFile("input.json");
            if (!gradeBooks.isEmpty()) {
                GradeBook.outJsonFile("output.json", gradeBooks);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}