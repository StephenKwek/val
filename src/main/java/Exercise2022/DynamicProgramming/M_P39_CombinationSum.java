package Exercise2022.DynamicProgramming;

public class M_P39_CombinationSum {
    public int combinationSum(int[] nums, int target) {
        int[] ways = new int[target + 1];
        ways[0] = 1;
        for (int i = 0; i < ways.length; i++) {
            for (int n : nums) {
                if (ways[i] > 0 && i + n <= target) {
                    ways[i + n] += ways[i];
                }
            }
        }
        return ways[target];
    }

    public static void main(String[] args) {
        M_P39_CombinationSum p = new M_P39_CombinationSum();
        assert 7 == p.combinationSum(new int[] {1, 2, 3}, 4);
        assert 0 == p.combinationSum(new int[] {9}, 3);
        assert 0 == p.combinationSum(new int[] {9}, 3);
    }
}
