package Exercise2022.String;

import org.apache.commons.lang3.StringUtils;

public class M_P2311_LongestBinarySubsequenceLessThanOrEqualToK {
    private String convertToBinaryString(int k) {
        StringBuffer sb = new StringBuffer();
        while (k > 0) {
            char c = k % 2 == 0 ? '0' : '1';
            sb.append(c);
            k = k / 2;
        }
        return sb.reverse().toString();
    }

    private int longest(String s, String t) {
        int is = s.length() - 1;
        for (int i = t.length() - 1; i >= 0; i--) {
            while (is >= 0 && s.charAt(is) != t.charAt(i)) {
                is--;
            }
            if (is == -1) {
                return -1;
            }
        }
        int best  = t.length();
        for (int i = 0; i < is; i++) {
            if (s.charAt(i) == '0') {
                best++;
            }
        }
        return best;
    }

    public int longestSubsequence(String s, int k) {
        int best = -1;
        for (int i = k; i >= 0; i--) {
            String t = convertToBinaryString(k);
            best = Math.max(best, longest(s, t));
        }
        return best;
    }

    public static void main(String[] args) {
        M_P2311_LongestBinarySubsequenceLessThanOrEqualToK p = new M_P2311_LongestBinarySubsequenceLessThanOrEqualToK();
        System.out.println(p.longestSubsequence("1001010", 5));
        assert 5 == p.longestSubsequence("1001010", 5);
        assert 6 == p.longestSubsequence("00101001", 1);

    }
}
