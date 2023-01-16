package Exercise2022.Matrices;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 79. Word Search
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally
 * or vertically neighboring. The same letter cell may not be used more than once.
 */
public class M_P79_WordSearch {

    private static final int[][] DIRECTIONS = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};
    public boolean exist(char[][] board, String word, int[] start) {
        int n = board.length;
        int m = board[0].length;
        if (board[start[0]][start[1]] == word.charAt(0)) {
            Stack<Pair<Integer, int[]>> s = new Stack<>();
            boolean[][] seen = new boolean[n][m];
            int i = 0;
            s.push(Pair.of(0, start));
            seen[start[0]][start[1]] = true;
            while (!s.isEmpty()) {
                Pair<Integer, int[]> cur = s.pop();
                int[] cell = cur.getRight();
                int level = cur.getLeft();
                if (level == word.length() - 1) {
                    return true;
                }
                Arrays.stream(DIRECTIONS)
                        .map(dir -> new int[]{dir[0] + cell[0], dir[1] + cell[1]})
                        .filter(next -> 0 <= next[0] && next[0] < n)
                        .filter(next -> 0 <= next[1] && next[1] < m)
                        .filter(next -> !seen[next[0]][next[1]])
                        .filter(next -> board[next[0]][next[1]] == word.charAt(level + 1))
                        .map(next -> Pair.of(level + 1, next))
                        .forEach(leveledNext -> {
                            s.add(leveledNext);
                            seen[leveledNext.getRight()[0]][leveledNext.getRight()[1]] = true;
                        });
                }
        }
        return false;
    }

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(board, word, new int[]{i, j})) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        M_P79_WordSearch p = new M_P79_WordSearch();

        char[][] board = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        assert p.exist(board, word);
        word = "SEE";
        assert p.exist(board, word);

        board = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        word = "ABCB";
        assert !p.exist(board, word);
    }
}
