package Exercise2022.Graph;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 269. Alien Dictionary
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 *
 * For example, Given the following words in dictionary,
 *
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 * The correct order is: "wertf".
 */
public class M_P269_AlienDictionay {
    BitSet[] convertToGraph(String[] words) {
        BitSet[] g = new BitSet[27];
        g[26] = new BitSet(27);
        for (String w : words) {
            g[26].set(w.charAt(0) - 'a');
            for (int i = 0; i < w.length() - 1; i++) {
                if (w.charAt(i) != w.charAt(i+1)) {
                    int u = w.charAt(i) - 'a';
                    if (g[u] == null) {
                        g[u] = new BitSet(26);
                    }
                    g[w.charAt(i) - 'a'].set(w.charAt(i+1) - 'a');
                }
            }
        }
        return g;
    }

    public String DFS(BitSet[] g, int u, BitSet seen) {
        seen.set(u);
        String order;
        if (g[u] == null) {
            order = "";
        } else {
            order = g[u].stream().boxed()
                    .filter(v -> !seen.get(v))
                    .map(v -> DFS(g, v, seen))
                    .collect(Collectors.joining());
        }
        return order + ((char) (u + 'a'));
    }

    public String alienOrder(String[] words) {
        BitSet[] g = convertToGraph(words);
        String temp = DFS(g, 26, new BitSet(27));
        return StringUtils.reverse(temp).substring(1);
    }

    public static void main(String[] args) {
        M_P269_AlienDictionay p = new M_P269_AlienDictionay();
        assert "werft".equals(p.alienOrder(new String[] {"wrt", "wrf", "er", "ett", "rftt"}));
    }

}