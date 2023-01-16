package Exercise2022.DynamicProgramming;

public class M_P55_JumpGame {
    public boolean canJump(int[] nums) {
        boolean[] can = new boolean[nums.length];
        can[0] = true;

        for (int i = 0; i < nums.length; i++) {
            if (can[i] && i + nums[i] < nums.length) {
                if (i + nums[i] == nums.length - 1) {
                    return true;
                }
                can[i + nums[i]] = true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        M_P55_JumpGame p = new M_P55_JumpGame();
        assert p.canJump(new int[] {2,3,1,1,4});
        assert !p.canJump(new int[] {2,3,1,0,4});
    }
}
