import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @autor zhuhao
 * @Date 2020/11/15
 **/
public class Bi39 {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);
        queue.offer(5);
        queue.offer(3);
        queue.forEach(a -> System.out.println(a));
    }

    public int[] decrypt(int[] code, int k) {
        int length = code.length;
        int[] res = new int[length];
        if (k != 0) {
            for (int i = 0; i < length; i++) {
                int sum = 0;
                int threshold = k > 0 ? k : -k;
                for (int j = 1; j <= threshold; j++) {
                    int loc = k > 0 ? (i + j) % length : (i - j + length) % length;
                    sum += code[loc];
                }
                res[i] = sum;
            }

        }
        return res;
    }

    public int minimumDeletions(String s) {
        int length = s.length(), bCount = 0;
        int[] dp = new int[length + 1];
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == 'a') {
                dp[i + 1] = Math.min(dp[i] + 1, bCount);
            } else {
                dp[i + 1] = dp[i];
                bCount++;
            }
        }
        return dp[length];
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a1, a2) -> a1[0] - a2[0]);
        queue.offer(new int[]{0, 0, 0});
        HashSet<Integer> forbit = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        int maxLimit = 2000 + 2 * b;
        for (int i : forbidden) {
            forbit.add(i);
            maxLimit = Math.max(maxLimit, i + 2 * b);
        }
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int step = node[0];
            int idx = node[1];
            int dir = node[2];
            if (idx == x) {
                return step;
            }
            if (idx + a < maxLimit && !forbit.contains(idx + a) && !visited.contains(idx + a + ',' + 0)) {
                visited.add(idx + a + "," + 0);
                queue.offer(new int[]{step + 1, idx + a, 0});
            }
            if (idx - b >= 0 && !forbit.contains(idx - b) && !visited.contains(idx - b + ',' + 1) && dir != 1) {
                visited.add(idx - b + "," + 1);
                queue.offer(new int[]{step + 1, idx - b, 1});
            }
        }
        return -1;
    }



}
