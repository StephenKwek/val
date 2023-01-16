package Exercise2022.String;

import java.util.*;

/**
 * 792. Number of Matching Subsequences
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none)
 * deleted without changing the relative order of the remaining characters.
 * NOT TESTED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public class M_P792_NumberOfMatchingSubsequence {
    static class TrieNode {
        Character val;
        Map<Character, TrieNode> children = new HashMap<>();
        boolean terminal = false;
        int match = 0;

        TrieNode(Character val) {
            this.val = val;
        }

        void insert(TrieNode root, String s) {
            TrieNode cur = root;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (cur.children.containsKey(c)) {
                    cur = cur.children.get(c);
                } else {
                    TrieNode next = new TrieNode(c);
                    cur.children.put(c, next);
                    cur = next;
                }
                if (i == s.length() - 1) {
                    cur.terminal = true;
                }
            }
        }

        public int numMatchingSubseq(String s, String[] words) {
            TrieNode root = trie(words);
            Set<TrieNode> count = new HashSet<>();
            count.add(root);
            Set<TrieNode> frontier = new HashSet<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                for (TrieNode cur: count) {
                    if (cur.children.containsKey(c)) {
                        TrieNode child = cur.children.get(c);
                        frontier.add(child);
                        child.match += cur.match;
                    }
                }
            }
            return count.stream()
                    .filter(u -> u.terminal)
                    .map(u -> u.match).reduce(0, Integer::sum);
        }
    }

    public static TrieNode trie(String[] words) {
        TrieNode  root = new TrieNode(null);
        for (String w: words) {
            root.insert(root, w);
        }
        return root;
    }

}
