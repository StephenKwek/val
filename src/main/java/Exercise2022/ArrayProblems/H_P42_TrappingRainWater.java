package Exercise2022.ArrayProblems;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Stack;

public class H_P42_TrappingRainWater {
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        Stack<Pair<Integer, Integer>> s = new Stack<>();
        int total = 0;
        int i = 1;
        s.add(ImmutablePair.of(0, height[0]));
        while (i < height.length) {
            while (i < height.length && height[i] <= s.peek().getRight()) {
                if (height[i] != s.peek().getRight()) {
                    s.push(ImmutablePair.of(i, height[i]));
                }
                i++;
            }
            if (i < height.length) {
                int wall = height[i];
                int floor = 0;
                while (!s.isEmpty() && s.peek().getRight() <= wall) {
                    Pair<Integer, Integer> pre = s.pop();
                    total += (i - pre.getLeft() - 1) * (Math.min(wall, pre.getRight() - floor));
                    floor = Math.min(wall, pre.getRight());
                }
                if (!s.isEmpty()) {
                    total += (i - s.peek().getLeft() - 1) * (wall - floor);
                }
                s.push(ImmutablePair.of(i, wall));
                i++;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        H_P42_TrappingRainWater p = new H_P42_TrappingRainWater();
        assert 6 == p.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1});
        assert 9 == p.trap(new int[] {4,2,0,3,2,5});
    }
}
