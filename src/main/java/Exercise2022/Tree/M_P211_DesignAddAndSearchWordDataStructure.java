package Exercise2022.Tree;

import java.util.HashMap;
import java.util.Map;

public class M_P211_DesignAddAndSearchWordDataStructure {
    static class WordDictionary {
        private class Node {
            public Character val;
            public Map<Character, Node> children = new HashMap<>();
            public boolean isTerminal;

            Node(Character val) {
                this.val = val;
                this.isTerminal = false;
            }
        }

        Node root;
        public WordDictionary() {
            root = new Node(null);
        }

        public void addWord(String word) {
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

        public boolean search(Node node, String word) {
            if (word.length() == 0) {
                return true;
            }
            if (node == null) {
                return false;
            }
            if (word.charAt(0) == '.') {
                return node.children.values().stream()
                        .anyMatch(child -> search(child, word.substring(1)));
            } else {
                return (search(node.children.getOrDefault(word.charAt(0), null), word.substring(1)));
            }
        }

        public boolean search(String word) {
            return search(root, word);
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        assert !wordDictionary.search("pad"); // return False
        assert wordDictionary.search("bad"); // return True
        assert wordDictionary.search(".ad"); // return True
        assert wordDictionary.search("b.."); // return True
        assert 1 != 2;
    }


}
