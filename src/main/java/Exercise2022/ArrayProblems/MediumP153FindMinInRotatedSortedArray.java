package Exercise2022.ArrayProblems;

/**
 * 153. Find Minimum in Rotated Sorted Array
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class MediumP153FindMinInRotatedSortedArray {
    private int findMin(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        } else if (left + 1 == right) {
            return Math.min(nums[left], nums[right]);
        }
        int mid = (left + right)/2;
        if (nums[mid] > nums[right]) {
            return findMin(nums, mid, right);
        } else {
            return findMin(nums, left, mid);
        }
    }

    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    public static void main(String[] args) {
        MediumP153FindMinInRotatedSortedArray mediumP153FindMinInRotatedSortedArray = new MediumP153FindMinInRotatedSortedArray();
        assert 1 == mediumP153FindMinInRotatedSortedArray.findMin(new int[]{3,4,5,1,2});
        assert 0 == mediumP153FindMinInRotatedSortedArray.findMin(new int[]{4,5,6,7,0,1,2});
    }
}
