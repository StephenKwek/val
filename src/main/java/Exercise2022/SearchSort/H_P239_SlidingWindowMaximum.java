package Exercise2022.SearchSort;

import Exercise2022.ArrayProblems.TestUtil;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 *  239. Sliding Window Maximum (Hard)
 * You are given an array of integers nums, there is a sliding window of size k which is
 * moving from the very left of the array to the very right. You can only see the k numbers
 * in the window. Each time the sliding window moves right by one position.
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * Return the max sliding window.
 */
public class H_P239_SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Pair<Integer, Integer>> q = new PriorityQueue<>(Collections.reverseOrder());
        int[] soln = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            q.add(ImmutablePair.of(nums[i], i));
            if (q.size() >= k) {
                while (q.peek().getRight() <= i - k ) {
                    q.poll();
                }
                System.out.println(i + "," + k);
                soln[i - k + 1] = q.peek().getLeft();
            }
        }
        // could add code to truncate q, popped these items that are outdated to another q, if the q size is above some threshold
        return soln;
    }

    public static void main(String[] args) {
        H_P239_SlidingWindowMaximum hardP239SlidingWindowMaximum = new H_P239_SlidingWindowMaximum();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] soln = {3,3,5,5,6,7};
        TestUtil.equals(hardP239SlidingWindowMaximum.maxSlidingWindow(nums, 3), soln);

        int[] nums2 = {1};
        int[] soln2 = {1};
        TestUtil.equals(hardP239SlidingWindowMaximum.maxSlidingWindow(nums2, 1), soln2);
    }
}
