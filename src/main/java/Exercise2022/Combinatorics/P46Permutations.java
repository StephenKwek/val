package Exercise2022.Combinatorics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 46. Permutations
 * Given an array nums of distinct integers, return all the possible
 * permutations. You can return the answer in any order.
 *
 * https://leetcode.com/problems/permutations/
 */
public class P46Permutations {
    public List<List<Integer>> permute(List<Integer> items) {
        List<List<Integer>> soln = new ArrayList<>();
        if (items.isEmpty()) {
            List<Integer> emptyList = new ArrayList<>();
            soln.add(emptyList);
        } else {
            ArrayList<Integer> temp = new ArrayList<>(items);
            for (Integer item : items) {
                temp.remove(item);
                for (List<Integer> partial : permute(temp)) {
                    List<Integer> extended = new LinkedList<>(partial);
                    extended.add(0, item);
                    soln.add(extended);
                }
                temp.add(item);
            }
        }
        return soln;
    }

    public static void main(String[] args) {
        P46Permutations p46Permutations = new P46Permutations();
        List<Integer> nums = List.of(1,2,3,4);
        p46Permutations.permute(nums)
                .forEach(permutaiton -> System.out.println(permutaiton));
    }
}
