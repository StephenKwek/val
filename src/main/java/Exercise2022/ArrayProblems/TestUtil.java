package Exercise2022.ArrayProblems;

import Exercise2022.LinkedList.ListNode;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class TestUtil {
    public static void equals2(List<Integer> l1, List<Integer> l2) {
        assert l1.size() == l2.size();
        for (int i = 0; i < l1.size(); i++) {
            assert l1.get(i) == l2.get(i);
        }
    }

    public static void equals(int[] source, int[] target) {
        assert source.length == target.length;
        for (int i = 0; i < source.length; i++) {
            assert source[i] == target[i];
        }
    }

    public static void equals(double[] source, double[] target) {
        assert source.length == target.length;
        for (int i = 0; i < source.length; i++) {
            System.out.println(i + "," + source[i] + ","  + target[i]);
            assert (Math.abs(source[i] - target[i]) < 0.0001);
        }
    }

    public static void equals(Integer[] source, Integer[] target) {
        assert source.length == target.length;
        for (int i = 0; i < source.length; i++) {
            assert source[i] == target[i];
        }
    }

    public static void equals(int[][] source, int[][] target) {
        assert source.length == target.length;
        assert source[0].length == target[0].length;
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[0].length; j++) {
                assert source[i][j] == target[i][j];
            }
        }
    }

    public static void equals(List<String> l1, List<String> l2) {
        assert l1.size() == l2.size();
        assert l1.stream().allMatch(l2::contains);
    }

    public static void equals(ListNode h1, ListNode h2) {
        while (h1 != null && h2 != null) {
            assert h1.val == h2.val;
            h1 = h1.next;
            h2 = h2.next;
        }
        assert h1 == null & h2 == null;
    }

    private static int bfsDepth(List<Integer>[] G, Integer root) {
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();
        q.add(ImmutablePair.of(root, 0));
        int depth = 1;
        while (!q.isEmpty()) {
            Pair<Integer, Integer> cur = q.poll();
            depth = cur.getRight();
            int finalNextLevel = ++depth;
            if (G[cur.getLeft()] != null) {
                List<ImmutablePair<Integer, Integer>> unseenNeighbors = G[cur.getLeft()].stream()
                        .filter(v -> !seen.contains(v))
                        .map(v -> ImmutablePair.of(v, finalNextLevel))
                        .collect(Collectors.toList());
                q.addAll(unseenNeighbors);
            }
        }
        return depth;
    }

    public static Integer bfsForestDepth(List<Integer>[] G, Set<Integer> roots) {
        return roots.stream()
                .map(root -> bfsDepth(G, root))
                .max(Integer::compareTo)
                .get();
    }
}
