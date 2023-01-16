package Exercise2022.String;

import java.util.HashMap;
import java.util.Map;

/**
 * 424. Longest Repeating Character Replacement
 * You are given a string s and an integer k. You can choose any character of the string and change it to any
 * other uppercase English character. You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after
 * performing the above operations.
 */
public class M_P424_LongestRepeatingCharReplacement {
    private int getRemaining(Map<Character, Integer> counter, char c) {
        return counter.entrySet()
                .stream()
                .filter(characterIntegerEntry -> characterIntegerEntry.getKey() != c)
                .map(Map.Entry::getValue)
                .reduce(0, Integer::sum);
    }

    // NOTE THAT THIS IS O(n) algorithm!!!
    public int characterReplacement(String s, int k) {
        int remaining = k;
        int best = 0;
        int j = 0;
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < s.length();) {
            remaining = k - getRemaining(counter, s.charAt(i));
            if (remaining >= 0) {
                while (remaining > 0 || (j < s.length() && s.charAt(j) == s.charAt(i))) {
                    counter.put(s.charAt(j), counter.getOrDefault(s.charAt(j), 0) + 1);
                    if (s.charAt(j) != s.charAt(i)) {
                        remaining--;
                    }
                    j++;
                }
                best = Math.max(best, j - i);
                counter.get(s.charAt(i));
            }
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
            i++;
        }
        return best;
    }

    public static void main(String[] args) {
        M_P424_LongestRepeatingCharReplacement p = new M_P424_LongestRepeatingCharReplacement();
        assert 4 == p.characterReplacement("ABAB", 2);
        assert 4 == p.characterReplacement("AABABBA", 1);
    }
}
