package Exercise2022.ArrayProblems;

import java.util.Arrays;

/**
 * 1187. Make Array Strictly Increasing
 * Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1
 * strictly increasing.
 *
 * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment
 * arr1[i] = arr2[j].
 *
 * If there is no way to make arr1 strictly increasing, return -1.
 */
public class QQQQH_P1187_MakeArrayStrictlyIncreasing {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        return -1;
    }
}
