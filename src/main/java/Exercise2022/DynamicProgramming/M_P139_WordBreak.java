package Exercise2022.DynamicProgramming;

import java.util.List;

public class M_P139_WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] breakable = new boolean[s.length() + 1];
        breakable[0] = true;

            for (int i = 0; i < s.length(); i++) {
                int finalI = i;
                wordDict.forEach(w -> {
                    if (breakable[finalI] && s.startsWith(w, finalI)) {
                        breakable[finalI + w.length()] = true;
                    }
                });
            };
        return breakable[s.length()];
    }

    public static void main(String[] args) {
        M_P139_WordBreak p = new M_P139_WordBreak();
        assert p.wordBreak("leetcode", List.of("leet", "code"));
        assert p.wordBreak("applepenapple", List.of("apple", "pen"));
        assert !p.wordBreak("catsandog", List.of("cats","dog","sand","and","cat"));
    }
}
