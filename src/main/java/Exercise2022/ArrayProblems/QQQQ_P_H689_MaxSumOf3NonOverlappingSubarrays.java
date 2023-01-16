package Exercise2022.ArrayProblems;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QQQQ_P_H689_MaxSumOf3NonOverlappingSubarrays {
    public Integer[] maxSumOfThreeSubarrays(int[] nums, int k) {
        List<Pair<Integer, Integer>> range =
                IntStream.range(0, nums.length - k + 1)
                .boxed()
                .map(i -> ImmutablePair.of(i,
                        IntStream.range(0, k)
                                .map(j -> nums[i + j])
                                .sum()
                ))
                .sorted(Collections.reverseOrder())
                .limit(3 * k)
                .sorted(Comparator.comparingInt(Pair::getLeft))
                .collect(Collectors.toCollection(ArrayList::new));

        List<List<Integer>> back = IntStream.range(0, range.size())
                .boxed()
                .map(i -> Arrays.asList(i))
                .collect(Collectors.toList());
        List<Integer> pre = range.stream()
                .map(pair -> pair.getRight())
                .collect(Collectors.toList());
        int bestEnd = -1;
        for (int l = 1; l < 3; l++) {
            List<Integer> cur =  new ArrayList<>();
            int bestSum = -1;
            for (int i = l; i < range.size(); i++) {
                cur.add(cur.get(i-1));
                int bestItem = i - 1;
                for (int j = l - 1; j >= 0; j--) {
                    if (i - j >= 0 && range.get(i).getKey() - range.get(i - j).getKey() >= k) {
                        if (cur.get(i) == null || (range.get(i).getValue() + pre.get(i-j)) > cur.get(i)) {
                            cur.set(i, range.get(i).getValue() + pre.get(i-j));
                            bestItem = i - j;
                        }
                        break;
                    }
                }
                if (cur.get(i) > bestSum) {
                    bestSum = cur.get(i);
                    bestEnd = i;
                }
                back.get(i).add(bestItem);
            }
            pre = cur;
        }
        Integer[] ints = (Integer[]) back.get(bestEnd).toArray();
        return ints;

        /*
        for (int l = 1; l < 3; l++) {
            select[l] = new ArrayList<>();
            prevSelect[l] = new ArrayList<>();
            for (int i = l; i < range.size(); i++) {
                Pair<Integer, Integer> cur = range.get(i);
                Pair<Integer, Integer> best = (i != l && i - 1 < select[l].size()) ? select[l].get(i-1) : null;
                for (int j = l - 1; j >= 0; j--) {
                    Pair<Integer, Integer> candidate = select[l-1].get(j);
                    if (candidate != null && cur.getLeft() - candidate.getLeft() >= k) {
                        if (best == null || best.getRight() < candidate.getRight() + range.get(i).getRight()) {
                            best = ImmutablePair.of(i, candidate.getRight() + range.get(i).getRight());
                            prevSelect[l].add(candidate.getLeft());
                        } else {
                            prevSelect[l].add(prevSelect[l].get(i-1));
                        }
                        break;
                    }
                }
                select[l].add(best);
            }
        }

        Integer bestStart = null;
        for (int i = 0; i < select[2].size(); i++) {
            if (select[2].get(i) != null && (bestStart == null || select[2].get(bestStart).getRight() < select[2].get(i).getRight())) {
                bestStart = i;
            }
        }
        if (bestStart == null) {
            return null;
        }
        int[] soln = new int[3];
        for (int l = 2; l >= 0; l--) {
            soln[l] =range.get(bestStart).getLeft();
            if (l != 0) {
                bestStart = prevSelect[l].get(bestStart);
            }
        }
        return soln;
         */
    }

    public static void main(String[] args) {
        QQQQ_P_H689_MaxSumOf3NonOverlappingSubarrays p = new QQQQ_P_H689_MaxSumOf3NonOverlappingSubarrays();
        TestUtil.equals(new Integer[] {0,3,5}, p.maxSumOfThreeSubarrays(new int[] {1,2,1,2,6,7,5,1,1}, 2));
    }
}
