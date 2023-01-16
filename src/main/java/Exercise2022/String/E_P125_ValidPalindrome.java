package Exercise2022.String;

import java.util.stream.IntStream;

public class E_P125_ValidPalindrome {
    public boolean isPalindrome(String s) {
        String finalS = s.trim().replaceAll(" +", "")
                .toLowerCase()
                .replaceAll("[^a-z]", "");
        return IntStream.range(0, finalS.length()/2)
                .allMatch(i -> finalS.charAt(i) == finalS.charAt(finalS.length() - 1 - i));
    }

    public static void main(String[] args) {
        E_P125_ValidPalindrome p = new E_P125_ValidPalindrome();
        assert p.isPalindrome("A man, a plan, a canal:    Panama");
        assert !p.isPalindrome("race a car");
    }
}
