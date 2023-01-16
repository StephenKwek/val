package Exercise2022.ArrayProblems;

import java.util.HashMap;
import java.util.Map;

/**
 * 992. Subarrays with K Different Integers (Hard)
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 *
 * A good array is an array where the number of different integers in that array is exactly k.
 *
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 */
public class HardP992HardSubarraysWKDifferentIntegers {
    public int subarraysWithKDistinct(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            counts.clear();
            int j = i;
            for (; counts.size() < k && j < nums.length; j++) {
                if (counts.containsKey(nums[j])) {
                    counts.put(nums[j], counts.get(nums[j]) + 1);
                } else {
                    counts.put(nums[j], 1);
                }
            }

            if (counts.size() == k) {
                total++;
                while (j < nums.length && counts.containsKey(nums[j])) {
                    total++;
                    j++;
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        HardP992HardSubarraysWKDifferentIntegers p992HardSubarraysWKDifferentIntegers = new HardP992HardSubarraysWKDifferentIntegers();
        int[] nums = {1,2,1,2,3};
        assert 7 == p992HardSubarraysWKDifferentIntegers.subarraysWithKDistinct(nums, 2);

        int[] nums2 = {1,2,1,3,4};
        assert 3 == p992HardSubarraysWKDifferentIntegers.subarraysWithKDistinct(nums2, 3);
    }
}
