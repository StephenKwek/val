package Exercise2022.Graph;

import java.util.*;

public class H_P317_ShortestDistanceFromAllBuildings {
    private static final int[][] DIRECTIONS = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};
    public int shortestDistance(int[][] grid) {
        List<int[]> buildings = new ArrayList<>();
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    buildings.add(new int[] {i, j});
                }
            }
        }

        int[][] costs = new int[n][m];
        for (int[] b : buildings) {
            Integer[][] seen = new Integer[n][m];
            for (int i = 0; i < n; i++) {
                Arrays.fill(seen[i], -1);
            }
            Queue<int[]> q = new LinkedList<>();
            q.add(b);
            seen[b[0]][b[1]] = 0;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                Arrays.stream(DIRECTIONS)
                        .map(dir -> new int[] {dir[0] + cur[0], dir[1] + cur[1]})
                        .filter(cell -> 0 <= cell[0] && cell[0] <= (n-1))
                        .filter(cell -> 0 <= cell[1] && cell[1] <= (m-1))
                        .filter(cell -> grid[cell[0]][cell[1]] != 2 && seen[cell[0]][cell[1]] == -1)
                        .forEach(cell -> {
                            seen[cell[0]][cell[1]] = seen[cur[0]][cur[1]] + 1;
                            costs[cell[0]][cell[1]] += seen[cur[0]][cur[1]] + 1;
                            q.add(cell);
                        });
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (costs[i][j] > 0) {
                    min = Math.min(min, costs[i][j]);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) {
        H_P317_ShortestDistanceFromAllBuildings p = new H_P317_ShortestDistanceFromAllBuildings();
        //assert 7 == p.shortestDistance(new int[][] { {1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0} });
        assert 1 == p.shortestDistance(new int[][] { {1, 0}});
        assert -1 == p.shortestDistance(new int[][] { {1}});
    }
}
