package Exercise2022.Tree;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * 124. Binary Tree Maximum Path Sum
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
 * has an edge connecting them. A node can only appear in the sequence at most once. Note that the
 * path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class H_P124_MaximumPathSum {
    /*
     * return pair, first is the solution rooted at the node, second is the best solution starting
     * at the node.
     */
    private Pair<Integer, Integer> subSolution(TreeNode root) {
        if (root == null) {
            return ImmutablePair.of(0,0);
        }
        Pair<Integer, Integer> leftSubSolution = subSolution(root.left);
        Pair<Integer, Integer> rightSubSolution = subSolution(root.right);
        int bestRootedPath = root.val + Math.max(leftSubSolution.getRight(), rightSubSolution.getRight());
        int bestChildPath = Math.max(leftSubSolution.getLeft(), rightSubSolution.getRight());
        int bestPath = Math.max(leftSubSolution.getLeft() + root.val + rightSubSolution.getRight(),
                                bestChildPath);
        return ImmutablePair.of(bestPath, bestRootedPath);
    }

    public int maxPathSum(TreeNode root) {
        return subSolution(root).getLeft();
    }

    public int maxPathSum(Integer[] levelTraversal) {
        TreeNode root = new TreeNode(levelTraversal);
        return maxPathSum(root);
    }

    public static void main(String[] args) {
        H_P124_MaximumPathSum hardP124MaximumPathSum = new H_P124_MaximumPathSum();
        Integer[] levelTraversal = {-10,9,20,null,null,15,7};
        assert 42 == hardP124MaximumPathSum.maxPathSum(levelTraversal);
    }

}
