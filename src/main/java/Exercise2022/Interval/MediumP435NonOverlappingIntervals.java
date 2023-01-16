package Exercise2022.Interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. Non-overlapping Intervals
 * Given an array of intervals where intervals[i] = [starti, endi], return the minimum number of intervals
 * you need to remove to make the rest of the intervals non-overlapping.
 */
public class MediumP435NonOverlappingIntervals {
    private boolean leftOf(int[] interval1, int[] interval2) {
        return interval1[1] <= interval2[0];
    }

    private boolean rightOf(int[] interval1, int[] interval2) {
        return interval2[1] <= interval1[0];
    }

    private boolean overlap(int[] interval1, int[] interval2) {
        return !(leftOf(interval1, interval2) || rightOf(interval1, interval2));
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int numRemoved = 0;
        for (int i = 0; i < intervals.length;) {
            int j = i;
            while (j + 1 < intervals.length && overlap(intervals[i], intervals[j + 1])) {
                j++;
            }
            numRemoved += j - i;
            i = j + 1;
        }
        return numRemoved;
    }

    public static void main(String[] args) {
        MediumP435NonOverlappingIntervals p = new MediumP435NonOverlappingIntervals();
        assert 1 == p.eraseOverlapIntervals(new int[][] {{1,2},{2,3},{3,4},{1,3}});
        assert 2 == p.eraseOverlapIntervals(new int[][] {{1,2},{1,2},{1,2}});
        assert 0 == p.eraseOverlapIntervals(new int[][] {{1,2},{2,3}});
    }

}
