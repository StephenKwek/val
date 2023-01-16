package Exercise2022.Grid;

/**
 * 2018  Check if Word Can Be Placed In Crossword
 * You are given an m x n matrix board, representing the current state of a crossword puzzle. The crossword
 * contains lowercase English letters (from solved words), ' ' to represent any empty cells, and '#' to represent
 * any blocked cells.
 *
 * A word can be placed horizontally (left to right or right to left) or vertically (top to bottom or bottom to top)
 * in the board if:
 *
 * It does not occupy a cell containing the character '#'.
 * The cell each letter is placed in must either be ' ' (empty) or match the letter already on the board.
 * There must not be any empty cells ' ' or other lowercase letters directly left or right of the word if the word
 * was placed horizontally.
 * There must not be any empty cells ' ' or other lowercase letters directly above or below the word if the word was
 * placed vertically.
 * Given a string word, return true if word can be placed in board, or false otherwise.
 */
public class M_P2018_CheckIfWordCanBePlacedInCrossword {
    public boolean placeWordInCrossword(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (( j == 0 || board[i][j-1] == '#') && (j + word.length() - 1) < n) {
                    int k = 0;
                    for (; k < word.length(); k++) {
                        if (board[i][j + k] != word.charAt(k) && board[i][j + k] != ' ') {
                            break;
                        }
                    }
                    if (k == word.length() && (j + k == n || board[i][j + k] == '#')) {
                        return true;
                    }
                }
                if (( i == 0 || board[i-1][j] == '#') && i + word.length() - 1 < m) {
                    int k = 0;
                    for (; k < word.length(); k++) {
                        if (board[i+k][j] != word.charAt(k) && board[i+k][j] != ' ') {
                            break;
                        }
                    }
                    if ((k == word.length()) && (i + k == m || board[i + k][j] == '#')) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        M_P2018_CheckIfWordCanBePlacedInCrossword p = new M_P2018_CheckIfWordCanBePlacedInCrossword();
        char[][] board = new char[][] {{'#', ' ', '#'}, {' ', ' ', '#'},{'#', 'c', ' '}};
        assert p.placeWordInCrossword(board, "abc");

        board = new char[][] {{' ', '#', 'a'}, {' ', '#', 'c'}, {' ', '#', 'a'}};
        assert !p.placeWordInCrossword(board, "ac");
    }
}
