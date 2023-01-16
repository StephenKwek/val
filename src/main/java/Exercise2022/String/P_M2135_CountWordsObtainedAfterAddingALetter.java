package Exercise2022.String;

import java.util.Arrays;

public class P_M2135_CountWordsObtainedAfterAddingALetter {
    private boolean match(String t, String k) {
        int i = 0;
        while (i < k.length() && t.charAt(i) == k.charAt(i)) {
            i++;
        }
        while (i < k.length() && t.charAt(i + 1) == k.charAt(i)) {
            i++;
        }
        return i == k.length();

    }

    private String sortString(String s) {
        char[] tempArray = s.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public int wordCount(String[] startWords, String[] targetWords) {
        String[] keys = new String[startWords.length];
        for (int i = 0; i < startWords.length; i++) {
            keys[i] = sortString(startWords[i]);
        }
        int total = 0;
        for (String w : targetWords) {
            String t = sortString(w);
            boolean obtainable = Arrays.stream(keys)
                    .filter(k -> k.length() + 1 == t.length())
                    .anyMatch(k -> match(t, k));
            if (obtainable) {
                total++;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        P_M2135_CountWordsObtainedAfterAddingALetter p = new P_M2135_CountWordsObtainedAfterAddingALetter();
        String[] startWords = {"ant","act","tack"};
        String[] targetWords ={"tack","act","acti"};
        assert 2 == p.wordCount(startWords, targetWords);
        startWords = new String[] {"ab", "a"};
        targetWords = new String[] {"abc", "abcd"};
        assert 1 == p.wordCount(startWords, targetWords);
    }
}