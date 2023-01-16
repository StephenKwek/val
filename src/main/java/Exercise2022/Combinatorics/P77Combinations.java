package Exercise2022.Combinatorics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 77. Combinations
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 *
 * You may return the answer in any order.
 */
public class P77Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> soln = new ArrayList<>();
        if (k == 0) {
            soln = List.of(new ArrayList<>());
        } else if (n == k) {
                soln.add(
                        IntStream.range(1, n+1)
                                .boxed()
                                .collect(Collectors.toList())
                );
        } else {
                soln.addAll(combine(n - 1, k));
                List<List<Integer>> withN = combine(n - 1, k - 1);
                withN.forEach(combination -> combination.add(n));
                soln.addAll(withN);
        }
        return soln;
    }

    public static void main(String[] args) {
        P77Combinations p77Combinations = new P77Combinations();
        p77Combinations.combine(4, 2).forEach(c -> System.out.println(c));
    }
}
