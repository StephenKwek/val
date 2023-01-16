package Exercise2022.Applications;

import java.util.Stack;

public class H_P224_BasicCalculator {
    /*
      *  224. Basic Calculator
      *  Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
      *
      * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
      *
      *
      *
      *
      * Example 1:
      *
      * Input: s = "1 + 1"
      * Output: 2
      * Example 2:
      *
      * Input: s = " 2-1 + 2 "
      * Output: 3
      * Example 3:
      *
      * Input: s = "(1+(4+5+2)-3)+(6+8)"
      * Output: 23
      *
      *
      * Constraints:
      *
      * 1 <= s.length <= 3 * 105
      * s consists of digits, '+', '-', '(', ')', and ' '.
      * s represents a valid expression.
      * '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
      * '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
      * There will be no two consecutive operators in the input.
      * Every number and running calculation will fit in a signed 32-bit integer.
      */

        private int findMatching(String s, int i) {
            int count = 1;
            while(i < s.length()) {
                i++;
                if (s.charAt(i) == '(') {
                    count++;
                } else if (s.charAt(i) == ')') {
                    count--;
                }
                if (count == 0) {
                    return i;
                }
            }
            return -1;
        }

        private int eval(Integer x, Character operator, int y) {
            if (x == null) {
                return (operator != null && '-' == operator) ? -1 * y : y;
            } else if (operator == '-') {
                return x - y;
            } else {
                return x + y;
            }
        }
        public int calculate(String s) {
            Integer x = null;
            Character operator = null;
            s = s.replaceAll(" ", "");
            for (int i = 0; i < s.length();) {
                char c =  s.charAt(i);
                switch(c) {
                    case '-':
                    case '+':
                        operator = c;
                        i++;
                        break;
                    case '(':
                        int close = findMatching(s, i);
                        Integer num = calculate(s.substring(i + 1, close));
                        x = eval(x, operator, num);
                        i = close + 1;
                        break;
                    default: // is a number or end of input
                         num = 0;
                         while (i < s.length() && '0' <= s.charAt(i) && s.charAt(i) <= '9') {
                             num = 10 * num + (s.charAt(i) - '0');
                             i++;
                         }
                        x = eval(x, operator, num);
                }
            }

            return x;
        }

    public static void main(String[] args) {
        H_P224_BasicCalculator p = new H_P224_BasicCalculator();
        assert 11 == p.calculate("(7)-(0)+(4)");
        assert -12 == p.calculate("- (3 + (4 + 5))");
        assert 3 == p.calculate("2-1+2");
        assert 23 == p.calculate("(1+(4+5+2)-3)+(6+8)");
        assert 3 == p.calculate("2- 1 + 2");
        assert 2 == p.calculate("1 + 1");
        assert 2 == p.calculate("1 - 2  + 3");
        assert -4 == p.calculate("1 - (2  + 3)");
        assert 4 == p.calculate("55 - (20  + 31)");
        assert 3 == p.calculate("1-(   -2)");
    }
}

