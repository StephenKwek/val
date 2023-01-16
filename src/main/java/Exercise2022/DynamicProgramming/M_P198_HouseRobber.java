package Exercise2022.DynamicProgramming;

import java.util.stream.IntStream;

public class M_P198_HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int profit[] = new int[nums.length];
        profit[0] = nums[0];
        profit[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            profit[i] = (i != 2)
                         ? Math.max(profit[i-2], profit[i-3]) + nums[i]
                         : profit[i-2] + nums[i];
        }
        return IntStream.range(Math.max(0,nums.length - 4), nums.length)
                .map(i -> profit[i])
                .max()
                .getAsInt();
    }

    public static void main(String[] args) {
        M_P198_HouseRobber p = new M_P198_HouseRobber();
        assert 4 == p.rob(new int[] {1,2,3,1});
        assert 12 == p.rob(new int[] {2,7,9,3,1});
    }
}
