import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @autor zhuhao
 * @Date 2020/11/2
 **/
public class BiContest38 {
    public static void main(String[] args) {
    }

    private HashMap<Integer, Integer> map = new HashMap<>();

    public int[] frequencySort(int[] nums) {
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        Integer[] array = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(array, (a, b) -> {
                    if (!map.get(a).equals(map.get(b))) {
                        return map.get(a) - map.get(b);
                    } else {
                        return b - a;
                    }
                }
        );
        int[] result = Arrays.stream(array).mapToInt(Integer::valueOf).toArray();
        return result;
    }

    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });
        int max = 0;
        for (int i = 1; i < points.length - 1; i++) {
            if (points[i][0] == points[i - 1][0]) {
                continue;
            } else {
                max = Math.max(points[i][0] - points[i - 1][0], max);
            }
        }
        return max;
    }

    public int countSubstrings(String s, String t) {
        int result = 0;
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            for (int j = i; j < t.length(); j++) {
                String substring = t.substring(i, j + 1);
                if (!map.containsKey(substring.length())) {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(substring);
                    map.put(substring.length(), list);
                } else {
                    ArrayList<String> list = map.get(substring.length());
                    list.add(substring);
                    map.put(substring.length(), list);
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String substring = s.substring(i, j + 1);
                ArrayList<String> list = map.get(substring.length());
                for (String str : list) {
                    int count = 0;
                    for (int z = 0; z < str.length(); z++) {
                        if (substring.charAt(z) != str.charAt(z)) {
                            count++;
                            if (count > 1) {
                                break;
                            }
                        }
                    }
                    if (count == 1) {
                        result++;
                    }
                }
            }
        }

        return result;
    }

    public int numWays(String[] words, String target) {
        int n = target.length();
        long mod = (long) 1e9 + 7, res[] = new long[n + 1];
        res[0] = 1;
        for (int i = 0; i < words[0].length(); i++) {
            int[] count = new int[26];
            for (String word : words) {
                count[word.charAt(i) - 'a']++;
            }
            for (int j = n - 1; j >= 0; j--) {
                res[j + 1] += res[j] * count[target.charAt(j) - 'a'] % mod;
            }
        }
        return (int) (res[n] % mod);
    }

    public int numWays_(String[] words, String target) {
        // TODO: 2020/11/4 using a more common way to finish this problem
        int mod = (int) (1e9 + 7);
        int[][] result = new int[words[0].length()][target.length()];
        int[][] count = new int[words[0].length()][26];
        for (int i = 0; i < words[0].length(); i++) {
            for (String word : words) {
                count[i][word.charAt(i) - 'a']++;
            }
        }
        for (int i = 0; i < words[0].length(); i++) {
            result[i][0] = i > 0 ? result[i - 1][0] + count[i][target.charAt(0) - 'a']
                    : count[i][target.charAt(0) - 'a'];
        }
        for (int i = 1; i < target.length(); i++) {
            for (int j = 1; j < count.length; j++) {
                result[i][j] = result[i - 1][j - 1]
                        * count[i][count[j][target.charAt(i) - 'a']] % mod
                        + result[i - 1][j];
            }
        }

        return result[-1][-1] % mod;
    }


}
