package StudentPackage;
import java.util.Objects;

public class Student {
    public long id;
    public String name;
    public int group;
    public double grade;

    public Student(long id, String name, int group, double grade) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.grade = grade;
    }

    public Student(){
        this.id = 0;
        this.name = "";
        this.group = 0;
        this.grade = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return id == s.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + group + "," + grade;
    }

    public static Student fromString(String line) {
        String[] parts = line.split(",");
        return new Student(
            Long.parseLong(parts[0]),
            parts[1],
            Integer.parseInt(parts[2]),
            Double.parseDouble(parts[3])
        );
    }
}
