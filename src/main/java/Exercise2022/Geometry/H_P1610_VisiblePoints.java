package Exercise2022.Geometry;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/*
  1610. Maximum Number of Visible Points
  You are given an array points, an integer angle, and your location, where location = [posx, posy] and points[i] = [xi, yi] both denote integral coordinates on the X-Y plane.

Initially, you are facing directly east from your position. You cannot move from your position, but you can rotate. In other words, posx and posy cannot be changed. Your field of view in degrees is represented by angle, determining how wide you can see from any given view direction. Let d be the amount in degrees that you rotate counterclockwise. Then, your field of view is the inclusive range of angles [d - angle/2, d + angle/2].
You can see some set of points if, for each point, the angle formed by the point, your position, and the immediate east direction from your position is in your field of view.
There can be multiple points at one coordinate. There may be points at your location, and you can always see these points regardless of your rotation. Points do not obstruct your vision to other points.
Return the maximum number of points you can see.

Example 1:

Input: points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
Output: 3
Explanation: The shaded region represents your field of view. All points can be made visible in your field of view, including [3,3] even though [2,2] is in front and in the same line of sight.
Example 2:
Input: points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
Output: 4
Explanation: All points can be made visible in your field of view, including the one at your location.
Example 3:
Output: 1
Explanation: You can only see one of the two points, as shown above.


Constraints:

1 <= points.length <= 105
points[i].length == 2
location.length == 2
0 <= angle < 360
0 <= posx, posy, xi, yi <= 100
*/
public class H_P1610_VisiblePoints {
    private List<Float> getPointsInRadiants(int[][] points, int[] location) {
        return Arrays.stream(points)
                .map(p -> new int[]{p[0] - location[0], p[1] - location[1]})
                .map(newP -> ((float) newP[0])/newP[1])
                .sorted()
                .collect(Collectors.toList());
    }

    public int visiblePoints(int[][] points, int angle, int[] location) {
        List<Float> radiants = getPointsInRadiants(points, location);
        double r = Math.toRadians(angle);

        int j = 0;
        int best = 0;
        int coverage = 0;
        for (int i = 0; i < radiants.size() && radiants.get(i) <= 2 * Math.PI; i++) {
            double cur = radiants.get(i) + r;
            while (radiants.get(j) <= cur) {
                j++;
                coverage++;
            }
            best = Math.max(best, coverage);
            coverage--;
        }
        return best;
    }

    public static void main(String[] args) {
        H_P1610_VisiblePoints p = new H_P1610_VisiblePoints();
        //int[][] points = {{2,1},{2,2},{3,3}, {1,2}, {-3,-3}, {-3, 3}, {-2, 4}};
        int[][] points = {{2,1},{2,2},{3,3}};
        assert 3 == p.visiblePoints(points, 90, new int[] {1, 1} );
    }
}
