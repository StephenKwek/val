package Exercise2022.DataStructure;

import Exercise2022.ArrayProblems.TestUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class M_347_TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        return Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((o1, o2) -> Long.compare(o2.getValue(),o1.getValue()))
                .mapToInt(Map.Entry::getKey)
                .limit(k)
                .toArray();
    }

    public static void main(String[] args) {
        M_347_TopKFrequentElements p = new M_347_TopKFrequentElements();
        TestUtil.equals(new int[] {1, 2}, p.topKFrequent(new int[] {1,1,1,2,2,3}, 2));
        TestUtil.equals(new int[] {1}, p.topKFrequent(new int[] {1}, 1));
    }
}
