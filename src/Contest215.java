import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiFunction;

/**
 * @autor zhuhao
 * @Date 2020/11/21
 **/
public class Contest215 {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
    }

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int length = word1.length();
        int [] count1 = new int [26];
        int [] count2 = new int [26];
        for (char ch : word1.toCharArray()) {
            count1[ch-'a'] += 1;
        }
        for (char ch: word2.toCharArray()) {
            count2[ch-'a'] += 1;
        }
        // check if there is any discrepancy in the minimum and the maximum vakue
        for (int i=0; i<26; i++) {
            if (count1[i] == count2[i]) {
                continue;
            }
            if (count1[i] == 0 || count2[i] == 0) {
                return false;
            }
        }
        Arrays.sort(count1); // since the array size is 26 and it will not change, here the sorting is 26log26
        Arrays.sort(count2);
        for (int i=0; i<26; i++) {
            if (count1[i] != count2[i]) {
                return false;
            }
        }
        return true;
    }

    class OrderedStream {

        int count = 0;
        String[] arr;


        public OrderedStream(int n) {
            arr = new String[n];
        }

        public List<String> insert(int id, String value) {
            arr[id - 1] = value;
            ArrayList<String> list = new ArrayList<>();
            while (count < arr.length) {
                if (arr[count] != null) {
                    list.add(arr[count++]);
                } else {
                    break;
                }
            }
            return list;
        }
    }
}
