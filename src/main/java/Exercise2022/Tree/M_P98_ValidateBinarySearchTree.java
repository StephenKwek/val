package Exercise2022.Tree;

import Exercise2022.String.M_P49_GroupAnagrams;

public class M_P98_ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return (root == null)
              || ((root.left == null || (root.left.val <= root.val && isValidBST(root.left)))
                   && (root.right == null || (root.val <= root.right.val && isValidBST(root.right))));
    }

    public static void main(String[] args) {
        M_P98_ValidateBinarySearchTree p = new M_P98_ValidateBinarySearchTree();
        assert p.isValidBST(new TreeNode(new Integer[] {2,1,3}));
        assert !p.isValidBST(new TreeNode(new Integer[] {5,1,4,null,null,3,6}));
    }


}
