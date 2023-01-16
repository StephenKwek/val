package Exercise2022.Grid;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1631. Path With Minimum Effort
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
 * where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0),
 * and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down,
 * left, or right, and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 * NOT TESTED !!!!!!!!!!!!!!!!!!!!!!!
 */
public class M_P1631_PathWithMinEffort {
    private static int[][] directions = {{0,1}, {1,0}, {0, -1}, {-1, 0}};
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        int[][] best = new int[n][m];
        Arrays.fill(best, Integer.MAX_VALUE);
        Stack<int[]> s = new Stack<>();
        s.push(new int[] {0,0});
        best[0][0] = heights[0][0];
        while (!s.isEmpty()) {
            int[] cur = s.pop();
            List<Pair<Integer, int[]>> next = new ArrayList<>();
            for (int[] dir : directions) {
                int newX = cur[0] + dir[0];
                int newY = cur[1] + dir[1];
                if (0 <= newX && newX < n && 0 <= newY && newY < m) {
                    if (best[newX][newY] > best[cur[0]][cur[1]] + heights[newX][newY]) {
                        best[newX][newY] = best[cur[0]][cur[1]] + heights[newX][newY];
                        next.add(ImmutablePair.of(best[newX][newY], new int[]{newX, newY}));
                    }
                }
            }
            next.sort(Collections.reverseOrder());
            s.addAll(next.stream().map(Pair::getRight).collect(Collectors.toList()));
        }
        return best[n-1][m-1];
    }
}
