import java.io.*;
import java.util.*;

public class TextAlignment {
    private int lineWidth;

    public TextAlignment(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public static class FileWorker {

        public static String readFromFile(String fileName) throws IOException {
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(fileName);
                StringBuilder content = new StringBuilder();
                int charCode;

                while ((charCode = fileReader.read()) != -1) {
                    char ch = (char) charCode;
                    if (ch != '\n') {
                        content.append(ch);
                    }
                }
                return content.toString();
            } finally {
                if (fileReader != null) {
                    fileReader.close();
                }
            }
        }

        public static void writeToFile(String fileName, String content) throws IOException {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(fileName);
                fileWriter.write(content);
            } finally {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            }
        }
    }

    public String formatText(String text) {
        StringBuilder formattedText = new StringBuilder();
        String[] paragraphs = text.split("\t");

        for (int i = 0; i < paragraphs.length; i++) {
            formattedText.append(formatParagraph(paragraphs[i]));
        }

        return formattedText.toString();
    }

    private String formatParagraph(String paragraph) {
        paragraph = cleanText(paragraph);
        if (paragraph.isEmpty()) return "\n";

        List<String> words = splitIntoWords(paragraph);
        List<String> lines = createLines(words);

        return justifyLines(lines);
    }

    private String cleanText(String text) {
        return text.trim().replaceAll("\\s+", " ");
    }

    private List<String> splitIntoWords(String text) {
        return Arrays.asList(text.split(" "));
    }

    private List<String> createLines(List<String> words) {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (canAddWord(currentLine, word)) {
                addWordToLine(currentLine, word);
            } else {
                if (currentLine.length() > 0) {
                    lines.add(currentLine.toString());
                }
                currentLine = new StringBuilder(word);
            }
        }

        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        return lines;
    }

    private boolean canAddWord(StringBuilder line, String word) {
        if (line.length() == 0) {
            return word.length() <= lineWidth;
        }
        return line.length() + 1 + word.length() <= lineWidth;
    }

    private void addWordToLine(StringBuilder line, String word) {
        if (line.length() > 0) {
            line.append(" ");
        }
        line.append(word);
    }

    private String justifyLines(List<String> lines) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < lines.size(); i++) {
            if (i == lines.size() - 1) {
                result.append(lines.get(i)).append("\n");
            } else {
                result.append(justifyLine(lines.get(i))).append("\n");
            }
        }

        return result.toString();
    }

    private String justifyLine(String line) {
        if (line.length() >= lineWidth) {
            return line;
        }

        String[] words = line.split(" ");
        if (words.length == 1) {
            return padRight(line, lineWidth);
        }

        int totalSpacesToAdd = lineWidth - line.length();
        int wordGaps = words.length - 1;
        int baseSpaces = totalSpacesToAdd / wordGaps;
        int extraSpaces = totalSpacesToAdd % wordGaps;

        StringBuilder justified = new StringBuilder(words[0]);

        for (int i = 1; i < words.length; i++) {
            int spaces = baseSpaces + (i <= extraSpaces ? 1 : 0);
            appendSpaces(justified, spaces + 1);
            justified.append(words[i]);
        }

        return justified.toString();
    }

    private void appendSpaces(StringBuilder builder, int count) {
        for (int i = 0; i < count; i++) {
            builder.append(" ");
        }
    }

    private String padRight(String text, int length) {
        if (text.length() >= length) {
            return text;
        }
        return String.format("%-" + length + "s", text);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ширину строки для выравнивания: ");
        int width = scanner.nextInt();

        try {
            String inputText = FileWorker.readFromFile("input.txt");

            TextAlignment formatter = new TextAlignment(width);
            String formattedText = formatter.formatText(inputText);

            FileWorker.writeToFile("output.txt", formattedText);

            System.out.println("Текст успешно отформатирован!");
            System.out.println("Результат сохранен в output.txt");

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
