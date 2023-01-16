package Exercise2022.Tree;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class H_P2246_LongestPathWithDifferentAdjacentChars {
    public Pair<Integer, Integer> longestPath(TreeNode3 root) {
        if (root == null) {
            return ImmutablePair.of(0,0);
        }


        List<Integer> paths2GoodChild = new ArrayList<>();
        int longestPath = 1;
        for (TreeNode3 child : root.children) {
            Pair<Integer, Integer> sub = longestPath(child);
            if (child.val != root.val) {
                paths2GoodChild.add(sub.getRight());
                longestPath = Math.max(longestPath, sub.getLeft());
            }
        }
        Collections.sort(paths2GoodChild);
        int path2Root = paths2GoodChild.isEmpty()
                        ? 1
                        : paths2GoodChild.get(0) + 1;
        int longestPathThroughRoot = paths2GoodChild.isEmpty()
                        ? 1
                        : paths2GoodChild.size() == 1
                            ? 1 + paths2GoodChild.get(0)
                            : 1 + paths2GoodChild.get(0) + paths2GoodChild.get(1);
        longestPath = Math.max(longestPathThroughRoot, longestPath);

        return ImmutablePair.of(longestPath, path2Root);
    }

    public int longestPath(int[] parent, String s) {
        TreeNode3 root = TreeNode3.fromParentArray(parent, s);
        return longestPath(root).getLeft();
    }

    public static void main(String[] args) {
        H_P2246_LongestPathWithDifferentAdjacentChars p = new H_P2246_LongestPathWithDifferentAdjacentChars();
        assert 3 == p.longestPath(new int[] {-1, 0, 0, 1, 1, 2}, "abacde");
        assert 3 == p.longestPath(new int[] {-1, 0, 0, 0}, "abbc");
    }
}
