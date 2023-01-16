package Exercise2022.ArrayProblems;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * 45. Jump to Game 2
 * You are given a 0-indexed array of integers nums of length n.
 * You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated
 * such that you can reach nums[n - 1].
 */
public class P45JumpGame2 {
    public int streamBased(int[] nums) {
        int[] canReached = new int[nums.length];
        Arrays.fill(canReached, Integer.MAX_VALUE);
        canReached[0] = 1;
        IntStream.range(0, nums.length)
                .filter(i -> canReached[i] != Integer.MAX_VALUE)
                .forEach(i -> IntStream.range(0, nums.length)
                                .filter(jump -> i + jump < nums.length)
                                .forEach(jump -> canReached[i + jump] = Math.min(canReached[i] + 1, canReached[i + jump]))
                );
        assert canReached[nums.length - 1] != Integer.MAX_VALUE;
        return canReached[nums.length - 1];
    }

    public int nonStream(int[] nums) {
        int[] canReached = new int[nums.length];
        Arrays.fill(canReached, Integer.MAX_VALUE);
        canReached[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            if (canReached[i] != Integer.MAX_VALUE) {
                for (int j = 0; j < nums.length; j++) {
                    int nextCell = i + nums[j];
                    if (nextCell < nums.length) {
                        canReached[nextCell] = Math.min(canReached[i] + 1, canReached[nextCell]);
                    }
                }
            }
        }
        assert canReached[nums.length - 1] != Integer.MAX_VALUE;
        return canReached[nums.length - 1] ;
    }

    public static void main(String str[]) {
        P45JumpGame2 soln = new P45JumpGame2();
        int[] nums = {2, 3, 1, 1, 4};
        assert soln.nonStream(nums) == 2;
        assert soln.streamBased(nums) == 2;

        int[] nums2 = {3, 2, 0, 1, 4};
        assert soln.nonStream(nums2) == 2;
        assert soln.streamBased(nums2) == 2;

        System.out.println("done");
    }
}
