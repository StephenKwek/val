package Exercise2022.Combinatorics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 89. Gray Code
 * An n-bit gray code sequence is a sequence of 2n integers where:
 *      * Every integer is in the inclusive range [0, 2^n - 1],
 *      * The first integer is 0,
 *      * An integer appears no more than once in the sequence,
 *      * The binary representation of every pair of adjacent integers differs by exactly one bit, and
 *      * The binary representation of the first and last integers differs by exactly one bit.
 * Given an integer n, return any valid n-bit gray code sequence.
 * https://leetcode.com/problems/gray-code/
 */
public class P89GrayCode {

    private List<Integer> extend(List<Integer> partial, int n)  {
        List<Integer> soln = new ArrayList<>(partial);
        Collections.reverse(partial);
        int N = n == 0? 1 : n << 1;
        partial.stream()
                .map(c -> c + N)
                .forEach(c -> soln.add(c));
        return soln;
    }
    public List<Integer> grayCode(int n) {
        if (n == 0) {
            return List.of(0);
        }
        List<Integer> partial = grayCode(n - 1);
        return extend(partial, n - 1);
    }

    public static void main(String[] args) {
        P89GrayCode p89GrayCode = new P89GrayCode();
        assert List.of(0,1).equals(p89GrayCode.grayCode(1));
        System.out.println(p89GrayCode.grayCode(2));
    }
}
