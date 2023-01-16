package Exercise2022.Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class H_P297_SerializeAndDeserializeBinaryTree {
    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            StringBuffer sb = new StringBuffer();
            q.add(root);
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                sb.append(cur == null ? "#" : cur.val);
                sb.append(",");
                if (cur != null) {
                    q.add(cur.left);
                    q.add(cur.right);
                }
            }
            String s = sb.toString()
                    .replaceAll("(#,)+$", "")
                    .replaceAll(",$", "");
            return s;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {

            return new TreeNode(Arrays.stream(data.split(","))
                    .map(s -> s.equals("#") ? null : Integer.parseInt(s))
                    .toArray(Integer[]::new));
        }

    }
    public static void main(String[] args) {
        Codec codec = new Codec();
        System.out.println(codec.serialize(new TreeNode(new Integer[] {1,2,3,null,null,4,5})));
        assert "1,2,3,#,#,4,5".equals(codec.serialize(new TreeNode(new Integer[] {1,2,3,null,null,4,5})));

        E_P100_SameTree p = new E_P100_SameTree();
        TreeNode expected = new TreeNode(new Integer[]{1, 2, 3, null, null, 4, 5});
        assert p.isSameTree(expected, codec.deserialize("1,2,3,#,#,4,5"));
    }

}
