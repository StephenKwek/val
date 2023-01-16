package Exercise2022.ArrayProblems;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Stack;

/**
 * 11. Container With Most Water
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 * https://leetcode.com/problems/container-with-most-water/
 */
public class QQQQMediumP11ContainerWithMostWater {

    /*
    This version assumes that the example used in question (see link) is 40. Ie. in the picture shown, it is
    considered two rectangles, not one
     */
    public int maxArea2(int[] height) {
        Stack<Pair<Integer, Integer>> s = new Stack<>();
        int best = 0;
        s.add(ImmutablePair.of(0, height[0]));

        for (int i = 1; i < height.length; i++) {
            if (height[i] < s.peek().getRight()) {
                s.push(ImmutablePair.of(i, height[i]));
            } else {
                while (!s.isEmpty() && height[i] > s.peek().getRight()) {
                    Pair<Integer, Integer> pre = s.pop();
                    best = Math.max(best, (i - pre.getLeft()) * pre.getRight());
                }
                s.push(ImmutablePair.of(i, height[i]));
            }
        }
        if (!s.isEmpty()) {
            for (int i = 1; i < s.size(); i++) {
                Pair<Integer, Integer> a = s.get(i - 1);
                Pair<Integer, Integer> b = s.get(i);
                best = Math.max(best, (b.getLeft() - a.getLeft()) * b.getRight());
            }
        }
        return best;
    }

    public static void main(String[] args) {
        QQQQMediumP11ContainerWithMostWater p = new QQQQMediumP11ContainerWithMostWater();
        System.out.println(p.maxArea2(new int[]{1,8,6,2,5,4,8,3,7}));
        assert 49 == p.maxArea2(new int[]{1,8,6,2,5,4,8,3,7});
    }
}
