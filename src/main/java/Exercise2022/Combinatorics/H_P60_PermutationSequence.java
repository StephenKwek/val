package Exercise2022.Combinatorics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 60. Permutation Sequence
 * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 *
 * https://leetcode.com/problems/permutation-sequence/
 */
public class H_P60_PermutationSequence {
    private static final int[] FACTORIAL; // indexed from 1.
    static {
        FACTORIAL = new int[10];
        FACTORIAL[0] = 1;
        FACTORIAL[1] = 1;
        IntStream.range(2,10)
                .boxed()
                .forEach(i -> FACTORIAL[i] = i * FACTORIAL[i-1]);
    }
    public List<Integer> getPermutation(List<Integer> items, AtomicInteger k) {
            if (k.get() == 1) {
                return items;
            }
            ArrayList<Integer> temp = new ArrayList<>(items);
            for (Integer item : items) {
                     if (FACTORIAL[items.size() - 1] <= k.get()) {
                         k.addAndGet(-1 * FACTORIAL[items.size() - 1]);
                     } else {
                         temp.remove(item);
                         List<Integer> remainder = getPermutation(temp, k);
                         remainder.add(0, item);
                         return remainder;
                     }
            };
            assert false; // should not reach here for good input
            return null;
    }

    public String getPermutation(int n, int k) {
        List<Integer> items = IntStream.range(1, n + 1)
                .boxed()
                .collect(Collectors.toList());
        return getPermutation(items, new AtomicInteger(k))
                .stream()
                .map(i -> i.toString())
                .collect(Collectors.joining(""));
    }

    public static void main(String[] args) {
        H_P60_PermutationSequence hP60PermutationSequence = new H_P60_PermutationSequence();
        List<Integer> nums = List.of(1,2,3);
        assert "213".equals(hP60PermutationSequence.getPermutation(3,3));
        assert "2314".equals(hP60PermutationSequence.getPermutation(4,9));
    }
}
