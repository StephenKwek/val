package Exercise2022.Graph;

import Exercise2022.ArrayProblems.TestUtil;
import Exercise2022.DynamicProgramming.H_P140_WordBreakII;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.concurrent.Immutable;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 126. Word Ladder II
 *
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists.
 * Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 */
public class H_P126_WordLadderII {
    private Integer distance(String u, String v) {
        return Math.toIntExact(IntStream.range(0, u.length())
                .mapToObj(i -> u.charAt(i) != v.charAt(i))
                .filter(diff -> diff)
                .count());
    }
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<Map<String, List<List<String>>>> levels = new ArrayList<>();
        levels.add(
                ImmutableMap.of(beginWord, Collections.singletonList(List.of(beginWord)))
        );
        Queue<Pair<String, Integer>> q = new LinkedList<>();
        q.add(ImmutablePair.of(beginWord, 0));
        List<List<String>> soln = List.of();
        int lastLevel = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Pair<String, Integer> cur = q.poll();
            String curWord = cur.getLeft();
            int curLevel = cur.getRight();
            List<List<String>> previousPaths =  levels.get(curLevel).get(curWord);
            for (String w : wordList) {
                if (distance(w, curWord) == 1) {
                    int nextLevel = curLevel + 1;
                    if (nextLevel > lastLevel) {
                        break;
                    }
                    if (levels.size() - 1 < nextLevel) {
                        levels.add(new HashMap<>());
                    }
                    if (!levels.get(nextLevel).containsKey(w)) {
                        levels.get(nextLevel).put(w, new ArrayList<>());
                    }
                    for (List<String> partial : previousPaths) {
                        List<String> extended = new ArrayList<>(partial);
                        extended.add(w);
                        levels.get(nextLevel).get(w).add(extended);
                    }
                    q.add(ImmutablePair.of(w, nextLevel));
                    if (w.equals(endWord)) {
                        soln = levels.get(nextLevel).get(w);
                        if (lastLevel == Integer.MAX_VALUE) {
                            lastLevel = nextLevel;
                        }
                    }
                }
            }
        }
        return soln;
    }

    public static void main(String[] args) {
        H_P126_WordLadderII p   = new H_P126_WordLadderII();
        List<List<String>> actual = p.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        TestUtil.equals(Arrays.asList("hit","hot","dot","dog","cog"), actual.get(0));
        TestUtil.equals(Arrays.asList("hit","hot","lot","log","cog"), actual.get(1));
    }
}


