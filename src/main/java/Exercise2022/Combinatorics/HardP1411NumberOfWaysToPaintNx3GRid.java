package Exercise2022.Combinatorics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 1411. Number of Ways to Paint N × 3 Grid
 * You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors:
 * Red, Yellow, or Green while making sure that no two adjacent cells have the same color
 * (i.e., no two cells that share vertical or horizontal sides have the same color).
 *
 * Given n the number of rows of the grid, return the number of ways you can paint this grid. As the answer may
 * grow large, the answer must be computed modulo 10^9 + 7.
 * https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/
 */
public class HardP1411NumberOfWaysToPaintNx3GRid {
    private final String[] oneRowSoln = {
            "ryr", "ryg", "rgr", "rgy",
            "yry", "yrg", "ygr", "ygy",
            "gry", "grg", "gyr", "gyg"
    };
    private final Map<String, Long> waysOneRow =
            Arrays.stream(oneRowSoln)
                    .collect(Collectors.toMap(s -> s, s -> 1L));
    private static final int N = 1000000007;

    private boolean noConflict(String top, String bottom) {
        for (int i = 0; i < top.length(); i++) {
            if (top.charAt(i) == bottom.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public Map<String, Long> possibleWays(int n) {
        if (n == 1) {
            return waysOneRow;
        }
        Map<String, Long> subSoln = possibleWays(n - 1);
        return Arrays.stream(oneRowSoln)
                .collect(Collectors.toMap(
                            row -> row,
                            row -> subSoln.entrySet().stream()
                                    .filter(entry -> noConflict(entry.getKey(), row))
                                    .map(Map.Entry::getValue)
                                    .reduce(0L, Long::sum) % N
                ));
    }

    public long numOfWays(int n) {
        return possibleWays(n).values().stream().reduce(0L, Long::sum) % N;
    }

    public static void main(String[] args) {
        HardP1411NumberOfWaysToPaintNx3GRid hardP1411NumberOfWaysToPaintNx3GRid = new HardP1411NumberOfWaysToPaintNx3GRid();
        assert 12 == hardP1411NumberOfWaysToPaintNx3GRid.numOfWays(1);
        assert 30228214 == hardP1411NumberOfWaysToPaintNx3GRid.numOfWays(5000);
    }
}
