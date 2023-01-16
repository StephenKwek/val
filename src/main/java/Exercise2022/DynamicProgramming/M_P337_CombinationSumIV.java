package Exercise2022.DynamicProgramming;

public class M_P337_CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        int[] ways = new int[target + 1];
        ways[0] = 1;
        for (int n : nums) {
            for (int i = 0; i < ways.length; i++) {
                if (ways[i] > 0 && i + n <= target) {
                    ways[i + n] += ways[i];
                }
            }
        }
        return ways[target];
    }

    public static void main(String[] args) {
        M_P337_CombinationSumIV p = new M_P337_CombinationSumIV();
        assert 4 == p.combinationSum4(new int[] {1, 2, 3}, 4);
        assert 2 == p.combinationSum4(new int[] {2,3,6,7}, 7);
        assert 0 == p.combinationSum4(new int[] {9}, 3);
        assert 0 == p.combinationSum4(new int[] {9}, 3);
    }
}
