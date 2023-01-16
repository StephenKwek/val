package Exercise2022.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 253 Meeting Rooms II
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],…] (si < ei), find the minimum number of conference rooms required.
 *
 * For example, Given [[0, 30],[5, 10],[15, 20]], return 2.
 */
public class MediumP253MeetingRoomsII {
    private boolean leftOf(int[] interval1, int[] interval2) {
        return interval1[1] < interval2[0];
    }

    private boolean rightOf(int[] interval1, int[] interval2) {
        return interval2[1] < interval1[0];
    }

    private boolean overlap(int[] interval1, int[] interval2) {
        return !(leftOf(interval1, interval2) || rightOf(interval1, interval2));
    }

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<int[]> o = new ArrayList<>();
        int max = 1;
        o.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            o.removeIf(interval -> !overlap(interval, cur));
            o.add(intervals[i]);
            max = Math.max(max, o.size());
        }
        return max;
    }

    public static void main(String[] args) {
        MediumP253MeetingRoomsII p = new MediumP253MeetingRoomsII();
        assert 1 == p.minMeetingRooms(new int[][] {{0,30}, {35,40}});
        assert 2 == p.minMeetingRooms(new int[][] {{0,30}, {5,10}, {15,20}});
        assert 3 == p.minMeetingRooms(new int[][] {{0,30}, {7, 9}, {5,10}, {15,20}});

    }

}
