package Exercise2022.Combinatorics;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *   301. Remove Invalid Parentheses
 *   Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 *
 *   Return all the possible results. You may return the answer in any order.
 *   https://leetcode.com/problems/remove-invalid-parentheses/
 */
public class H_P301_RemoveInvalidParentheses {
    private static Function<String, Integer> computeMismatched = s -> s.chars()
                                                                .map(c -> c == '(' ? 1 : -1)
                                                                .sum();


    public List<String> removeInvalidParentheses(String s) {
        int extra = computeMismatched.apply(s);
        Map<Integer, Set<String>> subSoln = new HashMap<>();
        Set<String> seed = new HashSet<>();
        seed.add("");
        subSoln.put(0, seed);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                for (int k : subSoln.keySet()) {
                    if (!subSoln.containsKey(k+1)) {
                        subSoln.put(k+1, new HashSet<>());
                    }
                    for (String a : subSoln.get(k))  {
                        subSoln.get(k+1).add(a + c);
                    }
                }
            } else if (c == ')') {
                for (int k : subSoln.keySet()) {
                    if (k != 0) {
                        for (String a : subSoln.get(k)) {
                            subSoln.get(k - 1).add(a + c);
                        }
                    }
                }
            } else {
                for (int k : subSoln.keySet()) {
                    subSoln.put(k, subSoln.get(k).stream()
                            .map(a -> a + c)
                            .collect(Collectors.toCollection(HashSet::new)));
                }

            }
        }
        return subSoln.get(0).stream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        H_P301_RemoveInvalidParentheses p = new H_P301_RemoveInvalidParentheses();
        assert p.removeInvalidParentheses("()())()").contains("(())()");
        assert p.removeInvalidParentheses("()())()").contains("()()()");

        assert p.removeInvalidParentheses("(a)())()").contains("(a())()");
        assert p.removeInvalidParentheses("(a)())()").contains("(a)()()");

        assert p.removeInvalidParentheses(")(").contains("");
    }
}
