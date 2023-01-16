package Exercise2022.String;

import java.util.Map;
import java.util.Stack;

public class E_P20_ValidParentheses {
    private static final Map<Character, Character> MATCH = Map.of(
                '{', '}',
                '(', ')',
                '[', ']'
            );
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (MATCH.containsKey(c)) {
                stack.push(c);
            } else if (stack.isEmpty() || MATCH.get(stack.pop()) != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        E_P20_ValidParentheses p = new E_P20_ValidParentheses();
        assert p.isValid("()");
        assert p.isValid("(){}[]");
        assert p.isValid("{(){}[()]}");
        assert !p.isValid("{({)}[()]}");
    }
}
