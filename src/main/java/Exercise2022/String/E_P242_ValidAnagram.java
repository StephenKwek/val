package Exercise2022.String;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class E_P242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> frequencyS = s.chars()
                        .mapToObj(c -> (char)c)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(c -> 1)));
        Map<Character, Integer> frequencyT = t.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(c -> 1)));
        System.out.println("S:" + frequencyS.toString());
        System.out.println("T:" + frequencyT.toString());
        return frequencyS.size() == frequencyT.size()
                && frequencyS.entrySet().stream().allMatch(
                        entry -> frequencyT.containsKey(entry.getKey())
                                && entry.getValue() == frequencyT.get(entry.getKey()));
    }

    public static void main(String[] args) {
        E_P242_ValidAnagram p = new E_P242_ValidAnagram();
        assert p.isAnagram("anagram", "nagaram");
        assert !p.isAnagram("rat", "cat");
    }
}
