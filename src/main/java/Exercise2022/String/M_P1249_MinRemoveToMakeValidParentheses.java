package Exercise2022.String;

public class M_P1249_MinRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        int count = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                if (count <= 0) {
                    continue;
                } else {
                    count--;
                }
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        M_P1249_MinRemoveToMakeValidParentheses p = new M_P1249_MinRemoveToMakeValidParentheses();
        "lee(t(c)o)de".equals(p.minRemoveToMakeValid("lee(t(c)o)de)"));

    }
}
