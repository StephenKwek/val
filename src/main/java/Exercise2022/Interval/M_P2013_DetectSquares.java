package Exercise2022.Interval;

import java.util.TreeMap;

/**
 * 2013. Detect Squares
 * You are given a stream of points on the X-Y plane. Design an algorithm that:
 *
 * Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as
 * different points.
 * Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query point form an axis-aligned square with positive area.
 * An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.
 *
 * Implement the DetectSquares class:
 *
 * DetectSquares() Initializes the object with an empty data structure.
 * void add(int[] point) Adds a new point point = [x, y] to the data structure.
 * int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
 */
public class M_P2013_DetectSquares {
    static class DetectSquares {
        TreeMap<Integer, Integer>[] horizontals = new TreeMap[1000];
        TreeMap<Integer, Integer>[] verticals = new TreeMap[1000];
        public DetectSquares() {
            for (int i = 0; i < 1000; i++) {
                horizontals[i] = new TreeMap<>();
                verticals[i] = new TreeMap<>();
            }
        }

        public void add(int[] point) {
            verticals[point[0]].put(point[1], 1 + verticals[point[0]].getOrDefault(point[1], 0) );
            horizontals[point[1]].put(point[0], 1 + horizontals[point[1]].getOrDefault(point[0], 0) );
        }

        public int count(int[] point) {
            int soln = 0;
            if (!horizontals[point[1]].isEmpty()) {
                TreeMap<Integer, Integer> h0 = horizontals[point[1]];
                TreeMap<Integer, Integer> vertical = verticals[point[0]];
                for (Integer x : h0.keySet()) {
                    for (Integer y : vertical.keySet()) {
                        if (x != point[0] && y != point[1]) {
                            TreeMap<Integer, Integer> h1 = horizontals[y];
                            int numH0Int = h0.get(y) * (h0.get(point[0]) + 1);
                            int numH1Int = h1.get(y) * (h1.get(point[0]) + 1);
                            soln += numH1Int * numH0Int;
                        }
                    }
                }
            }
            return soln;
        }

        public static void main(String[] args) {
            DetectSquares ds = new DetectSquares();
            ds.add(new int[]{3,10});
            ds.add(new int[]{11,2});
            ds.add(new int[]{3,2});
            System.out.println(ds.count(new int[]{11,10}));
            System.out.println(ds.count(new int[]{14,8}));
            ds.add(new int[]{11,2});
            ds.count(new int[]{11,10});
        }
    }
}
