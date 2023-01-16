package Exercise2022.Matrices;

import Exercise2022.ArrayProblems.TestUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class M_P54_SpiralMatrix {
    static final int[][] DIRECTION = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    private boolean hitBoundary(int[] cell, int[] boundary, int dir) {
        int tmpX = cell[0] + DIRECTION[dir][0];
        int tmpY = cell[1] + DIRECTION[dir][1];
        if (dir == 0 && tmpY > boundary[0]) {
            boundary[3]++;
            return true;
        } else if (dir == 1 && tmpX > boundary[1]) {
            boundary[0]--;
            return true;
        } else if (dir == 2 && tmpY < boundary[2]) {
            boundary[2]--;
            return true;
        } else if (dir == 3 && tmpX < boundary[3]) {
            boundary[3]++;
            return true;
        }
        return false;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> soln = new ArrayList<>();
        int n = matrix.length;
        int m = matrix[0].length;
        int[] boundary = new int[]{m-1, n-1, 0, 0}; // {right, bottom, left, top}
        int dir = 0;
        int count = 0;
        int[] cell = {0, 0};
        while (count < n*m) {
            soln.add(matrix[cell[0]][cell[1]]);
            if (hitBoundary(cell, boundary, dir)) {
                dir = (dir + 1) % 4;
            }
            cell[0] += DIRECTION[dir][0];
            cell[1] += DIRECTION[dir][1];
            count++;
        }
        return soln;
    }

    public static void main(String[] args) {
        M_P54_SpiralMatrix p = new M_P54_SpiralMatrix();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> expected = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);

        List<Integer> actual = p.spiralOrder(matrix);
        assert actual.equals(expected);

        matrix = new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        expected = Arrays.asList(1,2,3,4,8,12,11,10,9,5,6,7);
        actual = p.spiralOrder(matrix);
        assert actual.equals(expected);

    }
}
