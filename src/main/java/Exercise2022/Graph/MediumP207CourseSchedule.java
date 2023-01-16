package Exercise2022.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 207. Course Schedule
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi]
 * indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 */
public class MediumP207CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // convert to a graph and do cycle detection
        List<Integer>[] G = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            int s = prerequisites[i][0];
            if (G[s] == null) {
                G[s] = new ArrayList<>();
            }
            G[s].add(prerequisites[i][1]);
        }

        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (!seen.contains(i)) {
                // do cycle detection using DFS
                Stack<Integer> s = new Stack<>();
                s.add(i);
                while (!s.isEmpty()) {
                    Integer cur = s.pop();
                    seen.add(cur);
                    for (int neighbor : G[cur]) {
                        if (!seen.contains(neighbor)) {
                            s.add(neighbor);
                        } else {
                            if (s.contains(neighbor)) {
                                return false;
                            }
                        }
                    }

                }

            }
        }
        return true;
    }
}
