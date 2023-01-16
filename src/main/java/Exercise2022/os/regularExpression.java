package Exercise2022.os;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regularExpression {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("[\\+|\\-]?\\d+\\.?\\d+");
        Matcher matcher = p.matcher("+123.345");
        assert matcher.find();

        matcher = p.matcher("123.345");
        assert matcher.find();

        matcher = p.matcher("-123.345");
        assert matcher.find();

        matcher = p.matcher("-123.f45");
        assert matcher.find();

    }
}
