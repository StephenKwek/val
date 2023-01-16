package Exercise2022.Applications;

import java.util.stream.Stream;


/**
 * 65. Valid Number
 * A valid number can be split up into these components (in order):
 *
 * A decimal number or an integer.
 * (Optional) An 'e' or 'E', followed by an integer.
 * A decimal number can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One of the following formats:
 * One or more digits, followed by a dot '.'.
 * One or more digits, followed by a dot '.', followed by one or more digits.
 * A dot '.', followed by one or more digits.
 * An integer can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One or more digits.
 * For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].
 *
 * Given a string s, return true if s is a valid number.
 */
public class H_P65_ValidNumber {
    enum State {
        Start,
        PrecisionSign,
        ExponentSign,
        Dot,
        Expo,
        PrecisionInteger,
        PrecisionFraction,
        Scale
    }
    public boolean isNumber(String s) {
        State state = State.Start;
        boolean hasNumberComponent = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch(c) {
                case '+':
                case '-': if (state == State.Start) {
                              state = State.PrecisionSign;
                          } else if (state == State.Expo) {
                              state = State.ExponentSign;
                          } else {
                              return false;
                          }
                          break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': if (state == State.Start || state == State.PrecisionSign || state == State.PrecisionInteger) {
                                state = State.PrecisionInteger;
                                hasNumberComponent = true;
                            } else if (state == State.Expo || state == State.ExponentSign || state == State.Scale) {
                                state = State.Scale;
                            } else if (state == State.Dot || state == State.PrecisionFraction) {
                                hasNumberComponent = true;
                                state = State.PrecisionFraction;
                            } else {
                                return false;
                            }
                            break;
                case 'E':
                case 'e': if (state == State.PrecisionInteger || state == State.PrecisionFraction || state == State.Dot) {
                                state = State.Expo;
                            } else {
                                return false;
                            }
                           break;
                case '.' : if (state == State.PrecisionInteger || state == State.Start || state == State.PrecisionSign) {
                                state = State.Dot;
                            } else {
                                return false;
                            }
                            break;
                default:
                            return false;

            }
        }
        return hasNumberComponent
                && (state == State.PrecisionInteger || state == State.PrecisionFraction || state == State.Scale || state == State.Dot);
    }

    public static void main(String[] args) {
        H_P65_ValidNumber p = new H_P65_ValidNumber();
        System.out.println(p.isNumber("46.e3"));
        Stream.of("46.e3", "2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789")
                .filter(n -> !p.isNumber(n))
                .forEach(n -> System.out.println(n));

        assert Stream.of("46.e3", "2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789")
                .allMatch(p::isNumber);
        Stream.of("+E3", "3.5e+3.5e+3.5", "abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53")
                .filter(p::isNumber)
                .forEach(n -> System.out.println(n));
        ;
        assert Stream.of("3.5e+3.5e+3.5", "abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53")
                .noneMatch(p::isNumber);
    }
}
