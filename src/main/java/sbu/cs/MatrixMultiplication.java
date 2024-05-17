package sbu.cs;

import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplication {

    public static class BlockMultiplier implements Runnable
    {
        private final List<List<Integer>> A;
        private final List<List<Integer>> B;
        private final List<List<Integer>> tempMatrixProduct;
        private final int startingRow;
        private final int endingRow;

        public BlockMultiplier(List<List<Integer>> A, List<List<Integer>> B, List<List<Integer>> tempMatrixProduct, int startingRow, int endingRow) {
            this.A = A;
            this.B = B;
            this.tempMatrixProduct = tempMatrixProduct;
            this.startingRow = startingRow;
            this.endingRow = endingRow;
        }

        @Override
        public void run() {
            for (int i = startingRow; i < endingRow; i++) {
                for (int j = 0; j < B.getFirst().size(); j++) {
                    int sum = 0;
                    for (int k = 0; k < A.getFirst().size(); k++) {
                        sum += A.get(i).get(k) * B.get(k).get(j);
                    }
                    tempMatrixProduct.get(i).set(j, sum);
                }
            }
        }
    }




    public static List<List<Integer>> ParallelizeMatMul(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B)
    {
        int rows = matrix_A.size();
        int quarter = rows / 4;

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Integer> rowC = new ArrayList<>();
            for (int j = 0; j < matrix_B.getFirst()
                    .size(); j++) {
                rowC.add(0);
            }
            result.add(rowC);
        }


        Thread t1 = new Thread(new BlockMultiplier(matrix_A, matrix_B, result, 0, quarter));
        Thread t2 = new Thread(new BlockMultiplier(matrix_A, matrix_B, result, quarter, quarter * 2));
        Thread t3 = new Thread(new BlockMultiplier(matrix_A, matrix_B, result, quarter * 2, quarter * 3));
        Thread t4 = new Thread(new BlockMultiplier(matrix_A, matrix_B, result, quarter * 3, rows));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
