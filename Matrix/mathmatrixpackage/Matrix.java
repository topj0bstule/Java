package mathmatrixpackage;
import java.util.Scanner;
public class Matrix {

    int width;
    int height;
    int row;
    int column;
    int matrix[][];
    int sum;
    int maxDistance;

    public Matrix(int height, int width) {
        this.height = height;
        this.width = width;
        matrix = new int[height][width];
        sum = 0;
    }

    public void fillMatrix(Scanner scanner) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = scanner.nextInt();
                System.out.println("Элемент [" + (i + 1) + "][" + (j + 1) + "]: " + matrix[i][j]);
            }
        }
    }

    public void calculateDistances() {
        maxDistance = 0;
        int maxIndex = -1;
        for (int i = 1; i < height; i++) {
            sum = 0;
            for (int j = 0; j < width; j++) {
                int term = matrix[0][j] * matrix[i][j];
                sum += term;
            }
            System.out.printf("Расстояние %d строки: %d\n", i + 1, sum);
            if (sum > maxDistance) {
                maxDistance = sum;
                maxIndex = i;
            }
        }
        if (maxIndex!=-1) {
            System.out.println("Максимально удалённая строка: " + (maxIndex + 1));
            System.out.println("Максимальная дистанция: " + maxDistance);
        }
        else System.out.println("Я разочарован в тебе");
    }

    public void findMinOfLocalMax() {
        int MinOfMaximum = Integer.MAX_VALUE;
        boolean found = false;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(width == 1 && height == 1){
                    MinOfMaximum = matrix[0][0];
                    System.out.println("Минимальный среди локальных максимумов: " + MinOfMaximum);
                    break;
                }
                int current = matrix[i][j];
                boolean isLocalMax = true;
                if (i == 0 && j == 0) {
                    if (current <= matrix[i][j+1] || 
                        current <= matrix[i+1][j] || 
                        current <= matrix[i+1][j+1])
                        isLocalMax = false;
                }
                else if (i == 0 && j == width - 1) {
                    if (current <= matrix[i][j - 1] || current <= matrix[i + 1][j] || current <= matrix[i + 1][j - 1])
                        isLocalMax = false;
                }
                else if (i == height - 1 && j == 0) {
                    if (current <= matrix[i - 1][j] || current <= matrix[i][j + 1] || current <= matrix[i - 1][j + 1])
                        isLocalMax = false;
                }
                else if (i == height - 1 && j == width - 1) {
                    if (current <= matrix[i - 1][j] || current <= matrix[i][j - 1] || current <= matrix[i - 1][j - 1])
                        isLocalMax = false;
                }
                else if (i == 0) {
                    if (current <= matrix[i][j - 1] || current <= matrix[i][j + 1] ||
                        current <= matrix[i + 1][j - 1] || current <= matrix[i + 1][j] || current <= matrix[i + 1][j + 1])
                        isLocalMax = false;
                }
                else if (i == height - 1) {
                    if (current <= matrix[i][j - 1] || current <= matrix[i][j + 1] ||
                        current <= matrix[i - 1][j - 1] || current <= matrix[i - 1][j] || current <= matrix[i - 1][j + 1])
                        isLocalMax = false;
                }
                else if (j == 0) {
                    if (current <= matrix[i - 1][j] || current <= matrix[i + 1][j] ||
                        current <= matrix[i - 1][j + 1] || current <= matrix[i][j + 1] || current <= matrix[i + 1][j + 1])
                        isLocalMax = false;
                }
                else if (j == width - 1) {
                    if (current <= matrix[i - 1][j] || current <= matrix[i + 1][j] ||
                        current <= matrix[i - 1][j - 1] || current <= matrix[i][j - 1] || current <= matrix[i + 1][j - 1])
                        isLocalMax = false;
                }
                else {
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if (di == 0 && dj == 0) continue;
                            if (current <= matrix[i + di][j + dj]) {
                                isLocalMax = false;
                                break;
                            }
                        }
                        if (!isLocalMax) break;
                    }
                }
                if (isLocalMax) {
                    found = true;
                    if (current < MinOfMaximum) {
                        MinOfMaximum = current;
                    }
                }
            }
        }

        if (found) {
            System.out.println("Минимальный среди локальных максимумов: " + MinOfMaximum);
        } else {
            System.out.println("Локальные максимумы не найдены.");
        }
    }
    public void maxEl(){
        int maxEl = Integer.MIN_VALUE;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(matrix[i][j] > maxEl) {
                    maxEl = matrix[i][j];
                    row = i;
                    column = j;
                }
            }
        }
        System.out.println("Максимальный элемент: " + maxEl);
    }
    public void delRowAndColomnOfBiggestNumber(){
        maxEl();
        Matrix newMatrix = new Matrix(height - 1, width - 1);
        int newI = 0;
        for (int i = 0; i < height; i++) {
            if (i == row) continue;
            int newJ = 0;
            for (int j = 0; j < width; j++) {
                if (j == column) continue;
                newMatrix.matrix[newI][newJ] = matrix[i][j];
                newJ++;
            }
            newI++;
        }
        System.out.println("Матрица после удаления строки " + (row + 1) + " и столбца " + (column + 1) + ":");
        newMatrix.printMatrix();
    }
    public void printMatrix(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j]);
                if(j != width - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}