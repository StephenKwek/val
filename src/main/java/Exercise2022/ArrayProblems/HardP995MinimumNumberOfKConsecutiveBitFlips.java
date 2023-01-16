package Exercise2022.ArrayProblems;

import java.util.Arrays;

/**
 * 995. Minimum Number of K Consecutive Bit Flips
 * You are given a binary array nums and an integer k.
 *
 * A k-bit flip is choosing a subarray of length k from nums and simultaneously changing every 0 in the subarray to 1,
 * and every 1 in the subarray to 0.
 *
 * Return the minimum number of k-bit flips required so that there is no 0 in the array. If it is not possible, return -1.
 *
 * A subarray is a contiguous part of an array.
 * https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/
 */
public class HardP995MinimumNumberOfKConsecutiveBitFlips {
    public int minKBitFlips(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i <= nums.length - k; i++) {
            if (nums[i] == 0) {
                count++;
                for (int j = i; j < i + k; j++) {
                    nums[j] = (nums[j] == 0) ? 1 : 0;
                }
                System.out.println(Arrays.toString(nums));
            }
        }
        return (Arrays.stream(nums).reduce(0, Integer::sum) == nums.length) ? count : -1;
    }

    public static void main(String[] args) {
        HardP995MinimumNumberOfKConsecutiveBitFlips p995MinimumNumberOfKConsecutiveBitFlips = new HardP995MinimumNumberOfKConsecutiveBitFlips();
        int[] nums = {0,1,0};
        assert 2 == p995MinimumNumberOfKConsecutiveBitFlips.minKBitFlips(nums, 1);

        int[] nums2 = {1,1,0};
        assert -1 == p995MinimumNumberOfKConsecutiveBitFlips.minKBitFlips(nums2, 2);

        int[] nums3 = {0,0,0,1,0,1,1,0};
        assert 3 == p995MinimumNumberOfKConsecutiveBitFlips.minKBitFlips(nums3, 3);
    }
}
