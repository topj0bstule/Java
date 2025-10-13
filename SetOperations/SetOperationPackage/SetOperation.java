package SetOperationPackage;

import StudentPackage.Student;
import java.util.*;

public class SetOperation {
    public static Set<Student> union(Set<Student> a, Set<Student> b) {
        Set<Student> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    public static Set<Student> intersection(Set<Student> a, Set<Student> b) {
        Set<Student> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    public static Set<Student> difference(Set<Student> a, Set<Student> b) {
        Set<Student> result = new HashSet<>(a);
        result.removeAll(b);
        return result;
    }
}
