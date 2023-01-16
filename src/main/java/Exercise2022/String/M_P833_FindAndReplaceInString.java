package Exercise2022.String;

/**
 * 833. Find And Replace in String
 * You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations
 * are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
 *
 * To complete the ith replacement operation:
 *
 * Check if the substring sources[i] occurs at index indices[i] in the original string s.
 * If it does not occur, do nothing.
 * Otherwise if it does occur, replace that substring with targets[i].
 * For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this
 * replacement will be "eeecd".
 *
 * All replacement operations must occur simultaneously, meaning the replacement operations should not affect the
 * indexing of each other. The testcases will be generated such that the replacements will not overlap.
 *
 * For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because
 * the "ab" and "bc" replacements overlap.
 * Return the resulting string after performing all replacement operations on s.
 *
 * A substring is a contiguous sequence of characters in a string.
 * NOT TESTED !!!!!!!!!!!!!!!!!!!!!!
 */
public class M_P833_FindAndReplaceInString {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        String[] replacement = new String[indices.length];
        for (int i = 0; i < indices.length; i++) {
            String temp  = s.substring(i);
            for (String w : sources) {
                if (temp.startsWith(w)) {
                    replacement[i] = w;
                    break;
                }
				/*
				if (w.length() <= Math.min(s.length() - i, ….) {
					match = true;
					for (int j = i < j + w.length(); j++) {
						match = false;
					}
					if (match) {
						replacement[i] = w;
						break;
					}
				}
				*/
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < indices.length; i++) {
            if (replacement == null) {
                sb.append(s.substring(indices[i], (i < indices.length - 1 ? indices[i+1] : s.length())));
            } else {
                String w = replacement[i];
                sb.append(w);
                sb.append(s.substring(indices[i] + w.length(), (i < indices.length - 1 ? indices[i + 1]: s.length())));
            }
        }
        return s.toString();
    }

}
