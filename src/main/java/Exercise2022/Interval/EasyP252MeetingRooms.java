package Exercise2022.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * 252. Meeting Rooms
 * Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei),
 * determine if a person could attend all meetings.
 */
public class EasyP252MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        return IntStream.range(0, intervals.length - 1)
                .boxed()
                .allMatch(i -> intervals[i][1] <= intervals[i+1][0]);
    }

    public static void main(String[] args) {
        EasyP252MeetingRooms p = new EasyP252MeetingRooms();
        assert !p.canAttendMeetings(new int[][]{{0,30}, {5,10}, {15,20}});
        assert p.canAttendMeetings(new int[][]{{7,10},{2,4}});
    }

}
