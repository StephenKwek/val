package Exercise2022.SearchSort;

import Exercise2022.ArrayProblems.TestUtil;
import com.google.common.primitives.Ints;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 480. Sliding Window Median
 *
 * The median is the middle value in an ordered integer list. If the size of the list is even,
 * there is no middle value. So the median is the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k. There is a sliding window of size k which
 * is moving from the very left of the array to the very right. You can only see the k numbers in the
 * window. Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array. Answers within 10-5 of the actual
 * value will be accepted.
 */
public class H_P480_SlidingWindowMedian {
    private int find(List<Integer> sortedWindow, int element, int left, int right) {
        if (left >= right) {
            return left;
        }
        int mid = (left + right)/2;
        if (element == sortedWindow.get(mid)) {
            return mid;
        } else if (element > sortedWindow.get(mid)) {
            return find(sortedWindow, element, mid + 1, right);
        } else {
            return find(sortedWindow, element, left, mid);
        }
    }
    public double[] medianSlidingWindow(int[] nums, int k) {
        int[] temp = Arrays.copyOf(nums, k);
        Arrays.sort(temp);
        List<Integer> sortedWindow = new ArrayList<>(temp.length);
        for (int e : temp) {
            sortedWindow.add(e);
        }

        double[] soln = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            soln[i] = k % 2 == 1
                    ? sortedWindow.get(k/2)
                    : (sortedWindow.get(k/2 - 1) + sortedWindow.get(k/2)) / 2.0;
            if (i < nums.length - k) {
                int toRemove = find(sortedWindow, nums[i], 0, k);
                sortedWindow.remove(toRemove);
                int toAdd = find(sortedWindow, nums[i + k], 0, k - 1);
                sortedWindow.add(toAdd, nums[i + k]);
            }
        }
        return soln;
    }

    public static void main(String[] args) {
        H_P480_SlidingWindowMedian p = new H_P480_SlidingWindowMedian();
        double[] expected = new double[] {1.00000,-1.00000,-1.0000, 3.00000,5.00000,6.00000};
        int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
        double[] x = p.medianSlidingWindow(nums, 3);
        for (double d : expected) {
            System.out.println(d);
        }
        TestUtil.equals(expected, p.medianSlidingWindow(nums, 3));

        expected = new double[] {2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000};
        nums = new int[] {1,2,3,4,2,3,1,4,2};
        //TestUtil.equals(expected, p.medianSlidingWindow(nums, 3));
    }
}