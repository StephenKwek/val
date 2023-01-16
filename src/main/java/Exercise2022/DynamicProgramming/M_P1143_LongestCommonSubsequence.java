package Exercise2022.DynamicProgramming;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class M_P1143_LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        if (StringUtils.isEmpty(text1) || StringUtils.isEmpty(text2)) {
            return 0;
        }
        int[] opt = new int[text2.length()];
        for (int i = 0; i < text1.length(); i++) {
            int pre = 0;
            for (int j = 0; j < text2.length(); j++) {
                opt[j] = (j == 0)
                        ? opt[j]
                        : Math.max(opt[j], opt[j-1]);
                if (text1.charAt(i) == text2.charAt(j)) {
                    opt[j] = Math.max(opt[j], pre + 1);
                }
                pre = opt[j];
            }
        }
        return Arrays.stream(opt).max().getAsInt();
    }

    public static void main(String[] args) {
        M_P1143_LongestCommonSubsequence p = new M_P1143_LongestCommonSubsequence();
        assert 3 == p.longestCommonSubsequence("abcde", "ace");
        assert 3 == p.longestCommonSubsequence("abc", "abc");
        assert 0 == p.longestCommonSubsequence("abc", "def");
    }

}
