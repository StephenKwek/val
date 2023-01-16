package Exercise2022.Graph;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 1048. Longest String Chain
 * You are given an array of words where each word consists of lowercase English letters.
 *
 * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing
 * the order of the other characters to make it equal to wordB.
 *
 * For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
 * A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2,
 * word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
 *
 * Return the length of the longest possible word chain with words chosen from the given list of words.
 */
public class M_P1048_LongestStringChain {
    private boolean reachable(String shortS, String longS) {
        if (shortS.length() + 1 != longS.length()) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < shortS.length();) {
            if (shortS.charAt(i) != longS.charAt(i + diff)) {
                if (diff > 0) {
                    return false;
                }
                diff++;
            } else {
                i++;
            }
        }
        return true;
    }

    private Map<String, List<String>> toGraph(String[] words) {
        Map<String , List<String>> g = new HashMap<>();
        Set<String> roots = Arrays.stream(words).collect(Collectors.toSet());
        for (int i = 0; i < words.length; i++) {
            g.put(words[i], new ArrayList<>());
            for (int j = 0; j < words.length; j++) {
                if (reachable(words[i], words[j])) {
                    g.get(words[i]).add(words[j]);
                    roots.remove(words[j]);
                }
            };
        }
        g.put("", new ArrayList<>(roots));
        return g;
    }

    public int longestStrChain(String[] words) {
        Map<String, List<String>> g = toGraph(words);
        Queue<Pair<Integer, String>> q = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        q.add(Pair.of(0,""));
        int maxLen = 0;
        while (!q.isEmpty()) {
            Pair<Integer, String> cur = q.poll();
            seen.add(cur.getRight());
            maxLen = Math.max(maxLen, cur.getLeft());
            if (g.containsKey(cur.getRight())) {
                q.addAll(
                        g.get(cur.getRight()).stream()
                                .filter(s -> !seen.contains(s))
                                .map(s -> ImmutablePair.of(cur.getLeft() + 1, s))
                                .collect(Collectors.toList())
                );
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        M_P1048_LongestStringChain p = new M_P1048_LongestStringChain();
        String[] words = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
        assert 5 == p.longestStrChain(words);
        words = new String[] {"abcd","dbqca"};
        assert 1 == p.longestStrChain(words);
    }
}
