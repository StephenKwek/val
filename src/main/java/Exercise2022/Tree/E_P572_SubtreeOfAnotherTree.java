package Exercise2022.Tree;

import Exercise2022.ArrayProblems.TestUtil;

import java.util.LinkedList;
import java.util.Queue;

public class E_P572_SubtreeOfAnotherTree {
    private static final E_P100_SameTree checker = new E_P100_SameTree();
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur != null) {
                if (cur.val == subRoot.val && checker.isSameTree(cur, subRoot)) {
                    return true;
                }
                q.add(cur.left);
                q.add(cur.right);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        E_P572_SubtreeOfAnotherTree p = new E_P572_SubtreeOfAnotherTree();
        assert p.isSubtree(
                new TreeNode(new Integer[] {3,4,5,1,2}),
                new TreeNode(new Integer[] {4,1,2})
        );
    }
}
