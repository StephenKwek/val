package Exercise2022.Combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_P2184_NumberOfWaysToBuildSturdyBrickWall {
    private static final int N = 1000000007;
    private ArrayList<ArrayList<Integer>> waysRow(int width, int[] bricks) {
        ArrayList<ArrayList<Integer>> frontier = new ArrayList<>();
        Arrays.stream(bricks)
                .filter(b -> b <= width)
                .forEach(b -> frontier.add(new ArrayList<>(List.of(b))));
        ArrayList<ArrayList<Integer>> completed = new ArrayList<>();
        while (!frontier.isEmpty()) {
            for (ArrayList<Integer> conf : List.copyOf(frontier)) {
                int len = conf.stream().reduce(0, Integer::sum);
                if (len == width) {
                    frontier.remove(conf);
                    completed.add(conf);
                } else if (len > width){
                    frontier.remove(conf);
                } else {
                    frontier.remove(conf);
                    for (int b : bricks) {
                        if (b + len <= width) {
                            ArrayList<Integer> newConf = new ArrayList<>(conf);
                            newConf.add(b);
                            frontier.add(newConf);
                        }
                    }
                }
            }
        }
        return completed;
    }

    private boolean canStack(List<Integer> u, List<Integer> v) {
        int i = 0;
        int j = 0;
        int lenU = 0;
        int lenV = 0;
        while (i < u.size() && j < v.size()) {
            if (lenU == lenV && lenU != 0 && (i != u.size() && j != v.size())) {
                return false;
            }
            if (lenU < lenV) {
                lenU += u.get(i);
                i++;
            } else {
                lenV += v.get(j);
                j++;
            }
        }
        return true;
    }
    private ArrayList<Integer>[] constructG(ArrayList<ArrayList<Integer>> ways) {
        ArrayList<Integer>[] g = new ArrayList[ways.size()];
        for (int i = 0; i < ways.size(); i++) {
            g[i] = new ArrayList<>();
            for (int j = 0; j < ways.size(); j++) {
                if (i != j) {
                    if (canStack(ways.get(i), ways.get(j)))  {
                        g[i].add(j);
                    }
                }
            }
        }
        return g;
    }
    public int buildWall(int height, int width, int[] bricks) {
        if (height == 0) {
            return 0;
        }
        ArrayList<ArrayList<Integer>> ways = waysRow(width, bricks);
        ArrayList<Integer>[] g = constructG(ways);
        int[] count = new int[ways.size()];
        Arrays.fill(count, 1);
        final int[] finalCount = count;
        for (int i = 1; i < height; i++) {
            int[] next = Arrays.copyOf(count, count.length);
            for (int j = 0; j < g.length; j++) {
                if (i != j) {
                    next[j] = (next[j] * g[j].stream()
                            .map(u -> finalCount[u])
                            .reduce(0, Integer::sum)) % N;
                }
            }
            count = next;
        }
        return Arrays.stream(count).sum() % N;

    }

    public static void main(String[] args) {
        M_P2184_NumberOfWaysToBuildSturdyBrickWall p = new M_P2184_NumberOfWaysToBuildSturdyBrickWall();
        int[] bricks = new int[] {1,2};
        System.out.println(p.buildWall(2,3,bricks));
        assert 2 == p.buildWall(2,3,bricks);
    }
}
