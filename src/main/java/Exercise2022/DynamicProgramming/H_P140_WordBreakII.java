package Exercise2022.DynamicProgramming;

import Exercise2022.ArrayProblems.TestUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class H_P140_WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String>[] t = new ArrayList[s.length() + 1];
        t[0] = new ArrayList<>();
        t[0].add("");
        for (int i = 0; i < s.length(); i++) {
            if (t[i] != null) {
                for (String w : wordDict) {
                    if (s.startsWith(w, i)) {
                        if (t[i + w.length()] == null) {
                            t[i + w.length()] = new ArrayList<>();
                        }
                        for (String u : t[i]) {
                            t[i + w.length()].add(u + " " + w);
                        }
                    }
                }
            }
        }
        return t[s.length()] == null
                ? List.of()
                : t[s.length()].stream()
                    .map(u -> u.trim())
                    .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        H_P140_WordBreakII p = new H_P140_WordBreakII();
        List<String> actual = p.wordBreak("catsanddog", Arrays.asList("cat","cats","and","sand","dog"));
        List<String> expected = Arrays.asList("cats and dog", "cat sand dog");
        TestUtil.equals(expected, actual);

        actual = p.wordBreak("pineapplepenapple", Arrays.asList("apple","pen","applepen","pine","pineapple"));
        expected = Arrays.asList("pine apple pen apple","pineapple pen apple","pine applepen apple");
        TestUtil.equals(expected, actual);

        actual = p.wordBreak("catsandog", Arrays.asList("cats","and","sand","dog", "cat"));
        expected = List.of();
        TestUtil.equals(expected, actual);
    }
}
