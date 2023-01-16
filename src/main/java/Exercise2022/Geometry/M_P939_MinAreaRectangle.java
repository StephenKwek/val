package Exercise2022.Geometry;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 939. Minimum Area Rectangle
 * You are given an array of points in the X-Y plane points where points[i] = [xi, yi].
 *
 * Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes. If there
 * is not any such rectangle, return 0.
 */
public class M_P939_MinAreaRectangle {
    private int minAreaRect(int[][] points) {
        Map<Integer, List<Integer>> lines = new HashMap<>();
        for (int[] pt : points) {
            if (!lines.containsKey(pt[1])) {
                lines.put(pt[1], new ArrayList<>());
            }
            Collections.sort(lines.get(pt[1]));
        }

        int best = Integer.MAX_VALUE;
        for (Integer y1 : lines.keySet()) {
            for (Integer y2 : lines.keySet()) {
                if (y1 < y2) {
                    best = Math.min(best, bestInterval(lines.get(y1), lines.get(y2)) * (y2 - y1));
                }
            }
        }
        return best;

    }

    private int bestInterval(List<Integer> line1, List<Integer> line2) {
        int j = 0;
        List<Integer> common = new ArrayList<>();
        for (int i = 0; i < line1.size(); i++) {
            while(j < line2.size() && line2.get(j) < line1.get(i)) {
            }
            if (j < line2.size() && line1.get(i) == line2.get(j)) {
                common.add(line1.get(i));
                j++;
            }
        }
        if (common.size() > 1) {
            return IntStream.range(0, common.size() - 1)
                    .min()
                    .getAsInt();
        } else {
            return Integer.MAX_VALUE;
        }
    }
}
