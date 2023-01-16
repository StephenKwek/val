package Exercise2022.Tree;

import Exercise2022.ArrayProblems.TestUtil;
import Exercise2022.Matrices.M_P79_WordSearch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 212. Word Search II
 *
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or
 * vertically neighboring. The same letter cell may not be used more than once in a word.
 */
public class H_P212_WordSearchII {
    private static M_P79_WordSearch wordSearch = new M_P79_WordSearch();
    public List<String> findWords(char[][] board, String[] words) {
        return Arrays.stream(words)
                .filter(word -> wordSearch.exist(board, word))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        char[][] board = new char[][]
                    {
                        {'o','a','a','n'},
                        {'e','t','a','e'},
                        {'i','h','k','r'},
                        {'i','f','l','v'}
                    };
        H_P212_WordSearchII p = new H_P212_WordSearchII();
        TestUtil.equals(List.of("eat", "oath"),
                p.findWords(board, new String[] {
                        "oath","pea","eat","rain"
                })
        );
    }

}
