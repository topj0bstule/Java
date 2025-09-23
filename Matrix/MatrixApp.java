import mathmatrixpackage.Matrix;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class MatrixApp {

    int MyProblems[];
    Scanner scanner;
    public static void main(String[] args) {
        MatrixApp program = new MatrixApp();
        program.run();
    }

    public void run() {
        try{
            int width;
            int height;
            File inputFile = new File("matrixInput.txt");
            scanner = new Scanner(inputFile);
            int myNumberInGroup = 10;
            System.out.println("Мой номер в группе: " + myNumberInGroup);
            System.out.printf("Мои задачи: ");
            MyProblems = new int[3];
            for (int i = 0; i < MyProblems.length; i++) {
                MyProblems[i] = 14 * i + myNumberInGroup;
                System.out.printf(MyProblems[i] + " ");
            }
            System.out.println();
            width = scanner.nextInt();
            System.out.println("Считанная ширина матрицы: " + width);
            height = scanner.nextInt();
            System.out.println("Считанная высота матрицы: " + height);

            Matrix matrix = new Matrix(height, width);
            matrix.fillMatrix(scanner);
            System.out.println("Считанная матрица: ");
            matrix.printMatrix();
            matrix.calculateDistances();
            matrix.findMinOfLocalMax();
            if (height == width) matrix.delRowAndColomnOfBiggestNumber();
            else {
                System.out.println("Матрица не квадратная: ");
                matrix.printMatrix();
            }
        }
        catch(FileNotFoundException exception){
            System.out.println("Файл не найден! Отключаю работу программы");
        }
    }
}