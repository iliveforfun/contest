import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @autor zhuhao
 * @Date 2020/10/26
 **/
public class Contest211 {
    public int maxLengthBetweenEqualCharacters(String s) {
        int length = s.length(), ans = -1;
        ArrayList<Character> used = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (used.contains(s.charAt(i))) {
                continue;
            }
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    ans = Math.max(j - i - 1, ans);
                    if (j == length - 1) {
                        return ans;
                    }
                }
            }
        }
        return ans;
    }

    public String findLexSmallestString(String s, int a, int b) {
        int n = s.length();
        String smallest = s;
        Queue<String> q = new LinkedList<>();
        q.offer(s);
        HashSet<String> set = new HashSet<>(q);
        while (!q.isEmpty()) {
            String cur = q.poll();
            if (smallest.compareTo(cur) > 0) {
                smallest = cur;
            }
            char[] c = cur.toCharArray();
            for (int i = 1; i < c.length; i += 2) {
                c[i] = (char) ((c[i] - '0' + a) % 10 + '0');
            }
            String add = String.valueOf(c);
            if (set.add(add)) {
                q.offer(add);
            }
            String rotate = cur.substring(n - b, n) + cur.substring(0, n - b);
            if (set.add(rotate)) {
                q.offer(rotate);
            }
        }
        return smallest;
    }

    public int bestTeamScore(int[] scores, int[] ages) {
        return 0;
    }

}
