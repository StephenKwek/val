package Exercise2022.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P_M323_NumerOfDirectedComponentInUndirectedGraph {
    private void DFS(List<Integer>[] g, int node, Set<Integer> seen) {
        seen.add(node);
        g[node].stream()
                .filter(u -> !seen.contains(u))
                .forEach(u -> DFS(g, u, seen));
    }

    private List<Integer>[] convertToAdjacenyMatrix(int n, int[][] edges) {
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            if (!g[edge[0]].contains(edge[1])) {
                g[edge[0]].add(edge[1]);
            }
            if (!g[edge[1]].contains(edge[0])) {
                g[edge[1]].add(edge[0]);
            }
        }
        return g;
    }

    public int countComponents(int n, int[][] edges) {
        final Set<Integer> seen = new HashSet<>();
        final List<Integer>[] g = convertToAdjacenyMatrix(n, edges);
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!seen.contains(i)) {
                count++;
                DFS(g, i, seen);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        P_M323_NumerOfDirectedComponentInUndirectedGraph p = new P_M323_NumerOfDirectedComponentInUndirectedGraph();
        assert 2 == p.countComponents(5, new int[][] {{0, 1}, {1, 2}, {3, 4}});
        assert 1 == p.countComponents(5, new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 4}});
    }
}
