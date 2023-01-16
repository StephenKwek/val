package Exercise2022.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 2421. Number of Good Paths
 * There is a tree (i.e. a connected, undirected graph with no cycles) consisting of n nodes numbered from 0 to n - 1
 * and exactly n - 1 edges.
 *
 * You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node. You are also
 * given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting
 * nodes ai and bi.
 *
 * A good path is a simple path that satisfies the following conditions:
 *
 * The starting node and the ending node have the same value.
 * All nodes between the starting node and the ending node have values less than or equal to the starting node
 * (i.e. the starting node's value should be the maximum value along the path).
 * Return the number of distinct good paths.
 *
 * Note that a path and its reverse are counted as the same path. For example, 0 -> 1 is considered to be the same as
 * 1 -> 0. A single node is also considered as a valid path.
 */
public class QQQ_H_P2421_NumberOfGoodPaths {
    /*
    void rearrange(int[] vals, int[][] edges) {
        int[][] temp = new int[vals.length][2];
        for (int i = 0; i < vals.length; i++) {
            temp[i][0] = vals[i];
            temp[i][1] = i;
        }
        Arrays.sort(temp, Comparator.comparingInt());
        List<Integer[]> val2Index = IntStream.range(0, vals.length)
                .mapToObj(i -> new int[] {vals[i], i})
                .sorted((o1, o2) -> Integer.compare(o2[0], o1[0]))
                .collect(Collectors.toList());

        Map<Integer, Integer> remap = new HashMap<>();
        for (int i = 0; i < vals.length; i++) {
            vals[i] = val2Index.get(i).getLeft();
            remap.put(val2Index.get((int) i).getRight(), i);
        }
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new int[] {remap.get(edges[i][0]), remap.get(edges[i][1])};
        }
    }
     */

    int countPaths(List<Integer>[] g, int[] vals, int root, int minVal, Set<Integer> seen) {
        seen.add(root);
        int paths = g[root].stream()
                .filter(u -> vals[u] >= minVal)
                .filter(u -> vals[u] != minVal || root > u)
                .filter(u -> !seen.contains(u))
                .map(u -> countPaths(g, vals, u, minVal, seen) + 1)
                .reduce(0, Integer::sum);
        return paths;
    }

    private List<Integer>[] toGraph(int[][] edges, int n) {
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            g[edge[1]].add(edge[0]);
            g[edge[0]].add(edge[1]);
        }
        return g;
    }

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        //rearrange(vals, edges);
        List<Integer>[] g = toGraph(edges, vals.length);
        int total = 0;
        for (int i = 0; i < vals.length; i++) {
            total += countPaths(g, vals, i, vals[i], new HashSet<>());
        }
        return total + vals.length;
    }


    public static void main(String[] args) {
        QQQ_H_P2421_NumberOfGoodPaths p = new QQQ_H_P2421_NumberOfGoodPaths();
        //assert 5 == p.numberOfGoodPaths(new int[] {1,2,3}, new int[][] {{0,1}, {0,2}});
        assert 6 == p.numberOfGoodPaths(new int[] {1,1,2,3,3}, new int[][] {{0,3}, {0,2}, {2,1}, {2,4}});
        assert 6 == p.numberOfGoodPaths(new int[] {1,3,2,1,3}, new int[][] {{0,1}, {0,2}, {2,3}, {2,4}});
        assert 7 == p.numberOfGoodPaths(new int[] {1,1,2,2,3}, new int[][] {{0,1}, {1,2}, {2,3}, {2,4}});
    }
}
