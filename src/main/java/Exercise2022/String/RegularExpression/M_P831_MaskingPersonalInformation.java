package Exercise2022.String.RegularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class M_P831_MaskingPersonalInformation {
    public String maskPII(String s) {
        Pattern emailP = Pattern.compile("^[\\d|\\w](.+)[\\w|\\d]\\@([\\d|\\w|\\.]+)$");
        Pattern phoneNum = Pattern.compile("^()[\\d{4}]$");
        Matcher matcher = emailP.matcher(s);
        if (matcher.matches()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                System.out.println(i + "->" + matcher.group(i));
            }
            return s.replace(matcher.group(1), "*".repeat(matcher.group(1).length()));
        } else {
            return s;
        }
    }

    public static void main(String[] args) {
        M_P831_MaskingPersonalInformation p = new M_P831_MaskingPersonalInformation();
        System.out.println(p.maskPII("sskwek@"));
        System.out.println(p.maskPII("sskwek@gmail.com"));
        System.out.println(p.maskPII("sskwek@gmail.com"));
        System.out.println(p.maskPII("sskwek2@gmail.com"));
    }
}
