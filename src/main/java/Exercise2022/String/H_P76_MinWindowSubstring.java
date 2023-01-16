package Exercise2022.String;

import javax.imageio.metadata.IIOMetadataNode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring
 *
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring
 *  of s such that every character in t (including duplicates) is included in the window.
 *  If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 */
public class H_P76_MinWindowSubstring {
    private boolean containsAllTarget(int[] count, int target) {
        return Arrays.stream(count)
                .filter(entry -> entry > 0)
                .count() == target;
    }

    public String minWindow(String s, String t) {
        int[] counter = new int[26];
        String best = "";
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while ((j < s.length())
                    && !containsAllTarget(counter, t.length())) {
                if (t.indexOf(s.charAt(j)) >= 0) {
                    counter[s.charAt(j) - '0'] += 1;
                }
                j++;
            }
            best = (best.isEmpty() || best.length() > j - i) && containsAllTarget(counter, t.length())
                    ? s.substring(i, j)
                    : best;
            if (counter[s.charAt(i) - 'a'] > 0) {
                counter[s.charAt(i)] -= 1;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        H_P76_MinWindowSubstring p = new H_P76_MinWindowSubstring();
        assert "BANC".equals(p.minWindow("ADOBECODEBANC", "ABC"));
        assert "a".equals(p.minWindow("a", "a"));
        assert "".equals(p.minWindow("a", "aa"));
    }
}
