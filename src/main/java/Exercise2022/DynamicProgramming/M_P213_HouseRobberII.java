package Exercise2022.DynamicProgramming;

import java.util.Arrays;
import java.util.stream.IntStream;

public class M_P213_HouseRobberII {
    private static final M_P198_HouseRobber P = new M_P198_HouseRobber();
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        return Math.max(
                P.rob(Arrays.copyOf(nums, nums.length - 1)),
                P.rob(Arrays.copyOfRange(nums, 1, nums.length))
        );
    }

    public static void main(String[] args) {
        M_P213_HouseRobberII p = new M_P213_HouseRobberII();
        assert 3 == p.rob(new int[] {2,3,2});
        assert 4 == p.rob(new int[] {1,2,3,1});
        assert 3 == p.rob(new int[] {1,2,3});
    }
}
