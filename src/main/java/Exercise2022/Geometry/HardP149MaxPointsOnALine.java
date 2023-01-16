package Exercise2022.Geometry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HardP149MaxPointsOnALine {
    public int maxPoints(int[][] points) {
        Map<String, Set<Integer>> lines = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                float gradient = ((float) points[i][1] - points[j][1]) / ((float) points[i][0] - points[j][0]);
                float intercept = - gradient * points[i][0] + points[i][1];
                String key = String.format("%8.8fx+%8.8f", gradient, intercept );
                if (!lines.containsKey(key)) {
                    lines.put(key, new HashSet<>());
                }
                lines.get(key).add(i);
                lines.get(key).add(j);
            }
        }
        int max = 0;
        for (Set<Integer> s : lines.values()) {
            max = Math.max(max, s.size());
        }
        return max;
    }

    public static void main(String[] args) {
        HardP149MaxPointsOnALine hardP149MaxPointsOnALine = new HardP149MaxPointsOnALine();
        int[][] nums = {{1,1}, {3,2}, {5,3}, {4,1}, {2,3}, {1,4}};
        assert 4 == hardP149MaxPointsOnALine.maxPoints(nums);
    }
}
