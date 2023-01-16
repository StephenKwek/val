package Exercise2022.DynamicProgramming;

import java.util.Arrays;

/**
 * 1055. Shortest Way to Form String
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of
 * the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Given two strings source and target, return the minimum number of subsequences of source such that their
 * concatenation equals target. If the task is impossible, return -1.
 */
public class M_P1055_ShortestWayFromString {
    private boolean contains(String source, String target) {
        int j = 0;
        for (int i = 0; i < target.length(); i++) {
            int k = j;
            for (; k < source.length() && source.charAt(k) != target.charAt(i); k++);
            if (k < source.length()) {
                j =  k + 1;
            } else {
                return false;
            }
        }
        return true;
    }
    public int shortestWay(String source, String target) {
        if (target.chars().anyMatch(c -> source.indexOf(c) == -1)) {
            return -1;
        }

        int[] shortest = new int[target.length() + 1];
        Arrays.fill(shortest, 0);

        for (int i = 1; i <= target.length(); i++) {
            for (int j = 1; j <= source.length() && i - j >= 0; j++) {
                int best = Integer.MAX_VALUE; // it is guaranteed that the following condition will be true for some j, say j == 1
                if (contains(source, target.substring(i - j, i))) {
                    shortest[i] = Math.min(best, shortest[i - j] + 1);
                }
            }
        }
        return shortest[target.length()];
    }

    public static void main(String[] args) {
        M_P1055_ShortestWayFromString p = new M_P1055_ShortestWayFromString();
        assert 2 == p.shortestWay("abc", "abcbc");
        assert -1 == p.shortestWay("abc", "abdbc");
        System.out.println(p.shortestWay("xyz", "xzyxz"));
        assert 3 == p.shortestWay("xyz", "xzyxz");
    }
}
