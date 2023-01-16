package Exercise2022.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 863. All Nodes Distance K in Binary Tree
 * Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the
 * values of all nodes that have a distance k from the target node.
 *
 * You can return the answer in any order.
 */
public class M_P863_AllNodesDistanceKInBinaryTree {
    private List<TreeNode> findPath(TreeNode root, TreeNode target) {
        if (root == target) {
            return Arrays.asList(root);
        }
        List<TreeNode> left = findPath(root.left, target);
        if (left != null) {
            left.add(root);
            return left;
        }
        List<TreeNode> right = findPath(root.right, target);
        if (right != null) {
            right.add(root);
            return right;
        }
        return null;
    }

    private List<Integer> subSoln(TreeNode root, int k) {
        List<TreeNode> frontier = new ArrayList();
        frontier.add(root);
        for (int i = 1; i < k; i++) {
            List<TreeNode> nextFrontier = new ArrayList();
            for (TreeNode cur : frontier) {
                if (cur.left != null) {
                    nextFrontier.add(cur.left);
                }
                if (cur.right != null) {
                    nextFrontier.add(cur.right);
                }
            }
            frontier = nextFrontier;
        }
        return frontier.stream()
                .filter(u -> u.val - root.val == k)
                .map(u -> u.val)
                .collect(Collectors.toList());
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<TreeNode> path = findPath(root, target);
        return subSoln(root, k);
    }

}
