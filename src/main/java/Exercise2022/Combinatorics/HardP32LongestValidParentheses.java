package Exercise2022.Combinatorics;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * 32. Longest Valid Parentheses
 * https://leetcode.com/problems/longest-valid-parentheses/submissions/
 * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses
 * substring
 */
public class HardP32LongestValidParentheses {
    public int longestValidParentheses(String s) {
        Stack<Integer> leftmost = new Stack<>();
        int best = 0;
        int unmatched = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                unmatched++;
                if (unmatched > leftmost.size()) {
                    leftmost.push(i);
                }
            } else {
                if (unmatched > 0)  {
                    int curBest = i - leftmost.get(unmatched - 1) + 1;
                    best = Math.max(best, curBest);
                }
                unmatched--;
                if (unmatched < 0) {
                    leftmost.clear();
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        HardP32LongestValidParentheses hardP32LongestValidParentheses = new HardP32LongestValidParentheses();
        assert 2 == hardP32LongestValidParentheses.longestValidParentheses("(()");
        assert 4 == hardP32LongestValidParentheses.longestValidParentheses("()()");
        assert 4 == hardP32LongestValidParentheses.longestValidParentheses("(()()");
        assert 6 == hardP32LongestValidParentheses.longestValidParentheses("(()())");
        assert 0 == hardP32LongestValidParentheses.longestValidParentheses("");
    }
}
