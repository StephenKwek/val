package Exercise2022.Geometry;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Stack;

/**
 * 84. Largest Rectangle in Histogram
 *
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 *
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class H_P84_LargestRectangleInHistorgram {
    public int largestRectangleArea(int[] heights) {
        Stack<Pair<Integer,Integer>> stack = new Stack<>();
        int curBest = 0;
        for (int i = 0; i < heights.length; i++) {
             while (!stack.isEmpty() && stack.peek().getRight() < heights[i]) {
                 Pair<Integer, Integer> top = stack.pop();
                 curBest = Math.max(curBest, top.getRight() * (i - top.getLeft() + 1));
             }
             stack.push(ImmutablePair.of(i, heights[i]));
        }

        if (!stack.isEmpty()) {
            Pair<Integer, Integer> top = stack.peek();
            while (!stack.isEmpty()) {
                Pair<Integer, Integer> next = stack.pop();
                curBest = Math.max(curBest,
                            next.getRight() * (top.getRight() - next.getRight())
                        );
            }
        }
        return curBest;
    }

    public static void main(String[] args) {
        H_P84_LargestRectangleInHistorgram p = new H_P84_LargestRectangleInHistorgram();
        int[] heights = {5,6,2};
        assert 10 == p.largestRectangleArea(heights);
    }
}
