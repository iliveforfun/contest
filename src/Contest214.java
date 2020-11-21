import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @autor zhuhao
 * @Date 2020/11/8
 **/
public class Contest214 {
    public static void main(String[] args) {
        String s = "abcd";
        new Contest214().minDeletions(s);
    }

    public int getMaximumGenerated(int n) {
        int result = 0;
        if (n >= 1) {
            int[] array = new int[n + 1];
            array[0] = 0;
            for (int i = 1; i <= n; i++) {
                array[i] = i == 1 ? 1 :
                        (i % 2 == 0 ? array[i / 2] : array[i / 2] + array[i / 2 + 1]);
            }
            result = Arrays.stream(array).max().getAsInt();
        }
        return result;
    }

    public int minDeletions(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (Integer value : map.values()) {
            queue.add(value);
        }
        while (queue.size() > 0) {
            int mostFrequent = queue.remove();
            if (queue.size() == 0) {
                return result;
            }
            if (mostFrequent == queue.peek()) {
                if (mostFrequent > 1) {
                    queue.add(mostFrequent - 1);
                }
                result++;
            }
        }
        return result;
    }

    public int maxProfit(int[] inventory, int orders) {
        long res = 0;
        int mod = (int) (1e9 + 7);
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : inventory) {
            queue.add(i);
        }
        int cur = queue.remove();
        int count = 1;
        while (orders > 0) {
            int next = queue.isEmpty() ? 0 : queue.peek();
            if ((cur - next) * count <= orders) {
                long tmp = (cur + next + 1) * (cur - next) * count / 2 % mod;
                res = (res + tmp) % mod;
                orders -= (cur - next) * count;
            } else {
                next = cur - orders / count;
                long tmp = (next + 1 + cur) * (cur - next) * count / 2 % mod;
                res = (res + tmp) % mod;
                res = (res + next * (orders % count)) % mod;
                orders = 0;
            }
            count++;
            if (!queue.isEmpty()) {
                cur = queue.remove();
            }
        }
        return (int) res;
    }


}
