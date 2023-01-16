package Exercise2022.Grid;

import New.SearchAndSort;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 1293. Shortest Path in a Grid with Obstacles Elimination
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.

 * Return the minimum number of steps to nextFrontier from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such nextFrontier return -1.
 * https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 */
public class H_P1293_ShortestPathInAGridWithObstacles {
    private static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static class Cell {
        int _x;
        int _y;
        int _bombs;
        int _level;

        Cell(int x, int y, int level, int bombs) {
            _x = x;
            _y = y;
            _level = level;
            _bombs = bombs;
        }
    }

    public static int shortestPathObstaclesElimination(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] seen = new int[n][m];
        for (int[] row : seen) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        LinkedList<Cell> q = new LinkedList<>();
        q.add(new Cell(0, 0, 0, k));
        seen[0][0] = k;
        while (!q.isEmpty()) {
            Cell cur = q.removeFirst();
            for (int[] direction : DIR) {
                int newX = cur._x + direction[0];
                int newY = cur._y + direction[1];
                if (0 <= newX && newX < n && 0 <= newY && newY < m) {
                    if (newX == n - 1 && newY == m - 1) {
                        return cur._level + 1;
                    }
                    int bombs = cur._bombs - ((grid[newX][newY] == 1) ? 1 : 0);
                    if (bombs <= seen[newX][newY] || bombs < 0) {
                        continue;
                    }
                    q.addLast(new Cell(newX, newY, cur._level + 1, bombs));
                    seen[newX][newY] = bombs;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid =
                {{0,0,0},
                        {1,1,0},
                        {0,0,0},
                        {0,1,1},
                        {0,0,0}};
        assert 10 == shortestPathObstaclesElimination(grid, 0);
        assert 6 == shortestPathObstaclesElimination(grid, 1);

        grid = new int[][] {{0,1,1}, {1,1,1}, {1,0,0}};
        assert -1 == shortestPathObstaclesElimination(grid, 1);
        assert 4 == shortestPathObstaclesElimination(grid, 1);
    }
}
