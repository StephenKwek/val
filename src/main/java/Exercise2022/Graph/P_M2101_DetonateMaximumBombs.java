package Exercise2022.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 2101. Detonate the Maximum Bombs
 * You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt.
 * This area is in the shape of a circle with the center as the location of the bomb.
 *
 * The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote
 * the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.
 *
 * You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its
 * range. These bombs will further detonate the bombs that lie in their ranges.
 *
 * Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate
 * only one bomb.
 */
public class P_M2101_DetonateMaximumBombs {
    private boolean connected(int[] a, int[] b) {
        int xLen = a[0] - b[0];
        int yLen = a[1] - b[1];
        int distanceSq = xLen * xLen + yLen * yLen;
        return (distanceSq <= a[2] * a[2] || distanceSq <= b[2] * b[2]);
    }

    private ArrayList[] toGraph(int[][] bombs)  {
        ArrayList[] g = new ArrayList[bombs.length];
        for (int i = 0; i < bombs.length; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < bombs.length; i++) {
            for (int j = 0; j < i; j++) {
                if (i != j && connected(bombs[i], bombs[j])) {
                    if (!g[i].contains(j)) {
                        g[i].add(j);
                    }
                    if (!g[j].contains(i)) {
                        g[j].add(i);
                    }
                }
            }
        }
        return g;
    }

    private Set<Integer> DFS(List[] g, int root) {
        Set<Integer> seen = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Integer cur = stack.pop();
            seen.add(cur);
            g[cur].stream()
                  .filter(u -> !seen.contains(u))
                  .forEach(v -> stack.add((int) v));
        }
        return seen;
    }

    public int maximumDetonation(int[][] bombs) {
        ArrayList[] g = toGraph(bombs);
        Set<Integer> seen = new HashSet<>();
        int best = 0;
        for (int i = 0; i < bombs.length; i++) {
            if (!seen.contains(i)) {
                Set<Integer> curSeen = DFS(g, i);
                best = Math.max(best, curSeen.size());
                seen.addAll(curSeen);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        P_M2101_DetonateMaximumBombs p = new P_M2101_DetonateMaximumBombs();
        int[][] bombs = new int[][] {{2,1,3},{6,1,4}};
        assert 2 == p.maximumDetonation(bombs);
        bombs = new int[][] {{1,1,5},{10,10,5}};
        assert 1 == p.maximumDetonation(bombs);
        bombs = new int[][] {{1,2,3},{2,3,1},{3,4,2},{4,5,3},{5,6,4}};
        assert 5 == p.maximumDetonation(bombs);
    }
}
