package Exercise2022.Interval;

import Exercise2022.ArrayProblems.TestUtil;

/**
 * 57. Insert Interval
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent
 * the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by start and intervals
 * still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 */
public class MediumP57InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = 0;
        int right = intervals.length;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1] < newInterval[0]) {
                i++;
                left = i;
            } else if (newInterval[1] < intervals[i][0]) {
                right = i;
                break;
            }
        }

        int newLength = left + 1 + (intervals.length - right);
        int[][] result = new int[newLength][2];
        if (left != -1) {
            System.arraycopy(intervals, 0, result, 0, left);
        }
        if (right != left) {
            int newLeft = Math.min(newInterval[0], intervals[left][0]);
            int newRight = Math.max(newInterval[1], intervals[right - 1][1]);
            int[] collapsedInterval = {newLeft, newRight};
            result[left] = collapsedInterval;
        } else {
            result[left] = newInterval;
        }
        if (right < intervals.length) {
            System.arraycopy(intervals, right, result, left + 1, intervals.length - right);
        }
        return result;
    }

    public static void main(String[] args) {
        MediumP57InsertInterval p = new MediumP57InsertInterval();
        int[][] intervals = {{1,3}, {6,9}};
        int[] newInterval = {2,5};
        TestUtil.equals(new int[][] {{1,5}, {6,9}}, p.insert(intervals, newInterval));
        intervals = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        newInterval = new int[]{4,8};
        TestUtil.equals(new int[][] {{1,2},{3,10},{12,16}}, p.insert(intervals, newInterval));
    }
}
