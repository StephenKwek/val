package Exercise2022.Combinatorics;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.primitives.Chars;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class H_P691_stickers2SpellWord {
    private boolean isEmpty(int[] t) {
        for (int i = 0; i < t.length; i++) {
            if (t[i] > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean hasMatch(int[] s, int[] t) {
        for (int i = 0; i < t.length; i++) {
            if (t[i] > 0 && s[i] > 0) {
                return true;
            }
        }
        return false;
    }

    private int[] consume(int[] s, int[] t) {
        int[] diff = new int[26];
        for (int i = 0; i < t.length; i++) {
            if (t[i] > 0) {
                diff[i] = (s[i] > 0) ? Math.max(0, t[i] - s[i]) : t[i];
            }
        }
        return diff;
    }


    private int recursion(int[][] s, int[] t) {
        if (isEmpty(t)) {
            return 0;
        }
        Optional<Integer> subSoln = IntStream.range(0, s.length)
                .boxed()
                .filter(i -> hasMatch(s[i], t))
                .map(i -> recursion(s, consume(s[i], t)))
                .filter(sub -> sub != -1)
                .map(sub -> 1 + sub)
                .min(Integer::compareTo);
        return subSoln.isEmpty() ? -1 : subSoln.get();
    }

    public int minStickers(String[] stickers, String target) {
        int[] t = new int[26];
        for (char c : target.toCharArray()) {
            int i = c - 'a';
            t[i] += 1;
        }

        int[][] s = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            for (int j = 0; j < stickers[i].length(); j++) {
                int index = (stickers[i].charAt(j) - 'a');
                s[i][index] += 1;
            }
        }
        return recursion(s, t);
    }

    public static void main(String[] args) {
        H_P691_stickers2SpellWord p = new H_P691_stickers2SpellWord();
        assert 3 == p.minStickers(new String[] {"with","example","science"}, "thehat");
        assert -1 == p.minStickers(new String[] {"notice","possible"}, "basicbasic");
    }
}
