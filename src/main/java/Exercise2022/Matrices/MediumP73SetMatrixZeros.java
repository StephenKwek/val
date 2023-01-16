package Exercise2022.Matrices;

import Exercise2022.ArrayProblems.TestUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MediumP73SetMatrixZeros {
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int k = 0; k < n + m - 1; k++) {
            Set<Integer> toZeros = new HashSet<>();
            for (int j = 0; j <= Math.min(k,m-1); j++) {
                int i = Math.min(n-1,k - j);
                boolean flip = false;
                if (matrix[i][j] == 1) {
                    for (int i1 = 0; i1 < i && !flip; i1++) {
                        flip = matrix[i1][j] == 0;
                    }
                    for (int j1 = 0; j1 < j && !flip; j1++) {
                        flip = matrix[i][j1] == 0;
                    }
                }
                if (flip) {
                    toZeros.add(i);
                }
            }
            for (int j = 0; j <= Math.min(k,m-1); j++) {
                int i = Math.min(n-1,k - j);
                if (matrix[i][j] == 0) {
                    for (int i1 = 0; i1 < i; i1++) {
                        matrix[i1][j] = 0;
                    }
                    for (int j1 = 0; j1 < i; j1++) {
                        matrix[i][j1] = 0;
                    }
                } else if (toZeros.contains(i)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("");
        MediumP73SetMatrixZeros p = new MediumP73SetMatrixZeros();
        int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
        int[][] expected = {{1,0,1},{0,0,0},{1,0,1}};
        p.setZeroes(matrix);
        TestUtil.equals(expected, matrix);
        matrix = new int[][] {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        expected = new int[][] {{0,0,0,0},{0,4,5,0},{0,3,1,0}};
        TestUtil.equals(expected, matrix);
    }
}
