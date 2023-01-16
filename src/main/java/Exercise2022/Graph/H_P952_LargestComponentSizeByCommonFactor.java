package Exercise2022.Graph;

import Exercise2022.ArrayProblems.TestUtil;

import java.util.*;

public class H_P952_LargestComponentSizeByCommonFactor {
    public Set<Integer> findFactors(int num) {
        Set<Integer> factors = new HashSet<>();
        int i = 1;
        if (num != 1) {
            while (i <= Math.sqrt(num)) {
                    if (num % (i+1) == 0) {
                        Set<Integer> soln = findFactors(num / (i+1));
                        soln.add(i+1);
                        return soln;
                    }
                i++;
            }
            factors.add(num);
        }
        return factors;
    }

    public int largestComponentSize(int[] nums) {
        Map<Integer, BitSet> numWithFactors = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> factors = findFactors(nums[i]);
            if (factors.isEmpty()) {
                numWithFactors.put(nums[i], new BitSet(nums.length));
                numWithFactors.get(nums[i]).set(i);
            } else {
                for (int f : factors) {
                    if (!numWithFactors.containsKey(f)) {
                        numWithFactors.put(f, new BitSet(nums.length));
                    }
                    numWithFactors.get(f).set(i);
                }
            }
        }

        List<BitSet> cluster = new ArrayList<>(numWithFactors.values());
        int max = cluster.stream().map(BitSet::cardinality).max(Integer::compareTo).get();

        for (int i = cluster.size() - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (cluster.get(j) != null && cluster.get(i).intersects(cluster.get(j))) {
                    cluster.get(j).or(cluster.get(i));
                    if (cluster.get(j).cardinality() > max) {
                        max = cluster.get(j).cardinality();
                    }
                    cluster.set(i, null);
                    break;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        H_P952_LargestComponentSizeByCommonFactor p = new H_P952_LargestComponentSizeByCommonFactor();
        assert 10 == p.largestComponentSize(new int[] {98,36,5,6,39,79,78,15,16,91,69});
        assert 4 == p.largestComponentSize(new int[] {4,6,15,35});
        assert 2 == p.largestComponentSize(new int[] {20,50,9,63});
        assert 1 == p.largestComponentSize(new int[] {2,3,5,7,11,13});
        assert 8 == p.largestComponentSize(new int[] {2,3,6,7,4,12,21,39});
    }
}
