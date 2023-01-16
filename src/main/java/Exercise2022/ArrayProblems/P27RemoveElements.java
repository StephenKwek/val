package Exercise2022.ArrayProblems;

/*
 * P27 Remove Element
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */

public class P27RemoveElements {
    public static P27RemoveElements p27RemoveElements = new P27RemoveElements();
    public int removeElement(int[] nums, int target) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) {
                if (i != j) {
                    nums[j] = nums[i];
                }
                j++;
            }
        }
        for (int i = j + 1; i < nums.length; i++) {
            nums[i] = -1;
        }
        return j;
    }

    private static void judge(int[] nums, int[] expectedNums, int val) {
        int k = p27RemoveElements.removeElement(nums, val); // Calls your implementation

        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }

    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int[] expected = {2,2};
        judge(nums, expected, 3);

        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int[] expected2 = {0, 1, 3, 0, 4};
        judge(nums2, expected2, 2);
    }

}
