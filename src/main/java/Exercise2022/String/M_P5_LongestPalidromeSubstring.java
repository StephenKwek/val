package Exercise2022.String;

import java.util.stream.IntStream;

public class M_P5_LongestPalidromeSubstring {
    private boolean isPalindrome(final String s, int left, int right) {
        return IntStream.range(0, (right - left + 1) / 2 )
                .allMatch(i -> s.charAt(left) == s.charAt(right - i));
    }

    public String longestPalindrome(String s) {
        String palindrome = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= Math.min(s.length() - 1 - i, i); j++) {
                if (isPalindrome(s, i - j, i + j) && 2 * j + 1 > palindrome.length())  {
                    palindrome = s.substring(i - j, i + j + 1);
                }
            }
            for (int j = 1; i + 2*j <= s.length(); j++) {
                if (isPalindrome(s, i, i + 2*j - 1) && 2*j > palindrome.length()) {
                    palindrome = s.substring(i, i + 2*j);
                }
            }
        }
        return palindrome;
    }

    public static void main(String[] args) {
        M_P5_LongestPalidromeSubstring p = new M_P5_LongestPalidromeSubstring();
        assert "bab".equals(p.longestPalindrome("babab"));
        assert "bb".equals(p.longestPalindrome("cbbd"));
    }
}
