package Exercise2022.String;

/**
 * 1216. Valid Palindrome III
 *
 * Given a string s and an integer k, return true if s is a k-palindrome.
 *
 * A string is k-palindrome if it can be transformed into a palindrome by removing at most k
 * characters from it.
 */
public class H_P1216_ValidPalindromeIII {
    public boolean isValidPalindrome(String s, int k) {
        int[][] subSoln = new int[s.length()][s.length()];
        for (int i = 0; i < s.length() -1; i++) {
            subSoln[i][1] = (s.charAt(i) == s.charAt(i+1)) ? 0: 1;
        }
        for (int l = 2; l  < s.length(); l++) {
            for (int i = 0; i  < s.length() - l; i++) {
                if (s.charAt(i + l) == s.charAt(i)) {
                    subSoln[i][l] = subSoln[i+1][l - 2];
                } else {
                    subSoln[i][l] = Math.min(
                            2 + subSoln[i+1][l-2],
                            1 + Math.min(subSoln[i+1][l-1], subSoln[i][l-1])
                    );
                }
            }
        }
        return subSoln[0][s.length() - 1] <= k;
    }

    public static void main(String[] args) {
        H_P1216_ValidPalindromeIII p = new H_P1216_ValidPalindromeIII();
        assert p.isValidPalindrome("abcdeca", 2);
        assert p.isValidPalindrome("abbababa", 1);
        assert !p.isValidPalindrome("abcdeca", 1);
    }

}
