package Exercise2022.Matrices;

import Exercise2022.ArrayProblems.TestUtil;

/**
 * 48. Rotate Image
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 * https://leetcode.com/problems/rotate-image/
 */
public class M_P48_RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int mid = n/2;
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < ((n%2==1) ? mid+1: mid); j++) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[n - 1 - j][i]; // (0,0) <- (2,0), (1,0) <- (2,1)
                    matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];      // (2,0) <- (2,2), (2,1) <- (1,2)
                    matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];      // (2,2) <- (0,2), (0,1) <- (0,1)
                    matrix[j][n - 1 - i] = temp; // (0,2) <- (0,0), (0,1) <- (1,0)
            }
        }
    }

    public static void main(String[] args) {
        M_P48_RotateImage p = new M_P48_RotateImage();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] expected = {{7,4,1},{8,5,2},{9,6,3}};
        p.rotate(matrix);
        TestUtil.equals(expected, matrix);

        matrix = new int[][] {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        expected = new int[][] {{15,13,2,5},{14,3,4,1},{12,6,8,9},{16,7,10,11}};
        p.rotate(matrix);
        TestUtil.equals(expected, matrix);

    }
}
