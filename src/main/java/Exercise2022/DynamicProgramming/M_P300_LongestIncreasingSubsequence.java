package Exercise2022.DynamicProgramming;

import java.util.Arrays;

public class M_P300_LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] opt = new int[nums.length];
        Arrays.fill(opt, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++ ) {
                if (nums[i] > nums[j]) {
                    opt[i] = Math.max(opt[i], opt[j] + 1);
                }
            }
        }
        return Arrays.stream(opt).max().getAsInt();
    }

    public static void main(String[] args) {
        M_P300_LongestIncreasingSubsequence p = new M_P300_LongestIncreasingSubsequence();
        System.out.println(p.lengthOfLIS(new int[] {10,9,2,5,3,7,101,18}));
        assert 4 == p.lengthOfLIS(new int[] {10,9,2,5,3,7,101,18});
        assert 4 == p.lengthOfLIS(new int[] {0,1,0,3,2,3});
        assert 1 == p.lengthOfLIS(new int[] {7,7,7,7,7,7,7,7});
    }
}
