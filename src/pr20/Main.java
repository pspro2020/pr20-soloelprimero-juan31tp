package pr20;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    static final int nRows = 5;
    static final int nColumns = 5;

    public static void main(String[] args) {

        ThreadPoolExecutor fixedThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int matrix[][] =  new int[nRows][nColumns];
        int lookFor;
        List<Task> tasks = new ArrayList<>();

        lookFor = getRndNumber(20, 1);
        System.out.println("The number we are looking for is: " + lookFor);

        createMatrix(matrix);
        showMatrix(matrix);

        for (int i = 0; i < matrix.length; i++){
            tasks.add(new Task(i, matrix[i], lookFor));
        }

        try {
            Place result = fixedThreadPool.invokeAny(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            fixedThreadPool.shutdown();
        }

    }

    private static void showMatrix(int[][] matrix) {
        System.out.println("The matrix is:");
        for (int i=0; i<nRows; i++){
            for (int j=0; j<nColumns; j++){
                System.out.printf(String.valueOf(matrix[i][j]));
            }
        }
        System.out.println("");
    }

    private static void createMatrix(int[][] matrix) {
        for (int i=0; i<nRows; i++){
            for (int j=0; j<nColumns; j++){
                matrix[i][j]=getRndNumber(20, 1);
            }
        }
    }

    private static int getRndNumber(int max, int min) {
        return (int) (Math.random()*(max-min)+min);
    }

}
