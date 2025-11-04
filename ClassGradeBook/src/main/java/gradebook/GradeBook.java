package gradebook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class GradeBook {
    public Student student;
    public ArrayList<Session> sessions;

    public GradeBook(){}
    public GradeBook(Student student_, ArrayList<Session> sessions_) {
        this.student = student_;
        this.sessions = sessions_;
    }

    public Boolean isExcellentSession(Session session) {
        if (session == null || session.subjects == null)
            return false;
        for (Session.Subject subject : session.subjects)
            if (subject.exam == null || subject.exam < 9 || subject.exam > 10) return false;
        return true;
    }


    public Double gpa(Session session) {
        Double result = 0.0;
        Integer count = 0;
        for (Session.Subject subject : session.subjects) {
            result += subject.exam;
            count++;
        }
        result /= count;
        return result;
    }

    public ArrayList<Integer> getExcellentSessionNumbers() {
        ArrayList<Integer> excellentSessions = new ArrayList<>();
        for (Integer i = 0; i < sessions.size(); i++) {
            if (isExcellentSession(sessions.get(i))) {
                excellentSessions.add(i + 1);
            }
        }
        return excellentSessions;
    }

    public Boolean hasExcellentSessions() {
        return !getExcellentSessionNumbers().isEmpty();
    }

    public static void outJsonFile(String filename, ArrayList<GradeBook> gradeBooks) throws IOException {
        ArrayList<GradeBook> filtered = new ArrayList<>();
        for (GradeBook gbBook : gradeBooks) {
            if (gbBook.hasExcellentSessions()) {
                filtered.add(gbBook);
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
           gson.toJson(filtered.toArray(new GradeBook[0]), GradeBook[].class, writer);
        }
    }

    public static ArrayList<GradeBook> createFromJsonFile(String filename) throws FileNotFoundException { 
        Gson gson = new Gson();
        Scanner scanner = new Scanner(new File(filename));
        StringBuilder jsonBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            jsonBuilder.append(scanner.nextLine());
        }
        scanner.close();
        String json = jsonBuilder.toString();
        GradeBook[] gradeBookArray = gson.fromJson(json, GradeBook[].class);
        return new ArrayList<>(Arrays.asList(gradeBookArray));
    }


    public static class Student {
        public String name;
        public String surname;
        public String patronymic;
        public String speciality;
        public Integer course;
        public Integer group;

        public Student(){}
        public Student(String surname_, String name_, String patronymic_,
                       Integer course_, Integer group_, String speciality_) {
            this.surname = surname_;
            this.name = name_;
            this.patronymic = patronymic_;
            this.course = course_;
            this.group = group_;
            this.speciality = speciality_;
        }
    }

    public static class Session {
        public ArrayList<Subject> subjects;
        public Session(){}

        public static class Subject {
            public String subject;
            public String teachersSurname;
            public String teachersName;
            public String teachersPatronymic;
            public Boolean credit;
            public Integer exam;

            public Subject(){}
            public Subject(String subject_, String teachersSurname_, String teachersName_,
                           String teachersPatronymic_, Boolean credit_, Integer exam_) {
                this.subject = subject_;
                this.teachersSurname = teachersSurname_;
                this.teachersName = teachersName_;
                this.teachersPatronymic = teachersPatronymic_;
                this.credit = credit_;
                this.exam = exam_;
            }
        }
    }
}