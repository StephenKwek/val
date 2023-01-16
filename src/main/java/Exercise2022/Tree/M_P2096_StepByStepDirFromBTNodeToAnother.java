package Exercise2022.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2096. Step-By-Step Directions From a Binary Tree Node to Another
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
 *
 * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
 *
 * 'L' means to go from a node to its left child node.
 * 'R' means to go from a node to its right child node.
 * 'U' means to go from a node to its parent node.
 * Return the step-by-step directions of the shortest path from node s to node t.
 */
public class M_P2096_StepByStepDirFromBTNodeToAnother {

    public String DFS(TreeNode node, int target) {
        if (node == null) {
            return null;
        } else if (node.val == target) {
            return "";
        }
        String left = DFS(node.left, target);
        if (left != null) {
            return "L" + left;
        }
        String right = DFS(node.right, target);
        if (right != null) {
            return "R" + right;
        } else {
            return null;
        }
    }
    public String getDirections(TreeNode root, int startValue, int destValue) {
        String upPath = DFS(root, startValue);
        return "U".repeat(upPath.length()) + DFS(root, destValue);
    }

    public static void main(String[] args) {
        M_P2096_StepByStepDirFromBTNodeToAnother p = new M_P2096_StepByStepDirFromBTNodeToAnother();
        TreeNode root = new TreeNode(new Integer[] {5,1,2,3,null,6,4});
        assert "UURL".equals(p.getDirections(root, 3, 6));
        root = new TreeNode(new Integer[] {2,1});
        assert "L".equals(p.getDirections(root, 2, 1));
    }
}
