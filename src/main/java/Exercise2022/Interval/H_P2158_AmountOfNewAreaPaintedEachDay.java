package Exercise2022.Interval;

import Exercise2022.ArrayProblems.TestUtil;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class H_P2158_AmountOfNewAreaPaintedEachDay {
    public int[] amountPainted(int[][] paint) {
        List<int[]> schedule = new LinkedList<>();
        int[] soln = new int[paint.length];
        for (int i = 0; i < paint.length; i++) {
            int[] cur = paint[i];
            int len = 0;
            int j = 0;
            while (cur[1] > cur[0]) {
                for (; j < schedule.size() && schedule.get(j)[1] <= cur[0]; j++) ;
                if (j == schedule.size() || cur[1] <= schedule.get(j)[0]) {
                    schedule.add(j, cur);
                    len += cur[1] - cur[0];
                } else {
                    if (cur[0] < schedule.get(j)[0]) {
                        int[] left = new int[]{cur[0], schedule.get(j)[0]};
                        schedule.add(j, left);
                        len += left[1] - left[0];
                        j++;
                    }
                    cur = new int[]{schedule.get(j)[1], cur[1]};
                }
            }
            soln[i] = len;
        }
        return soln;
    }

    public static void main(String[] args) {
        H_P2158_AmountOfNewAreaPaintedEachDay p = new H_P2158_AmountOfNewAreaPaintedEachDay();
        TestUtil.equals(new int[] {3,3,1}, p.amountPainted(new int[][]{{1, 4}, {4, 7}, {5, 8}}));
        TestUtil.equals(new int[] {3,3,1}, p.amountPainted(new int[][]{{1, 4}, {5, 8}, {4, 7}}));
        TestUtil.equals(new int[] {4, 0}, p.amountPainted(new int[][]{{1, 5}, {2, 4}}));
    }

}
