package Exercise2022.Interval;

import java.util.LinkedList;
import java.util.List;

public class H_P715_RangeModule {
    public static class RangeModule {
        private final List<int[]> ranges = new LinkedList<>();

        private int firstLeftEndIsRightOfTarget(int i, int target) {
            for (; i < ranges.size() && ranges.get(i)[1] <= target; i++);
            return i;
        }

        private int firstRightEndIsRightOfTarget(int i, int target) {
            for (; i < ranges.size() && ranges.get(i)[0] <= target; i++);
            return i;
        }

        public RangeModule() {
        }

        public void addRange(int left, int right) {
            int lower = firstLeftEndIsRightOfTarget(0, left);
            int upper = firstRightEndIsRightOfTarget(lower, right);
            if (lower == upper) {
                ranges.add(lower, new int[] {left, right});
            } else {
                int[] newInterval = new int[] {
                        Math.min(ranges.get(lower)[0], left),
                        Math.max(ranges.get(upper - 1)[1], right)
                };
                for (int i = lower; i < upper; i++) {
                    ranges.remove(i);
                }
                ranges.add(newInterval);
            }
        }

        public boolean queryRange(int left, int right) {
            int lower = firstLeftEndIsRightOfTarget(0, left);
            return (lower != ranges.size() && ranges.get(lower)[0] <= left && right <= ranges.get(lower)[1]);
        }

        public void removeRange(int left, int right) {
            int lower = firstLeftEndIsRightOfTarget(0, left);
            int upper = firstRightEndIsRightOfTarget(lower, right);
            if (lower != upper) {
                int[] interval1 = ranges.get(lower)[0] < left
                        ? new int[] {ranges.get(lower)[0], left}
                        : null;
                int[] interval2 = 0 <= upper - 1  && right < ranges.get(upper - 1)[1]
                        ? new int[] {right, ranges.get(upper - 1)[1]}
                        : null;

                for (int i = lower; i < upper; i++) {
                    ranges.remove(i);
                }
                if (interval2 != null) {
                    ranges.add(lower, interval2);
                }
                if (interval1 != null) {
                    ranges.add(lower, interval1);
                }
            }
        }
    }

    public static void main(String[] args)  {
        RangeModule p = new RangeModule();
        p.addRange(10, 20);
        p.removeRange(14, 16);
        assert p.queryRange(10, 14);
        assert !p.queryRange(13, 15);
        assert p.queryRange(16, 17);
    }
}
