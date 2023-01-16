package Exercise2022.Grid;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 827. Making A Large Island
 *
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 *
 * Return the size of the largest island in grid after applying this operation.
 *
 * An island is a 4-directionally connected group of 1s.
 */
public class H_P827_MakingALargeIsland {
    private static final int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    class Cell {
        public int x;
        public int y;
        public int change;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Set<Cell> seen = new HashSet<>();
        int best = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k <= 1; k++) {
                    if (grid[i][j] != 1 && k != 0) {
                        int change = grid[i][j];
                        Cell start = new Cell(i, j);
                        if (!seen.contains(start)) {
                            Stack<Cell> s = new Stack<>();
                            s.add(start);
                            int curBest = 0;
                            while (!s.isEmpty()) {
                                Cell cur = s.pop();
                                curBest++;
                                for (int[] dir : directions) {
                                    int newX = cur.x + dir[0];
                                    int newY = cur.y + dir[1];
                                    if (0 <= newX && newX < m && 0 <= newY && newY < n) {
                                        Cell next = new Cell(newX, newY);
                                        if (!seen.contains(next)) {
                                            s.add(next);
                                        } else if (change == 0 && grid[newX][newY] == 0) {
                                            next = new Cell(newX, newY);
                                            if (!seen.contains(next)) {
                                                s.add(next);
                                            }
                                        }
                                    }
                                }
                            }
                            best = Math.max(curBest, best);
                        }
                    }
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        H_P827_MakingALargeIsland p = new H_P827_MakingALargeIsland();
        assert 3 == p.largestIsland(new int[][] {{1,0}, {0,1}});
        assert 4 == p.largestIsland(new int[][] {{1,0}, {0,1}});
        assert 4 == p.largestIsland(new int[][] {{1,1}, {1,1}});
    }
}
