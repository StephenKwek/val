package Exercise2022.Tree;

import java.util.*;

public class M_P1650_LowestCommonAncestorOfABinaryTreeIII {
    private List<TreeNode2> getPath(TreeNode2 p) {
        List<TreeNode2> path = new ArrayList<>();
        TreeNode2 cur = p;
        while (cur != null) {
            path.add(cur);
            cur = cur.parent;
        }
        Collections.reverse(path);
        return path;
    }
    public TreeNode2 lowestCommonAncestor(TreeNode2 p, TreeNode2 q) {
        List<TreeNode2> pathP = getPath(p);
        List<TreeNode2> pathQ = getPath(q);
        int i = 0;
        for (; i < Math.min(pathP.size(), pathQ.size()) && pathQ.get(i) == pathP.get(i); i++);
        return pathP.get(i-1);
    }

    private static TreeNode2 findNode(TreeNode2 root, Integer val) {
        Queue<TreeNode2> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode2 cur = q.poll();
            if (cur.val == val) {
                return cur;
            }
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        M_P1650_LowestCommonAncestorOfABinaryTreeIII p = new M_P1650_LowestCommonAncestorOfABinaryTreeIII();
        TreeNode2 root = TreeNode2.fromArray(new Integer[] {3,5,1,6,2,0,8,null,null,7,4});
        TreeNode2 u = findNode(root, 5);
        TreeNode2 v = findNode(root, 1);
        assert 3 == p.lowestCommonAncestor(u, v).val;

        root = TreeNode2.fromArray(new Integer[] {3,5,1,6,2,0,8,null,null,7,4});
        u = findNode(root, 5);
        v = findNode(root, 4);
        assert 5 == p.lowestCommonAncestor(u, v).val;

        root = TreeNode2.fromArray(new Integer[] {1, 2});
        u = findNode(root, 1);
        v = findNode(root, 2);
        assert 1 == p.lowestCommonAncestor(u, v).val;
    }
}
