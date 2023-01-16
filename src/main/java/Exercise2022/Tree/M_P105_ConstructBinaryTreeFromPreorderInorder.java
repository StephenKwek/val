package Exercise2022.Tree;

import java.util.stream.IntStream;

public class M_P105_ConstructBinaryTreeFromPreorderInorder {
    public TreeNode buildTree(int[] preorder, int p1, int p2,
                              int[] inorder, int i1, int i2) {
        TreeNode cur = new TreeNode(preorder[p1]);
        if (p2 <= p1) {
            return null;
        }
        if (p2 - p1 == 1) {
            return cur;
        }
        int inOrderSplit = IntStream.range(i1, i2)
                .filter(i -> inorder[i] == preorder[p1])
                .findFirst()
                .getAsInt();
        int leftTreeSize = inOrderSplit - i1;
        cur.left = buildTree(preorder, p1 + 1, p1 + 1 + leftTreeSize,
                inorder, i1, inOrderSplit);
        cur.right = buildTree(preorder, p1 + leftTreeSize + 1, p2,
                inorder, inOrderSplit + 1, i2);
        return cur;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return (preorder.length == 0)
                ? null
                : buildTree(preorder, 0, preorder.length,
                         inorder, 0, inorder.length
                    );
    }

    public static void main(String[] args) {
        M_P105_ConstructBinaryTreeFromPreorderInorder p = new M_P105_ConstructBinaryTreeFromPreorderInorder();
        H_P297_SerializeAndDeserializeBinaryTree.Codec codecs = new H_P297_SerializeAndDeserializeBinaryTree.Codec();
        TreeNode expected = new TreeNode(new Integer[]{3, 9, 20, null, null, 15, 7});
        TreeNode actual = p.buildTree(new int[] {3,9,20,15,7}, new int[] {9,3,15,20,7});
        System.out.println(codecs.serialize(actual));
        System.out.println(codecs.serialize(expected));
        assert codecs.serialize(expected).equals(codecs.serialize(actual));
    }
}
