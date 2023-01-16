package Exercise2022.String;

/**
 * 316. Remove Duplicate Letters
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is
 * the smallest in lexicographical order
 * Lexicographically Smaller
 * A string a is lexicographically smaller than a string b if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
 * If the first min(a.length, b.length) characters do not differ, then the shorter string is the lexicographically smaller one.
 *
 *  among all possible results.
 */
public class M_P316_RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Boolean[] hit = new Boolean[26];
        for (int i = 0; i < s.length(); i++) {
            hit[s.charAt(i) - 'a'] = true;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 26; i++) {
            if (hit[i]) {
                sb.append(i + 'a');
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        M_P316_RemoveDuplicateLetters p = new M_P316_RemoveDuplicateLetters();
        assert "abc".equals(p.removeDuplicateLetters("bcabc"));
    }
}
