package Exercise2022.DataStructure;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class M_P1570_DotProductOfTwoSparseVectors {
    class SparseVector {
        private List<Pair<Integer, Integer>> vec = new ArrayList<>();

        SparseVector(int[] nums) {
            IntStream.range(0, nums.length)
                    .filter(i -> nums[i] != 0)
                    .forEach(i -> vec.add(ImmutablePair.of(i, nums[i])));
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector other) {
            int j = 0;
            int result = 0;
            for (int i = 0; i < vec.size(); i++) {
                while (j < other.vec.size() && other.vec.get(j).getKey() < vec.get(i).getKey()) {
                    j++;
                }
                if (other.vec.get(j).getKey() == vec.get(i).getKey()) {
                   result = other.vec.get(j).getKey() * vec.get(i).getKey();
                }
            }
            return result;
        }
    }
}
