package Exercise2022.ArrayProblems;

/**
 * 33. Search in Rotated Sorted Array
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class MediumP33SearchInRotatedSortedArray {
    private int search(int[] nums, int target, int left, int right) {
        if (left == right) {
            return nums[left] == target ? left : -1;
        } else if (left + 1 == right) {
            return nums[left] == target
                    ? left
                    : nums[right] == target
                        ? right
                        : -1;
        }

        int mid = (left + right)/2;
        if (target == nums[mid]) {
            return mid;
        } else if (target > nums[mid]) {
            if (target <= nums[left]) {
                return search(nums, target, left, mid);
            } else if (nums[mid] < nums[right]) {
                return search(nums, target, left, mid);
            } else  {
                return search(nums, target, mid, right);
            }
        } else {
            if (target > nums[left]) {
                return search(nums, target, mid, right);
            } else if (nums[mid] > nums[right]) {
                return search(nums, target, mid, right);
            } else  {
                return search(nums, target, left, mid);
            }
        }
    }

    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    public static void main(String[] args) {
        MediumP33SearchInRotatedSortedArray p = new MediumP33SearchInRotatedSortedArray();
        assert 4 == p.search(new int[]{4,5,6,7,0,1,2}, 0);
        assert -1 == p.search(new int[]{4,5,6,7,0,1,2}, 3);

    }
}
