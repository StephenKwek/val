package Exercise2022.Tree;

import org.apache.commons.lang3.tuple.Pair;

public class M_P230_KthSmallestElementInBST {
    public Pair<Integer,Integer> kthSmallestAndSizePair(TreeNode root, Integer k) {
        if (root == null) {
            return Pair.of(0, null);
        }
        Pair<Integer,Integer> leftSoln= kthSmallestAndSizePair(root.left, k);
        if (leftSoln.getRight() != null) {
            return leftSoln;
        }
        if (k - leftSoln.getLeft() == 1) {
            return Pair.of(-1, root.val);
        }
        Pair<Integer, Integer> rightSoln = kthSmallestAndSizePair(root.right, k - leftSoln.getLeft() - 1);
        if (rightSoln.getRight() != null) {
            return rightSoln;
        }
        return Pair.of(1 + leftSoln.getLeft() + rightSoln.getLeft(), null);
    }

    public Integer kthSmallest(TreeNode root, int k) {
        return kthSmallestAndSizePair(root, k).getRight();
    }

    public static void main(String[] args) {
        M_P230_KthSmallestElementInBST p = new M_P230_KthSmallestElementInBST();
        assert 1 == p.kthSmallest(new TreeNode(new Integer[]{3,1,4,null,2}), 1);
        assert 3 == p.kthSmallest(new TreeNode(new Integer[]{5,3,6,2,4,null,null,1}), 3);
    }
}
