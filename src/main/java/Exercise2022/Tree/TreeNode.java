package Exercise2022.Tree;

import Exercise2022.Interval.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode() {}
    TreeNode(Integer val) { this.val = val; }
    TreeNode(TreeNode other) {
        this.left = other.left;
        this.right = other.right;
        this.val = other.val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(Integer[] values) {
        this(fromArray(values));
    }



    private static TreeNode fromArray(Integer[] values) {
        List<TreeNode> nodes = Arrays.asList(values)
                .stream()
                .map(v -> (v == null) ? null : new TreeNode(v))
                .collect(Collectors.toList());

        TreeNode root = nodes.get(0);
        int childIndex = 1;
        for (int parentIndex = 0; parentIndex < nodes.size(); parentIndex++) {
            TreeNode parent = nodes.get(parentIndex);
            if (parent != null) {
                if (childIndex < nodes.size()) {
                    parent.left = nodes.get(childIndex++);
                    if (childIndex < nodes.size()) {
                        parent.right = nodes.get(childIndex++);
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

