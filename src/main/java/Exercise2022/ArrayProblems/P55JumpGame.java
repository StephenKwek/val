package Exercise2022.ArrayProblems;

import java.util.stream.IntStream;

/**
 * 55. Jump to Game
 * You are given an integer array nums. You are initially positioned at the array's first index, and
 * each element in the array represents your maximum jump length at that position.
 */
public class P55JumpGame {
    public boolean streamBased(int[] nums) {
        boolean[] canReached = new boolean[nums.length];
        canReached[0] = true;
        IntStream.range(0, nums.length)
                .sequential()
                .filter(i -> canReached[i])
                .map(i -> i + nums[i])
                .filter(next -> next < nums.length)
                .forEach(next -> canReached[next] = true);
        return canReached[nums.length - 1];
    }

    public boolean nonStream(int[] nums) {
        boolean[] canReached = new boolean[nums.length];
        canReached[0] = true;
        for (int i = 0; i < nums.length; i++) {
            if (canReached[i]) {
                    int nextCell = i + nums[i];
                    if (nextCell < nums.length && !canReached[nextCell]) {
                        //if (nextCell == nums.length) {
                        if (nextCell == nums.length - 1) {
                            return true;
                        } else if (nextCell < nums.length) {
                            canReached[nextCell] = true;
                        }
                    }
            }
        }
        return canReached[canReached.length - 1];
    }

    public static void main(String str[]) {
        P55JumpGame soln = new P55JumpGame();
        int[] nums = {2, 3, 1, 1, 4};
        assert soln.nonStream(nums);
        assert soln.streamBased(nums);

        int[] nums2 = {3, 2, 1, 0, 4};
        assert !soln.nonStream(nums2);
        assert !soln.streamBased(nums2);

        System.out.println("done");
    }
}
