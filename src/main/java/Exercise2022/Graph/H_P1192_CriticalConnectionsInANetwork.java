package Exercise2022.Graph;

import java.util.*;

/**
 * 1192. Critical Connections in a Network
 *
 *There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 */
public class H_P1192_CriticalConnectionsInANetwork {
    public List<List<Integer>> criticalConnections(int n, int[][] connections) {
        Map<Integer, List<Integer>> G = Node.convertToGraph(connections);
        Stack<Integer> s = new Stack<>();
        s.push(0);
        List<List<Integer>> soln = new ArrayList<>();
        dfs(G, new HashSet<>(), 0, new Stack<>(), soln);
        return soln;
    }

    private Set<Integer> dfs(Map<Integer, List<Integer>> G, Set<Integer> seen, int u, Stack<Integer> s, List<List<Integer>> soln) {
        Set<Integer> cross = new HashSet<>();
        Integer parent = !s.isEmpty() ? s.peek() : null;
        seen.add(u);
        s.add(u);
        for (int v : G.get(u)) {
            if (!seen.contains(v)) {
                cross.addAll(dfs(G, seen, v, s, soln));
            } else if (s.contains(v) && v != parent){
                cross.add(v);
            }
        }
        if (cross.isEmpty() && parent != null) {
            soln.add(Arrays.asList(u, parent));
        }
        s.pop();
        cross.remove(u);
        return cross;
    }

    public static void main(String[] args) {
        H_P1192_CriticalConnectionsInANetwork p = new H_P1192_CriticalConnectionsInANetwork();
        System.out.println(p.criticalConnections(4, new int[][] {{0,1}, {1,2}, {2,0}, {1,3}}));
        System.out.println(p.criticalConnections(4, new int[][] {{0,1}}));
        /*
        p.criticalConnections(4, new int[][] {{0,1}, {1,2}, {2,0}, {1,3}});
         */
    }
}

