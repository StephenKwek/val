package Exercise2022.Techniques.Greedy;

import java.util.Arrays;

public class HardP1326MinimumNumberOfTaps {
    private Integer[] getBestCoverage(int n, int[] ranges) {
        Integer[] best = new Integer[n+1];
        Arrays.fill(best, null);
        for (int i = 0; i <= n; i++) {
            int leftBoundary = i - ranges[i];
            for (int j = Math.max(0, leftBoundary); j <= i + ranges[i]; j++) {
                best[j] = best[j] == null
                            ? leftBoundary
                            : Math.min(best[j], leftBoundary);
            }
        }
        return best;
    }

    public int minTaps(int n, int[] ranges) {
        Integer[] bestCoverage = getBestCoverage(n, ranges);
        Integer tap = n;
        int soln = 0;
        while (tap >= 0) {
            tap = bestCoverage[n];
            if (tap == null) {
                return -1;
            }
            soln++;
        }
        return soln;
    }

    public static void main(String[] args) {
        HardP1326MinimumNumberOfTaps hardP1326MinimumNumberOfTaps = new HardP1326MinimumNumberOfTaps();
        assert 1 == hardP1326MinimumNumberOfTaps.minTaps(5, new int[] {3,4,1,1,0,0});
        assert 2 == hardP1326MinimumNumberOfTaps.minTaps(7, new int[] {3,4,1,1,0,0,3,0});
        assert -1 == hardP1326MinimumNumberOfTaps.minTaps(3, new int[] {0,0,0,0});
    }
}
