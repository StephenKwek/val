package Exercise2022.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TreeNode3 {
    public char val;
    public List<TreeNode3> children;
    TreeNode3() {}
    TreeNode3(char val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
    TreeNode3(TreeNode3 other) {
        this.children = other.children; this.val = other.val;
    }

    void addChild(TreeNode3 child) {
        children.add(child);
    }

    public static TreeNode3 fromParentArray(int[] parents, String s) {
        TreeNode3[] nodes = new TreeNode3[parents.length];
        for (int i = 0; i < parents.length; i++) {
            nodes[i] = new TreeNode3(s.charAt(i));
            if (i != 0) {
                TreeNode3 parent = nodes[parents[i]];
                parent.addChild(nodes[i]);
            }
        }
        return nodes[0];
    }
}

