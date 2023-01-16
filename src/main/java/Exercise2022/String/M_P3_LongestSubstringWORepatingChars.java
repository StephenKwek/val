package Exercise2022.String;

import java.util.HashSet;
import java.util.Set;

public class M_P3_LongestSubstringWORepatingChars {
    public int lengthOfLongestSubstring(String s) {
       Set<Character> appear = new HashSet<>();
       int best = -1;
       int j = 0;
       for (int i = 0; i < s.length(); i++) {
           while (j+1 < s.length() && !appear.contains(s.charAt(j+1))) {
               j++;
               appear.add(s.charAt(j));
           }
           best = Math.max(best, appear.size());
           appear.remove(s.charAt(i));
       }
       return best;
    }

    public static void main(String[] args) {
        M_P3_LongestSubstringWORepatingChars p = new M_P3_LongestSubstringWORepatingChars();
        assert 3 == p.lengthOfLongestSubstring("abcabcbb");
        assert 1 == p.lengthOfLongestSubstring("bbb");
        assert 3 == p.lengthOfLongestSubstring("pwwkew");
    }
}
