package Exercise2022.DynamicProgramming;

public class M_P91_DecodeWays {
    public int numDecodings(String s) {
        int[] ways = new int[s.length()];
        ways[s.length() - 1] = (s.charAt(s.length() - 1) == '0') ? 0 : 1;
        for (int i = s.length() - 2; i >= 0; i--) {
            ways[i] = s.charAt(i) == '0'
                      ? ways[i+1]
                      : s.charAt(i) == '1'
                            ? ways[i + 1] + (i + 2 < s.length() ? ways[i + 2] : 1)
                            : s.charAt(i) == '2' && '0' <= s.charAt(i)  && s.charAt(i) <= '6'
                                ? ways[i + 1] + (i + 2 < s.length() ? ways[i + 2] : 1)
                                : ways[i];
        }
        return s.charAt(0) == '0' ? 0 : ways[0];
    }

    public static void main(String[] args) {
        M_P91_DecodeWays p = new M_P91_DecodeWays();
        assert 2 == p.numDecodings("12");
        assert 3 == p.numDecodings("226");
        assert 0 == p.numDecodings("06");

    }
}
