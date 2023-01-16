package Exercise2022.ArrayProblems;

import java.util.stream.IntStream;

/**
 * 41. First Missing Positive
 * You are given a binary array nums and an integer k.
 *
 * A k-bit flip is choosing a subarray of length k from nums and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.
 *
 * Return the minimum number of k-bit flips required so that there is no 0 in the array. If it is not possible, return -1.
 *
 * A subarray is a contiguous part of an array.
 *
 * https://leetcode.com/problems/first-missing-positive/
 */
public class P41FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while ((nums[i] != i+1) && (0 < nums[i]) && (nums[i] <= nums.length)) {
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        return IntStream.range(0, nums.length)
                .filter(i -> nums[i] != i + 1)
                .findFirst()
                .getAsInt() + 1;
    }

    public static void main(String[] args) {
        P41FirstMissingPositive p41FirstMissingPositive = new P41FirstMissingPositive();
        int[] nums = {3,4,-1,1};
        assert 2 == p41FirstMissingPositive.firstMissingPositive(nums);

        int[] nums2 = {7,8,9,11,12};
        assert 1 == p41FirstMissingPositive.firstMissingPositive(nums2);
    }
}
