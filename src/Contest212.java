import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import javax.swing.text.StyledEditorKit;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @autor zhuhao
 * @Date 2020/10/25
 **/

public class Contest212 {
    public static void main(String[] args) {
        System.out.println("contest for 1025's leetcode");
    }

    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int index = 0, duration = releaseTimes[0];
        for (int i = 1; i < keysPressed.length(); i++) {
            if (releaseTimes[i] - releaseTimes[i - 1] >= duration) {
                if (releaseTimes[i] - releaseTimes[i - 1] == duration) {
                    index = keysPressed.charAt(i) > keysPressed.charAt(index) ? i : index;
                } else {
                    index = i;
                }
                duration = releaseTimes[i] - releaseTimes[i - 1];
            }
        }
        return keysPressed.charAt(index);
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        Boolean[] booleans = new Boolean[l.length];
        Arrays.fill(booleans, true);
        if (nums.length != 0) {
            for (int i = 0; i < l.length; i++) {
                ArrayList<Integer> subArray = new ArrayList<>();
                if (r[i] - l[i] < 2) {
                    continue;
                }
                for (int j = l[i]; j <= r[i]; j++) {
                    subArray.add(nums[j]);
                }
                Collections.sort(subArray);
                int difference = subArray.get(1) - subArray.get(0);
                for (int m = 2; m < subArray.size(); m++) {
                    if (subArray.get(m) - subArray.get(m - 1) != difference) {
                        booleans[i] = false;
                        break;
                    }
                }
            }
        }
        return Arrays.stream(booleans).collect(Collectors.toList());
    }

    public int minimumEffortPath(int[][] heights) {
        int[] DIR = new int[]{0, 1, 0, -1, 0};
        int m = heights.length, n = heights[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[]{0, 0, 0}); // distance, row, col
        while (!minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int d = top[0], r = top[1], c = top[2];
            if (r == m - 1 && c == n - 1) {
                return d;
            }
            for (int i = 0; i < 4; i++) {
                int nr = r + DIR[i], nc = c + DIR[i + 1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int newDist = Math.max(d, Math.abs(heights[nr][nc] - heights[r][c]));
                    if (dist[nr][nc] > newDist) {
                        dist[nr][nc] = newDist;
                        minHeap.add(new int[]{dist[nr][nc], nr, nc});
                    }
                }
            }
        }

        return 0;
        /**
         * 迪杰斯特拉
         */
    }


    public int[][] matrixRankTransform(int[][] matrix) {
        int rowL = matrix.length;
        int colL = matrix[0].length;
        int[][] ans = new int[rowL][colL];

        TreeMap<Integer, List<int[]>> tm = new TreeMap<>();
        // Go thru matrix, add elements to treeMap, each key is the value at that row/col that maps to a list of x,y coordinates
        for(int i = 0; i < rowL; i++){
            for(int j = 0; j < colL; j++){
                int ele = matrix[i][j];
                if(tm.get(ele) == null) {
                    tm.put(ele, new ArrayList<>());
                }
                tm.get(ele).add(new int[] {i, j});
            }
        }

        HashMap<Integer, Integer> nextRankForRows = new HashMap<>(); // For row x, nextRankForRows[x] is the next rank
        HashMap<Integer, Integer> nextRankForCols = new HashMap<>(); // For col x, nextRankForCols[x] is the next rank
        for(int i = 0; i < rowL; i++) {
            nextRankForRows.put(i, 1);
        }
        for(int i = 0; i < colL; i++) {
            nextRankForCols.put(i, 1);
        }


        for(Integer key : tm.keySet()) {
            List<int[]> equalNums = tm.get(key); // All the nums in the matrix that are equal
            int L = equalNums.size();

            // Union Find
            int[] parent = new int[L]; // Each num is its own component, so its parent is itself
            for(int i = 0; i < L; i++) {
                parent[i] = i;
            }

            // We now compare each component with all others, and union them depending
            for(int i = 0; i < L; i++){
                for(int j = i+1; j < L; j++) {
                    int[] a = equalNums.get(i);
                    int[] b = equalNums.get(j);
                    if (a[0] == b[0] || a[1] == b[1]){
                        //If they have the same row or col, then they should be the same rank.
                        union(i, j, parent);
                    }
                }
            }

            // Once, we have our unions (where each union is a group of connected rows/cols)
            // We then place them all together into a list, so its easier to go thru
            HashMap<Integer, List<int[]>> allUnions = new HashMap<>();
            for(int i = 0; i < L; i++){
                int parentOfUnion = find(i, parent); //find the union that this elem is a part of
                if(allUnions.get(parentOfUnion) == null) {
                    allUnions.put(parentOfUnion, new ArrayList<>());
                }
                allUnions.get(parentOfUnion).add(equalNums.get(i)); // add it to the union list
            }


            for(Integer unionParent : allUnions.keySet()){
                int max = 0; // max will be the rank we assign to this unionList
                List<int[]> unionList = allUnions.get(unionParent);

                // We go through all the elems in this union, and find the maximum rank that the rows and cols have for each ele
                for(int[] unionElem : unionList) {
                    max = Math.max(max, Math.max(nextRankForRows.get(unionElem[0]), nextRankForCols.get(unionElem[1])));
                }

                // Finally, we once again go thru the union list, and set the answer for that x,y elem
                for(int[] unionElem : unionList){
                    int x = unionElem[0];
                    int y = unionElem[1];
                    ans[x][y] = max;
                    nextRankForRows.put(x, max+1);
                    nextRankForCols.put(y, max+1);
                }
            }
        }
        return ans;
    }


    void union(int a, int b, int[] parent){
        parent[find(b, parent)] = find(a, parent);
    }

    int find(int a, int[] parent){
        if(parent[a] != a) {
            parent[a] = find(parent[a], parent);
        }
        return parent[a];
    }


}
