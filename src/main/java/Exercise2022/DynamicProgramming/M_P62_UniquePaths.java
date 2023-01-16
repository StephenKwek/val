package Exercise2022.DynamicProgramming;

import java.util.Arrays;

public class M_P62_UniquePaths {
    public int uniquePaths(int m, int n) {
        int[] paths = new int[n];
        Arrays.fill(paths, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                paths[j] = paths[j-1] + paths[j];
            }
        }
        return paths[n-1];
    }

    public static void main(String[] args) {
        M_P62_UniquePaths p = new M_P62_UniquePaths();
        assert 28 == p.uniquePaths(3,7);
        assert 3 == p.uniquePaths(3,2);

    }
}
