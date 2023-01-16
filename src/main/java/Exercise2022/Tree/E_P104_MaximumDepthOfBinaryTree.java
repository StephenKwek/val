package Exercise2022.Tree;

import Exercise2022.String.E_P125_ValidPalindrome;

public class E_P104_MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        return (root == null)
                ? 0
                : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static void main(String[] args) {
        E_P104_MaximumDepthOfBinaryTree p = new E_P104_MaximumDepthOfBinaryTree();
        assert 3 == p.maxDepth(new TreeNode(new Integer[]{3, 9, 20, null, null, 15, 7}));
        assert 2 == p.maxDepth(new TreeNode(new Integer[]{1, null, 2}));
    }
}
