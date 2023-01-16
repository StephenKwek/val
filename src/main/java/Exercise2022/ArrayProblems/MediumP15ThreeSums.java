package Exercise2022.ArrayProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k,
 * and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 */
public class MediumP15ThreeSums {
    private List<List<Integer>> twoSums(int[] nums, int target, int left, int right) {
        List<List<Integer>> soln = new ArrayList<>();
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                soln.add(new ArrayList<>(List.of(nums[left], nums[right])));
                // since no duplicate tuple in the solution, we can move both pointers
                do {
                    right--;
                } while(nums[left] + nums[right] == target);
                left++;
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return soln;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> solns = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i-1] != nums[i]) {
                List<List<Integer>> suffixSolns = twoSums(nums, -1 * nums[i], i + 1, nums.length - 1);
                for (List<Integer> soln : suffixSolns) {
                    soln.add(0, nums[i]);
                    solns.add(soln);
                }
            }
        }
        return solns;
    }

    public static void main(String[] args) {
        MediumP15ThreeSums p = new MediumP15ThreeSums();
        assert p.threeSum(new int[]{0,1,1}).isEmpty();

        System.out.println("--------0, 0, 0");
        System.out.println(p.threeSum(new int[] {0,9,0}));
        System.out.println("---------1,0,1,2,-1,-4");
        System.out.println(p.threeSum(new int[] {-1,0,1,2,-1,-4}));
    }

}
