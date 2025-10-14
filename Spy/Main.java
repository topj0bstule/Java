import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String P = scanner.nextLine().trim();
        Set<String> results = new HashSet<>();

        for (int i = 0; i < P.length(); i++) {
            char originalDigit = P.charAt(i);
            for (char replacement = '0'; replacement <= '9'; replacement++) {
                if (replacement == originalDigit) continue;
                if (i == 0 && replacement == '0') continue;

                StringBuilder candidate = new StringBuilder(P);
                candidate.setCharAt(i, replacement);
                String newNumberStr = candidate.toString();
                long newNumber = Long.parseLong(newNumberStr);

                if (newNumber % 9 == 0) {
                    results.add(newNumberStr);
                }
            }
        }

        for (String result : results) {
            System.out.println(result);
        }
    }
}
