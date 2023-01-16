package Exercise2022.Interval;

import Exercise2022.ArrayProblems.TestUtil;

import java.util.Arrays;
import java.util.Comparator;

public class P56MergeIntervals {
    private boolean leftOf(int[] interval1, int[] interval2) {
        return interval1[1] < interval2[0];
    }

    private boolean rightOf(int[] interval1, int[] interval2) {
        return interval2[1] < interval1[0];
    }

    private boolean overlap(int[] interval1, int[] interval2) {
        return !(leftOf(interval1, interval2) || rightOf(interval1, interval2));
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        

        int k = 0;   // intervals[0...k] will contain the solution obtained so far.
        for (int i = 0; i < intervals.length; )  {
            int j = i;
            int left = intervals[i][0];
            int right = intervals[i][1];
            for (; j < intervals.length; j++) {
                if (overlap(intervals[i], intervals[j])) {
                    left = Math.min(left, intervals[j][0]);
                    right = Math.max(right, intervals[j][1]);
                } else {
                    break;
                }
            }
            i = j;
            intervals[k] = new int[]{left, right};
            k++;
        }
        return Arrays.copyOf(intervals, k);   // copy only the first k intervals
    }

    public static void main(String[] args) {
        P56MergeIntervals p = new P56MergeIntervals();
        TestUtil.equals(new int[][] {{1,6},{8,10},{15,18}},
                             p.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}}));
        TestUtil.equals(new int[][] {{1,5}},
                p.merge(new int[][]{{1,4},{4,5}}));
    }
}
