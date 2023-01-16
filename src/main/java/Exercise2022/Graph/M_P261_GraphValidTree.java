package Exercise2022.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import static Exercise2022.Graph.Node.convertToGraph;

/**
 * 261. Graph Valid Tree
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 */
public class M_P261_GraphValidTree {

    private boolean detectCycleDFS(Map<Integer, List<Integer>> g, int root, final Set<Integer> seen) {
        seen.add(root);
        boolean hasCycle = g.getOrDefault(root, List.of())
                .stream()
                .anyMatch(seen::contains);
        if (hasCycle) {
            return true;
        } else {
            return g.getOrDefault(root, List.of()).stream()
                    .anyMatch(u -> detectCycleDFS(g, u, seen));
        }
    }

    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> g = convertToGraph(edges);
        Set<Integer> seen = new HashSet<>();
        if (detectCycleDFS(g, 0, seen)) {
            return false;
        }
        return seen.size() == n;
    }

    public static void main(String[] args) {
        M_P261_GraphValidTree p = new M_P261_GraphValidTree();
        assert p.validTree(5, new int[][] {{0,1}, {0,2}, {0,3}, {1,4}});
        assert !p.validTree(5, new int[][] {{0,1}, {1,2}, {2,3}, {1,3}, {1,4}});

    }
}
