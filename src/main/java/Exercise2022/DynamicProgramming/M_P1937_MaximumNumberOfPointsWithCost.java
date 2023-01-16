package Exercise2022.DynamicProgramming;

import java.util.Arrays;

/**
 * 1937. Maximum Number of Points with Cost
 * You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.
 *
 * To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.
 *
 * However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
 *
 * Return the maximum number of points you can achieve.
 * !!! NOT TESTED !!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
public class M_P1937_MaximumNumberOfPointsWithCost {
    public long maxPoints(int[][] points) {
        int n = points.length;
        int m = points[0].length;
        long[][] bestL = new long[n][m];
        long[][] bestR= new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    bestL[i][j] = points[i][j];
                    bestR[i][m - 1 -j] = points[i][m - 1 - j];
                } else {
                    bestL[i][j] = Math.max(points[i][j], bestL[i][j-1] - 1);
                    bestR[i][j] = Math.max(points[i][m-j], bestR[i][m-1-j] - 1);
                }
            }
        }

        long opt[] = new long[m];
        for (int j = 0; j < m; j++) {
            opt[j] = Math.max(bestL[0][j],bestR[0][j]);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                opt[j] = opt[j] + Math.max(bestL[0][j], bestR[0][j]);
            }
        }
        return Arrays.stream(opt).max().getAsLong();
    }

}
