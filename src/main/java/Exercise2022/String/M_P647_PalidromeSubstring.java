package Exercise2022.String;

import java.util.stream.IntStream;

public class M_P647_PalidromeSubstring {
    private boolean isPalindrome(final String s, int left, int right) {
        return IntStream.range(0, (right - left + 1) / 2 )
                .allMatch(i -> s.charAt(left) == s.charAt(right - i));
    }

    public int countSubstrings(String s) {
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= Math.min(s.length() - i - 1, i); j++) {
                System.out.println((i-j) + "," + (i + j));
                if (isPalindrome(s, i - j, i + j)) {
                    total++;
                } else {
                    break;
                }
            }
            for (int j = 1; 0 <= i - j && i + j - 1< s.length(); j++) {
                if (isPalindrome(s, i - j, i + j - 1)) {
                    total++;
                } else {
                    break;
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        M_P647_PalidromeSubstring p = new M_P647_PalidromeSubstring();
        System.out.println(p.countSubstrings("abc"));
        assert 3 == p.countSubstrings("abc");
        assert 6 == p.countSubstrings("aaa");
    }
}
