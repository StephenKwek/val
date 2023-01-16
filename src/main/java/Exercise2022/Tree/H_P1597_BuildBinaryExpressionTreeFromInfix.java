package Exercise2022.Tree;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 1597. Build Binary Expression Tree From Infix Expression
 *
 * A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary
 * expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (numbers),
 * and internal nodes (nodes with 2 children) correspond to the operators '+' (addition), '-' (subtraction),
 * '*' (multiplication), and '/' (division).
 *
 * For each internal node with operator o, the infix expression it represents is (A o B), where A is the expression the
 * left subtree represents and B is the expression the right subtree represents.
 *
 * You are given a string s, an infix expression containing operands, the operators described above, and parentheses '('
 * and ')'.
 *
 * Return any valid binary expression tree, whose in-order traversal reproduces s after omitting the parenthesis from it.
 *
 * Please note that order of operations applies in s. That is, expressions in parentheses are evaluated first, and
 * multiplication and division happen before addition and subtraction.
 *
 * Operands must also appear in the same order in both s and the in-order traversal of the tree.
 */
public class H_P1597_BuildBinaryExpressionTreeFromInfix {
  class Node {
      char val;
      Node left;
      Node right;
      Node() {this.val = ' ';}
      Node(char val) { this.val = val; }
      Node(char val, Node left, Node right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

  private Node subTree(Stack<Node> operands, Stack<Node> operators) {
      while (!operators.isEmpty()) {
          Node operator = operators.pop();
          operator.right = operands.pop();
          operator.left = operands.pop();
          operands.push(operator);
      }
      return operands.get(0);
    }

    public Node expTree(String s) {
        Stack<Node> operands = new Stack<>();
        Stack<Node> operators = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Node cur = new Node(c);
            if (c == '(') {
                int matchRight = s.indexOf(')', i);
                operands.push(expTree(s.substring(i+1, matchRight)));
                i = matchRight;
            } else if ('0' <= c && c <= '9') {
                operands.push(cur);
            } else if (c == '+' || c == '-') {
                cur.left = subTree(operands, operators);
                cur.right = expTree(s.substring(i + 1));
                return cur;
            } else {
                operators.push(cur);
            }
        }
        return operands.size() == 1 ? operands.get(0) : subTree(operands, operators);
    }

    public static String dump(Node root) {
        Queue<Node> q = new LinkedList<>();
        List<Character> visited = new LinkedList<>();
        q.add(root);
        int nonNull = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            nonNull += cur != null ? 1 : 0;
            if (cur == null) {
                if (nonNull > 0) {
                    visited.add(null);
                }
            } else {
                visited.add(cur.val);
                nonNull--;
                q.add(cur.left == null ? null : cur.left);
                q.add(cur.right == null ? null : cur.right);
            }
        }
        return visited.stream()
                .map(c -> c == null ? "null" : String.valueOf(c))
                .collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
        H_P1597_BuildBinaryExpressionTreeFromInfix p = new H_P1597_BuildBinaryExpressionTreeFromInfix();
        System.out.println(dump(p.expTree("3*4-2*5")));
        System.out.println(dump(p.expTree("1+2+3+4+5")));
        assert "-,*,*,3,4,2,5".equals(dump(p.expTree("3*4-2*5")));
        assert "-,2,+,/,1,3,*,5,2".equals(dump(p.expTree("2-3/(5*2)+1")));
        assert "+,1,+,2,+,3,+,4,5".equals(dump(p.expTree("1+2+3+4+5")));
    }
}
