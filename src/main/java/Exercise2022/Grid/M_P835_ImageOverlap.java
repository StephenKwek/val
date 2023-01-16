package Exercise2022.Grid;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class M_P835_ImageOverlap {
    private int numberOnes(int[][] img1) {
        int numOnes = 0;
        for (int i = 0; i < img1.length; i++) {
            for (int j = 0; j < img1[0].length; j++) {
                if (img1[i][j] == 1) {
                    numOnes++;
                }
            }
        }
        return numOnes;
    }

    private int[][] move(int [][] img1, int[] dir) {
        int[][] result = new int[img1.length][img1[0].length];
        for (int i = 0; i < img1.length; i++) {
            int newI = i + dir[0];
            if (0 <= newI && newI < img1.length) {
                for (int j = 0; j < img1[0].length; j++) {
                    int newJ = j + dir[1];
                    if (0 <= newJ && newJ < img1[0].length) {
                        result[newI][newJ] = img1[i][j];
                    }
                }
            }
        }
        return result;
    }

    private static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private int overlap(int[][] img1, int[][] img2) {
        int overlap = 0;
        for (int i = 0; i < img1.length; i++) {
            for (int j = 0; j < img1[0].length; j++) {
                if (img1[i][j] == img2[i][j] && img2[i][j] == 1) {
                    overlap++;
                }
            }
        }
        return overlap;
    }

    private String mat2String(int[][] img) {
        StringBuffer bf = new StringBuffer();
        Arrays.stream(img).forEach(row -> Arrays.stream(row).forEach(e -> bf.append(e)));
        return bf.toString();
    }

    public int largestOverlap(int[][] img1, int[][] img2) {
        int onesTarget = numberOnes(img2);
        Set<String> seen = new HashSet<>();
        Stack<int[][]> s = new Stack<>();
        s.push(img1);
        seen.add(mat2String(img1));
        int maxOverlap = overlap(img1, img2);
        while (!s.isEmpty()) {
            int[][] cur = s.pop();
            for (int[] dir: DIR) {
                int[][] next = move(cur, dir);
                if (!seen.contains(mat2String(next)) && numberOnes(next) >= onesTarget) {
                    s.push(next);
                    seen.add(mat2String(next));
                    maxOverlap = Math.max(maxOverlap, overlap(next, img2));
                }
            }
        }
        return maxOverlap;
    }

    public static void main(String[] args) {
        M_P835_ImageOverlap p = new M_P835_ImageOverlap();
        assert 3 == p.largestOverlap(new int[][] {{1,1,0},{0,1,0},{0,1,0}},
                                     new int[][] {{0,0,0},{0,1,1},{0,0,1}});
    }
}
