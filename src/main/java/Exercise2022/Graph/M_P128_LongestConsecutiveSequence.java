package Exercise2022.Graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class M_P128_LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> longestPrefixPath = new HashMap<>();
        Map<Integer, Integer> longestSuffixPath = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            Integer prefix = longestPrefixPath.getOrDefault(cur - 1, 0);
            Integer suffix = longestSuffixPath.getOrDefault(cur + 1, 0);
            longestPrefixPath.put(cur, 1 + prefix);
            longestSuffixPath.put(cur, 1 + suffix);

            longestSuffixPath.put(cur - prefix, 1 + prefix + suffix);
            longestPrefixPath.put(cur + suffix, 1 + prefix + suffix);
        }
        return longestPrefixPath.values().stream().max(Integer::compareTo).get();
    }

    public static void main(String[] args) {
        M_P128_LongestConsecutiveSequence p = new M_P128_LongestConsecutiveSequence();
        System.out.println(p.longestConsecutive(new int[] {100,4,200,1,3,2}));
        assert 4 == p.longestConsecutive(new int[] {100,4,200,1,3,2});
        assert 9 == p.longestConsecutive(new int[] {0,3,7,2,5,8,4,6,0,1});
    }
}
