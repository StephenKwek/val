package Exercise2022.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 *
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 */
public class M_P235_LowestCommonAncestorBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        while ((p.val < cur.val && q.val < cur.val)
                || (p.val > cur.val && q.val > cur.val)) {
            if (p.val < cur.val) {
                cur = lowestCommonAncestor(root.left, p, q);
            } else {
                cur = lowestCommonAncestor(root.right, p, q);
            }
        }
        return cur;
    }

    public static void main(String[] args) {
        M_P235_LowestCommonAncestorBST p = new M_P235_LowestCommonAncestorBST();
        assert 6 == p.lowestCommonAncestor(new TreeNode(new Integer[]{6,2,8,0,4,7,9,null,null,3,5}),
                                          new TreeNode(2),
                                          new TreeNode(8)).val;
        assert 2 == p.lowestCommonAncestor(new TreeNode(new Integer[]{6,2,8,0,4,7,9,null,null,3,5}),
                new TreeNode(2),
                new TreeNode(4)).val;
        assert 2 == p.lowestCommonAncestor(new TreeNode(new Integer[]{2,1}),
                new TreeNode(2),
                new TreeNode(1)).val;
    }

}
