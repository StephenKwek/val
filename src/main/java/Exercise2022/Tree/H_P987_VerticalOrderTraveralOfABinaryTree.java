package Exercise2022.Tree;

import Exercise2022.ArrayProblems.TestUtil;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.IntStream;

public class H_P987_VerticalOrderTraveralOfABinaryTree {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> positive = new ArrayList<>();
        List<List<Integer>> negative = new ArrayList<>();

        Stack<Pair<Integer,TreeNode>> stack = new Stack<>();
        stack.add(ImmutablePair.of(0,root));
        while (!stack.isEmpty()) {
            Pair<Integer, TreeNode> cur = stack.pop();
            int curLevel = cur.getLeft();
            TreeNode curNode = cur.getRight();
            if (curLevel > 0) {
                if (curLevel >= positive.size()) {
                    positive.add(new ArrayList<>());
                }
                positive.get(curLevel - 1).add(curNode.val);
            } else {
                int index = -1 * curLevel;
                if (index >= negative.size()) {
                    negative.add(new ArrayList<>());
                }
                negative.get(index).add(curNode.val);
            }
            if (curNode.left != null) {
                stack.add(ImmutablePair.of(curLevel - 1, curNode.left));
            }
            if (curNode.right != null) {
                stack.add(ImmutablePair.of(curLevel + 1, curNode.right));
            }
        }
        Collections.reverse(negative);
        negative.addAll(positive);
        return negative;
    }

    public static void main(String[] args) {
        H_P987_VerticalOrderTraveralOfABinaryTree p = new H_P987_VerticalOrderTraveralOfABinaryTree();
        List<List<Integer>> expected = List.of(
                List.of(4),
                List.of(2),
                List.of(1, 5, 6),
                List.of(3),
                List.of(7)
        );
        TreeNode root = new TreeNode(new Integer[] {1,2,3,4,6,5,7});
        List<List<Integer>> actual = p.verticalTraversal(root);
        assert actual.size() == expected.size();
        IntStream.range(0, actual.size()).forEach(i -> TestUtil.equals2(actual.get(i), expected.get(i)));

        List<List<Integer>> expected2 = List.of(
                List.of(9),
                List.of(3,15),
                List.of(20),
                List.of(7)
        );
        root = new TreeNode(new Integer[] {3,9,20,null,null,15,7});
        List<List<Integer>> actual2 = p.verticalTraversal(root);
        assert actual2.size() == expected2.size();
        IntStream.range(0, actual2.size()).forEach(i -> TestUtil.equals2(actual2.get(i), expected2.get(i)));

    }

}
