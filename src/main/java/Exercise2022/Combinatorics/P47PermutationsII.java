package Exercise2022.Combinatorics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 47. Permutations II
 * Given a collection of numbers, nums, that might contain duplicates,
 * return all possible unique permutations in any order.
 *
 * https://leetcode.com/problems/permutations/
 */
public class P47PermutationsII {
    public List<List<Integer>> permute(List<Integer> items) {
        List<List<Integer>> soln = new ArrayList<>();
        if (items.isEmpty()) {
            List<Integer> emptyList = new ArrayList<>();
            soln.add(emptyList);
        } else {
            ArrayList<Integer> temp = new ArrayList<>(items);
            items.stream()
                 .distinct()
                 .forEach(item -> {
                     temp.remove(item);
                     for (List<Integer> partial : permute(temp)) {
                         List<Integer> extended = new LinkedList<>(partial);
                         extended.add(0, item);
                         soln.add(extended);
                     }
                     temp.add(item);
                 });
        }
        return soln;
    }

    public static void main(String[] args) {
        P47PermutationsII p47PermutationsII = new P47PermutationsII();
        List<Integer> nums = List.of(1,2,3);
        p47PermutationsII.permute(nums)
                .forEach(permutaiton -> System.out.println(permutaiton));
    }
}
