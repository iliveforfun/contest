/**
 * @autor zhuhao
 * @Date 2020/11/4
 **/
public class Contest213 {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        for (int[] piece : pieces) {
            int i = 0;
            if (piece.length == 1) {
                for (; i < arr.length; i++) {
                    if (arr[i] == piece[0]) {
                        break;
                    }
                }
                if (i == arr.length) {
                    return false;
                }
            } else {
                for (; i < arr.length; i++) {
                    if (arr[i] == piece[0]) {
                        break;
                    }
                }
                if (i == arr.length || i==arr.length-1) {
                    return false;
                } else {
                    int loc = 1;
                    for (; loc < piece.length; loc++) {
                        if (arr[++i] != piece[loc]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public int countVowelStrings(int n) {
        int result = 0;

        return result;
    }
}
