package Exercise2022.ArrayProblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 1. Two Sums
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class EasyP1TwoSums {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            index.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (index.containsKey(target - nums[i]) && i != index.get(target - nums[i])) {
                return new int[] {i, index.get(target - nums[i])};
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        EasyP1TwoSums easyP1TwoSums = new EasyP1TwoSums();

        assert Arrays.equals(new int[]{0,1}, easyP1TwoSums.twoSum(new int[] {2, 7, 11, 15}, 9));
        assert Arrays.equals(new int[]{1,2}, easyP1TwoSums.twoSum(new int[] {3, 2, 4}, 6));
        assert Arrays.equals(new int[]{0,1}, easyP1TwoSums.twoSum(new int[] {3, 3}, 6));
    }
}
