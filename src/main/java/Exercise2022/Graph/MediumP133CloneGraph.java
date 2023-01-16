package Exercise2022.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 133. Clone Graph
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 */
public class MediumP133CloneGraph {
    public Node cloneGraph(Node node) {
        // create new nodes
        Map<Integer, Node> val2NewNode = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        Set<Node> seen = new HashSet<>();
        val2NewNode.put(node.val, new Node(node.val));
        q.add(node);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            seen.add(cur);
            for (Node neighbor : cur.neighbors) {
                if (!seen.contains(neighbor)) {
                    q.add(neighbor);
                    val2NewNode.put(neighbor.val, new Node(neighbor.val));
                }
            }
        }
        // create the neighbors
        seen.forEach(oldNode -> {
            Node newNode = val2NewNode.get(oldNode.val);
            newNode.neighbors = oldNode.neighbors
                    .stream()
                    .map(neighbor -> val2NewNode.get(neighbor.val))
                    .collect(Collectors.toList());
        });
        return val2NewNode.get(node.val);
    }
}
