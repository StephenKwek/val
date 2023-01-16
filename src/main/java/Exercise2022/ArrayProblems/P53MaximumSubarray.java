package Exercise2022.ArrayProblems;

public class P53MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int curMax = 0;
        int curSum = 0;
        for (int item : nums) {
            if (curSum + item > 0) {
                curSum += item;
                curMax = Math.max(curSum, curMax);
            } else {
                curSum = 0;
            }
        }
        return curMax;
    }

    public static void main(String[] args) {
        P53MaximumSubarray p53MaximumSubarray = new P53MaximumSubarray();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        assert 6 == p53MaximumSubarray.maxSubArray(nums);

        int[] nums2 = {1};
        assert 1 == p53MaximumSubarray.maxSubArray(nums2);

        int[] nums3 = {5,4,-1,7,8};
        assert 23 == p53MaximumSubarray.maxSubArray(nums3);
    }
}
