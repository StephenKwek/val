package Exercise2022.DataStructure;

import Exercise2022.ArrayProblems.TestUtil;

import java.util.HashMap;
import java.util.Map;

public class H_P2416_SumOfPrefixScoresOfString {
    class Node {
        public Character val = ' ';
        public int des = 0;
        public Map<Character, Node> children = new HashMap<>();

        public Node(Character c) {
            this.val = c;
        }
    }

    public Node root = new Node(' ');

    private void insert(String s) {
        Node cur = root;
        root.des = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cur.children.containsKey(c)) {
                cur = cur.children.get(c);
            } else {
                Node newNode = new Node(c);
                cur.children.put(c, newNode);
                cur = newNode;
            }
            cur.des++;
        }
    }

    private int traverse(String s) {
        int sum = 0;
        Node cur = root;
        for (int i = 0; i < s.length() && cur.children.containsKey(s.charAt(i)); i++) {
                cur = cur.children.get(s.charAt(i));
                sum += cur.des;
        }
        return sum;
    }

    public int[] sumPrefixScores(String[] words) {
        this.root = new Node(' ');
        for (String w : words) {
            insert(w);
        }
        int[] soln = new int[words.length];
        for (int i = 0; i < soln.length; i++) {
            soln[i] = traverse(words[i]);
        }
        return soln;
    }

    public static void main(String[] args) {
        H_P2416_SumOfPrefixScoresOfString p = new H_P2416_SumOfPrefixScoresOfString();
        String[] words = new String[] {"abc", "ab", "bc", "b"};
        TestUtil.equals(new int[] {5,4,3,2}, p.sumPrefixScores(words));
        TestUtil.equals(new int[] {4}, p.sumPrefixScores(new String[] {"abcd"}));
    }

}
