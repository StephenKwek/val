package Exercise2022.Tree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TreeNode2 {
    public int val;
    public TreeNode2 left;
    public TreeNode2 right;
    public TreeNode2 parent;
    TreeNode2() {}
    TreeNode2(Integer val) { this.val = val; }
    TreeNode2(TreeNode2 other) {
        this.left = other.left;
        this.right = other.right;
        this.val = other.val;
    }
    TreeNode2(int val, TreeNode2 left, TreeNode2 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode2(Integer[] values) {
        this(fromArray(values));
    }

    public static TreeNode2 fromArray(Integer[] values) {
        List<TreeNode2> nodes = Arrays.asList(values)
                .stream()
                .map(v -> (v == null) ? null : new TreeNode2(v))
                .collect(Collectors.toList());

        TreeNode2 root = nodes.get(0);
        int childIndex = 1;
        for (int parentIndex = 0; parentIndex < nodes.size(); parentIndex++) {
            TreeNode2 parent = nodes.get(parentIndex);
            if (parent != null) {
                if (childIndex < nodes.size()) {
                    parent.left = nodes.get(childIndex++);
                    if (parent.left != null) {
                        parent.left.parent = parent;
                    }
                    if (childIndex < nodes.size()) {
                        parent.right = nodes.get(childIndex++);
                        if (parent.right != null) {
                            parent.right.parent = parent;
                        }
                    }
                }
            }
        }
        return root;
    }

    boolean fullNode() {
        return left != null && right != null;
    }
}

