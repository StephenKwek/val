package Exercise2022.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

    public static Map<Integer, List<Integer>> convertToGraph(int[][] edges) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int[] edge: edges) {
            if (!g.containsKey(edge[0])) {
                g.put(edge[0], new ArrayList<>());
            }
            g.get(edge[0]).add(edge[1]);
            if (!g.containsKey(edge[1])) {
                g.put(edge[1], new ArrayList<>());
            }
            g.get(edge[1]).add(edge[0]);
        }
        return g;
    }

    public static Map<Integer, List<Integer>> convertToGraph(List<List<Integer>> edges) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (List<Integer> edge: edges) {
            if (!g.containsKey(edge.get(0))) {
                g.put(edge.get(0), new ArrayList<>());
            }
            g.get(edge.get(0)).add(edge.get(1));
            if (!g.containsKey(edge.get(1))) {
                g.put(edge.get(1), new ArrayList<>());
            }
            g.get(edge.get(1)).add(edge.get(0));
        }
        return g;
    }
}