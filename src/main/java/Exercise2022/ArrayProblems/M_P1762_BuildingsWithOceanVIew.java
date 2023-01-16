package Exercise2022.ArrayProblems;

import java.util.ArrayList;
import java.util.List;

/**
 * 1762. Buildings With an Ocean View
 *
 *
 */
public class M_P1762_BuildingsWithOceanVIew {
    public int[] findBuildings(int[] heights) {
        int maxHeight = 0;
        List<Integer> soln = new ArrayList<>();
        for (int i = heights.length - 1; i >= 0; --i) {
           if (heights[i] > maxHeight)  {
               soln.add(0, i);
               maxHeight = heights[i];
           }
        }
        return soln.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    public static void main(String[] args) {
        M_P1762_BuildingsWithOceanVIew p = new M_P1762_BuildingsWithOceanVIew();
        TestUtil.equals(new int[] {0,1,2,3}, p.findBuildings(new int[] {4,3,2,1}));
        TestUtil.equals(new int[] {0,1,2,3}, p.findBuildings(new int[] {4,3,2,1}));
    }
}
