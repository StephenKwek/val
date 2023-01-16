package Exercise2022.String;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class M_P49_GroupAnagrams {
    private String getKey(String s) {
        return s.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(c -> 1)))
                .toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        return List.copyOf(
            Arrays.stream(strs)
                .collect(Collectors.groupingBy(s -> getKey(s)))
                .values()
        );
    }

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        M_P49_GroupAnagrams p = new M_P49_GroupAnagrams();
        //System.out.println(p.groupAnagrams(strs));
        System.out.println(p.groupAnagrams(new String[] {""}));
        //System.out.println(p.groupAnagrams(new String[] {"a"}));
    }
}
