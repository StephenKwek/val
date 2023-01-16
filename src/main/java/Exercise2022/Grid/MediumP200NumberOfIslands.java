package Exercise2022.Grid;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Stack;

/**
 * 200. Number of Islands
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 */
public class MediumP200NumberOfIslands {
    private static int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};

    private void gridDFS(char[][] grid, boolean[][] seen, Pair<Integer, Integer> root) {
        Stack<Pair<Integer, Integer>> s = new Stack<>();
        s.add(root);
        seen[root.getLeft()][root.getRight()] = true;
        while (!s.isEmpty()) {
            Pair<Integer, Integer> cur = s.pop();
            for (int[] dir : directions) {
                int newX = cur.getLeft() + dir[0];
                int newY = cur.getRight() + dir[1];
                if (0 <= newX && newX < grid.length
                        && 0 <= newY && newY < grid[0].length
                        && grid[newX][newY] == '1'
                        && !seen[newX][newY]) {
                    s.add(ImmutablePair.of(newX, newY));
                    seen[newX][newY] = true;
                }
            }
        }
    }

    public int numIslands(char[][] grid) {
        int numIslands = 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] seen = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!seen[i][j] && grid[i][j] == '1') {
                    numIslands++;
                    gridDFS(grid, seen, Pair.of(i, j));
                }
            }
        }
        return numIslands;
    }

    public static void main(String[] args) {
        MediumP200NumberOfIslands p = new MediumP200NumberOfIslands();
        char[][] grid = {
                        {'1','1','1','1','0'},
                        {'1','1','0','1','0'},
                        {'1','1','0','0','0'},
                        {'0','0','0','0','0'}
                        };
        assert 1 == p.numIslands(grid);

        char[][] grid2 = {
                            {'1','1','0','0','0'},
                            {'1','1','0','0','0'},
                            {'0','0','1','0','0'},
                            {'0','0','0','1','1'}
                        };
        assert 3 == p.numIslands(grid2);
    }
}
