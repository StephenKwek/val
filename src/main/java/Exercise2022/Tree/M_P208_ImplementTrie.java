package Exercise2022.Tree;

import java.util.HashMap;
import java.util.Map;

public class M_P208_ImplementTrie {
    static class Trie {
        private class Node {
            public Character val;
            public Map<Character, Node> children = new HashMap<>();
            public boolean isTerminal;

            Node(Character val) {
                this.val = val;
                this.isTerminal = false;
            }
        }

        private Node root;
        public Trie() {
            root = new Node(null);
        }

        public void insert(String word) {
            Node cur = root;
            int i = 0;
            for (; i < word.length() && cur.children.containsKey(word.charAt(i)); i++) {
                cur = cur.children.get(word.charAt(i));
            };
            for (;i < word.length(); i++) {
                Node next = new Node(word.charAt(i));
                cur.children.put(word.charAt(i), next);
                cur = next;
            }
            cur.isTerminal = true;
        }

        public boolean search(String word) {
            Node cur = root;
            for (int i = 0; i < word.length() && cur.children.containsKey(word.charAt(i)); i++) {
                cur = cur.children.get(word.charAt(i));
            }
            return cur.isTerminal;
        }

        public boolean startsWith(String prefix) {
            Node cur = root;
            int i = 0;
            for (; i < prefix.length() && cur.children.containsKey(prefix.charAt(i)); i++) {
                cur = cur.children.get(prefix.charAt(i));
            }
            return i == prefix.length();
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        assert trie.search("apple");
        assert !trie.search("app");
        assert trie.startsWith("app");
        trie.insert("app");
        assert trie.search("app");
    }
}
