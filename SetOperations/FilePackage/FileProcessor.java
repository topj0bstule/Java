package FilePackage;

import StudentPackage.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileProcessor {

    public static Set<Student> readFromFile(String path) throws FileNotFoundException {
        Set<Student> students = new HashSet<>();
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                students.add(Student.fromString(line));
            }
        }

        scanner.close();
        return students;
    }

    public static void writeToFile(String path, Set<Student> students) throws IOException {
        File file = new File(path);
        FileWriter writer = new FileWriter(file);
        if(students.size() == 0) writer.write("empty set");
        for (Student s : students) {
            writer.write(s.toString() + System.lineSeparator());
        }

        writer.close();
    }
}
