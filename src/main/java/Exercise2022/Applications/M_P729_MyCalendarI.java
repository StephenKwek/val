package Exercise2022.Applications;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class M_P729_MyCalendarI {
    static class MyCalendar {
        final TreeMap<Integer, Integer> events = new TreeMap<>();
        public MyCalendar() {
        }

        public boolean book(int start, int end) {
            Integer left = events.floorKey(start);
            if (left != null && events.get(left) > start) {
                return false;
            }
            Integer right = events.floorKey(end - 1);
            if (right != null && (start < events.get(right)  || start < right)) {
                return false;
            }
            events.put(start, end);
            return true;
        }
    }

    public static void main(String[] args) {
        MyCalendar p = new MyCalendar();
        /*
        int[][]  range = {{37,50},{33,50},{4,17},{35,48},{8,25}};
        boolean[] expected = {true,false,true,false,false};
        int[][] range = {{97,100},{33,51},{89,100},{83,100},{75,92},{76,95},{19,30},{53,63},{8,23},{18,37},{87,100},{83,100}, {54,67},
            {35,48},{58,75},{70,89},{13,32},{44,63},{51,62},{2,15}};
        boolean[] expected = {true,true,false,false,true,false,true,true,false,false,false,false,false,false,false,
                false,false,false,false,true};
        */
        int[][] range = {{20,29},{13,22},{44,50},{1,7},{2,10},{14,20},{19,25},{36,42},{45,50},{47,50},{39,45},{44,50},
                {16,25}, {45,50},{45,50},{12,20},{21,29},{11,20},{12,17},{34,40},{10,18},{38,44},{23,32},{38,44},
                {15,20},{27,33},{34,42},{44,50},{35,40},{24,31}};
        boolean[] expected = {true,false,true,true,false,true,false,true,false,false,false,false,false,false,false,false,false,false,
                false,false,false,false,false,false,false,false,false,false,false,false};
        for (int i = 0; i < expected.length; i++) {
            System.out.println(i + "," + expected[i] + range[i][0] + "," + range[i][1]);
            if (i == 1) {
                assert p.book(range[i][0], range[i][1]) == expected[i];
            } else {
                assert p.book(range[i][0], range[i][1]) == expected[i];
            }
        }


        p = new MyCalendar();
        assert p.book(37,50);
        assert !p.book(33,50);
        assert p.book(4,17);
        assert !p.book(35,48);
        assert !p.book(8,25);

        p = new MyCalendar();
        assert p.book(47,50);
        assert p.book(33,41);
        assert !p.book(39,45);
        assert !p.book(33,42);
        assert p.book(25,32);
        assert !p.book(26,35);
        assert p.book(19,25);
        assert p.book(3,8);
        assert p.book(8,13);
        assert !p.book(18,27);
    }
}
