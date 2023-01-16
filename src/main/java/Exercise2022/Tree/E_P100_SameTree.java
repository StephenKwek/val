package Exercise2022.Tree;

public class E_P100_SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) || isSameTree(p.right, q.right);
        }
        return false;
    }

    public static void main(String[] args) {
        new TreeNode(new Integer[] {1,null,2});
        E_P100_SameTree tree = new E_P100_SameTree();
        assert tree.isSameTree(new TreeNode(new Integer[] {1,2,3,4,5,6,7}),
                               new TreeNode(new Integer[] {1,2,3,4,5,6,7}));
        assert !tree.isSameTree(new TreeNode(new Integer[] {1,2}),
                new TreeNode(new Integer[] {1,null,2}));
        assert !tree.isSameTree(new TreeNode(new Integer[] {1,1,2}),
                new TreeNode(new Integer[] {1,2,1}));
    }
}
