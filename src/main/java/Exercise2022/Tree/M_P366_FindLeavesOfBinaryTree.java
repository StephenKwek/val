package Exercise2022.Tree;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class M_P366_FindLeavesOfBinaryTree {
    private boolean isChild(TreeNode node) {
        return node.left == null && node.right == null;
    }
    private ArrayList<TreeNode> gatherLeaves(TreeNode node) {
        if (node == null) {
            return new ArrayList<>();
        } else if (isChild(node)) {
            ArrayList<TreeNode> soln = new ArrayList<>();
            soln.add(node);
            return soln;
        }

        ArrayList<TreeNode> left = gatherLeaves(node.left);
        ArrayList<TreeNode> right = gatherLeaves(node.right);
        if (left.size() == 1 && node.left == left.get(0)) {
            node.left = null;
        }
        if (right.size() == 1 && node.right == right.get(0)) {
            node.right = null;
        }

        left.addAll(right);
        return left;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> soln = new ArrayList<>();
        while (!isChild(root)) {
            soln.add(gatherLeaves(root).stream()
                    .map(node -> node.val)
                    .collect(Collectors.toList())
            );
        }
        soln.add(List.of(root.val));
        return soln;
    }

    public static void main(String[] args) {
        M_P366_FindLeavesOfBinaryTree p = new M_P366_FindLeavesOfBinaryTree();
        TreeNode root = new TreeNode(new Integer[] {1,2,3,4,5});
        System.out.println(p.findLeaves(root));
    }
}
