package Exercise2022.Tree;

/**
 * 1483. Kth Ancestor of a Tree Node
 * You are given a tree with n nodes numbered from 0 to n - 1 in the form of a parent array parent where parent[i] is
 * the parent of ith node. The root of the tree is node 0. Find the kth ancestor of a given node.
 *
 * The kth ancestor of a tree node is the kth node in the path from that node to the root node.
 *
 * Implement the TreeAncestor class:
 *
 * TreeAncestor(int n, int[] parent) Initializes the object with the number of nodes in the tree and the parent array.
 * int getKthAncestor(int node, int k) return the kth ancestor of the given node node. If there is no such ancestor, return -1.
 */
public class HardP1483KthAncestorOfATreeNode {
    public int[] parent;
    public HardP1483KthAncestorOfATreeNode(int n, int[] parent) {
        this.parent = parent;
    }

    public int getKthAncestor(int node, int k) {
        int cur = node;
        while(k > 0 && cur != -1) {
            cur = parent[cur];
            k--;
        }
        return cur;
    }

    public static void main(String[] args) {
        int[] tree = {-1, 0, 0, 1, 1, 2, 2};
        HardP1483KthAncestorOfATreeNode treeAncestor = new HardP1483KthAncestorOfATreeNode(7, tree);
        assert 1 == treeAncestor.getKthAncestor(3,1);
        assert 0 == treeAncestor.getKthAncestor(5, 2);
        assert -1 == treeAncestor.getKthAncestor(6, 3);
    }
}
