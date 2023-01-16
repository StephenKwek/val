package Exercise2022.Tree;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 1340. Jump Game V
 *
 * Given an array of integers arr and an integer d. In one step you can jump from index i to index:
 *
 * i + x where: i + x < arr.length and  0 < x <= d.
 * i - x where: i - x >= 0 and  0 < x <= d.
 * In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).
 *
 * You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.
 *
 * Notice that you can not jump outside of the array at any time.
 */
public class H_P1340_JumpGameV {
    public int maxJumps(int[] arr, int d) {
        List<Integer> sortedIndex = IntStream.range(0, arr.length)
                .boxed()
                .map(i -> ImmutablePair.of(arr[i], i))
                .sorted()
                .map(Pair::getValue)
                .collect(Collectors.toList());

        int[] maxJumps = new int[arr.length];
        int best = -1;
        for (int i = 0; i < sortedIndex.size(); i++) {
            int actualId = sortedIndex.get(i);
            Optional<Integer> maxPreJumps = IntStream.range(Math.max(0, actualId - d),
                                                            Math.min(sortedIndex.size(), actualId + d + 1))
                    .boxed()
                    .filter(j -> j != actualId)
                    .filter(j -> arr[j] < arr[actualId])
                    .map(j -> maxJumps[sortedIndex.get(j)])
                    .max(Integer::compareTo);
            maxJumps[actualId] = 1 + (maxPreJumps.orElse(0));
            best = Math.max(maxJumps[actualId], best);
        }
        return best;
    }

    public static void main(String[] args) {
        H_P1340_JumpGameV p = new H_P1340_JumpGameV();
        assert 4 == p.maxJumps(new int[] {6,4,14,6,8,13,9,7,10,6,12}, 2);
        assert 1 == p.maxJumps(new int[] {3,3,3,3,3,3,3}, 3);
    }
}
