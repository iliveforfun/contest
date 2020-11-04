import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * @autor zhuhao
 * @Date 2020/11/4
 **/
public class Contest213 {
    public static void main(String[] args) {
        int[] heights = {4, 2, 7, 6, 9, 14, 12};
        int bricks = 5, ladders = 1;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(-1);
        queue.add(-2);
        System.out.println(queue.poll());
        /**
         * [4,2,7,6,9,14,12]
         * 5
         * 1
         */
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
        return "";
    }
}

