package Exercise2022.ArrayProblems;

/**
 * 238. Product of Array Except Self
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of
 * nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 */
public class MediumP238ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] prefixProduct = new int[nums.length];
        prefixProduct[0] = 1;
        int[] suffixProduct = new int[nums.length];
        suffixProduct[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            prefixProduct[i] = prefixProduct[i-1] * nums[i-1];
        }
        for (int i = nums.length - 2; i >= 0;  i--) {
            suffixProduct[i] = suffixProduct[i+1] * nums[i+1];
        }
        int[] soln = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            soln[i] = prefixProduct[i] * suffixProduct[i];
        }
        return soln;
    }

    public static void main(String[] args) {
        MediumP238ProductOfArrayExceptSelf mediumP238ProductOfArrayExceptSelf = new MediumP238ProductOfArrayExceptSelf();
        TestUtil.equals(new int[]{24,12,8,6},
                             mediumP238ProductOfArrayExceptSelf.productExceptSelf(new int[]{1,2,3,4}));
        TestUtil.equals(new int[]{0,0,9,0,0},
                mediumP238ProductOfArrayExceptSelf.productExceptSelf(new int[]{-1,1,0,-3,3}));
    }
}
