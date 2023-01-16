package Exercise2022.Tree;

import Exercise2022.ArrayProblems.TestUtil;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class M_P102_BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return List.of();
        }
        List<List<Integer>> levels = new ArrayList<>();
        Queue<Pair<Integer, TreeNode>> q = new LinkedList<>();
        q.add(Pair.of(0, root));
        while (!q.isEmpty()) {
            Pair<Integer, TreeNode> cur = q.poll();
            if (levels.size() < cur.getLeft() + 1) {
                levels.add(new ArrayList<>());
            }
            levels.get(cur.getLeft()).add(cur.getRight().val);
            if (cur.getRight().left != null) {
                q.add(Pair.of(cur.getLeft() + 1, cur.getRight().left));
            }
            if (cur.getRight().right != null) {
                q.add(Pair.of(cur.getLeft() + 1, cur.getRight().right));
            }
        }
        return levels;
    }

    public static void main(String[] args) {
        M_P102_BinaryTreeLevelOrderTraversal p = new M_P102_BinaryTreeLevelOrderTraversal();
        assert "[[3], [9, 20], [15, 7]]".equals(p.levelOrder(new TreeNode(new Integer[] {3,9,20,null,null,15,7})).toString());
        assert "[[1]]".equals(p.levelOrder(new TreeNode(new Integer[] {1})).toString());
        assert "[]".equals(p.levelOrder(null).toString());
    }
}
