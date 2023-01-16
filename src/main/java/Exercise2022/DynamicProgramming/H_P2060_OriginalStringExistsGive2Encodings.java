package Exercise2022.DynamicProgramming;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 2060. Check if an Original String Exists Given Two Encoded Strings
 *
 * An original string, consisting of lowercase English letters, can be encoded by the following steps:
 *
 * Arbitrarily split it into a sequence of some number of non-empty substrings.
 * Arbitrarily choose some elements (possibly none) of the sequence, and replace each with its length (as a numeric string).
 * Concatenate the sequence as the encoded string.
 * For example, one way to encode an original string "abcdefghijklmnop" might be:
 *
 * Split it as a sequence: ["ab", "cdefghijklmn", "o", "p"].
 * Choose the second and third elements to be replaced by their lengths, respectively. The sequence becomes ["ab", "12", "1", "p"].
 * Concatenate the elements of the sequence to get the encoded string: "ab121p".
 * Given two encoded strings s1 and s2, consisting of lowercase English letters and digits 1-9 (inclusive), return true if there exists an original string that could be encoded as both s1 and s2. Otherwise, return false.
 *
 * Note: The test cases are generated such that the number of consecutive digits in s1 and s2 does not exceed 3.
 */
public class H_P2060_OriginalStringExistsGive2Encodings {
    private List<String> expand(String s) {
        List<String> soln = new ArrayList<>();
        if (!s.isEmpty()) {
            char c = s.charAt(0);
            if ('0' <= c && c < '9') {
                int l = 0;
                for (int i = 0; i < s.length() && '0' <= s.charAt(i) && s.charAt(i) < '9'; i++) {
                    l = l*10 + (s.charAt(i) - '0');
                    String prefix = "*".repeat(l);
                    List<String> subSoln = (i + 1 < s.length())
                            ? expand(s.substring(i + 1)).stream()
                                .map(sub -> prefix + sub)
                                .collect(Collectors.toList())
                            : List.of(prefix);
                    soln.addAll(subSoln);
                }
            } else {
                int i = 0;
                for (; i < s.length() && 'a' <= s.charAt(i) && s.charAt(i) < 'z'; i++) ;
                if (i < s.length()) {
                    String prefix = s.substring(0, i);
                    soln = expand(s.substring(i)).stream()
                            .map(sub -> prefix + sub)
                            .collect(Collectors.toList());
                } else {
                    soln.add(s);
                }
            }
        }
        Collections.sort(soln, Comparator.comparing(String::length));
        return soln;
    }

    private boolean match(String s, String t) {
        if (s.length() == t.length()) {
            return IntStream.range(0, s.length())
                    .allMatch(i -> s.charAt(i) == '*' || t.charAt(i) == '*' || s.charAt(i) == s.charAt(i));
        } else {
            return false;
        }
    }

    public boolean possiblyEquals(String s1, String s2) {
        List<String> stringsA = expand(s1);
        List<String> stringsB = expand(s2);
        int i = 0;
        int j = 0;
        while (i < stringsA.size() && j < stringsB.size()) {
            String a = stringsA.get(i);
            String b = stringsB.get(j);
            if (a.length() == b.length()) {
                int k = 0;
                for (; i < stringsA.size() && stringsA.get(i).length() == a.length(); i++) {
                    k = j;
                    for (; k < stringsB.size() && stringsB.get(k).length() == b.length(); k++) {
                        if (match(stringsA.get(i), stringsB.get(k))) {
                            return true;
                        }
                    }
                }
                j = k;
                for (; j < stringsB.size() && stringsB.get(j).length() == b.length(); j++) ;
            } else if (a.length() < b.length()) {
                i++;
            } else {
                j++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        H_P2060_OriginalStringExistsGive2Encodings p = new H_P2060_OriginalStringExistsGive2Encodings();
        assert p.possiblyEquals("l123e", "44");
    }
}
