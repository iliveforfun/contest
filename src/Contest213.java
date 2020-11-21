import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @autor zhuhao
 * @Date 2020/11/4
 **/
public class Contest213 {
    public static void main(String[] args) {
        System.out.println((int) 3.6);
    }

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
                if (i == arr.length || i == arr.length - 1) {
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
        int result[] = new int[5];
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                for (int j = 0; j < 5; j++) {
                    result[j] = 1;
                }
            } else {
                for (int j = 0; j < 5; j++) {
                    for (int l = j + 1; l < 5; l++) {
                        result[j] += result[l];
                    }
                }
            }
        }
        return Arrays.stream(result).sum();
    }

    public int furthestBuilding(int[] A, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < A.length - 1; i++) {
            int d = A[i + 1] - A[i];
            if (d > 0) {
                pq.add(d);
            }
            if (pq.size() > ladders) {
                bricks -= pq.poll();
            }
            if (bricks < 0) {
                return i;
            }
        }
        return A.length - 1;
    }

    public String kthSmallestPath(int[] destination, int k) {
        StringBuilder s = new StringBuilder();
        int row = destination[0], col = destination[1];
        int[][] dp = new int[row + col][row];
        dp[0][0] = 1;
        for (int i = 1; i < row + col; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= i && j < col; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        while (row > 0 && col > 0) {
            int low = dp[row + col - 1][col - 1];
            if(k>low){
                s.append('V');
                k-=low;
                row--;
            }else{
                col--;
                s.append('H');
            }
        }
        if (row == 0) {
            for (int i = 0; i < col; i++) {
                s.append('V');
            }
        }
        if (col == 0) {
            for (int i = 0; i < row; i++) {
                s.append('H');
            }
        }
        return s.toString();
    }

}
