import java.util.*;

public class P81305시험장나누기 {
    static int root, nodeSize, dp[][];
    static boolean check, v[];
    static ArrayList<Integer>[] list;

    public int solution(int k, int[] num, int[][] links) {

        nodeSize = num.length;
        dp = new int[nodeSize][2];
        v = new boolean[nodeSize];

        list = new ArrayList[nodeSize];
        for (int i = 0; i < nodeSize; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < links.length; i++) {
            int left = links[i][0];
            int right = links[i][1];
            if (left != -1) {
                list[i].add(left);
                v[left] = true;
            }
            if (right != -1) {
                list[i].add(right);
                v[right] = true;
            }
        }

        // searchRootNode
        for (int i = 0; i < nodeSize; i++) {
            if (!v[i]) root = i;
        }

        // algo
        int sum = 0;
        for (int i = 0; i < nodeSize; i++) {
            sum += num[i];
        }

        int answer = 0;
        int start = sum / k;
        int end = sum;
        while (start <= end) {
            int mid = (start + end) / 2;
            check = false;
            cal(root, mid, num, k);
            if (!check) {
                end = mid - 1;
                answer = mid;
            } else start = mid + 1;
        }
        return answer;
    }

    public static void cal(int idx, int max, int[] num, int k) {
        if (check) return;

        int size = list[idx].size();
        for (int i = 0; i < size; i++) {
            cal(list[idx].get(i), max, num, k);
        }

        if (check) return;
        if (size == 0) {
            // 단말 노드
            if (num[idx] <= max) {
                dp[idx][0] = 1;
                dp[idx][1] = num[idx];
            } else check = true;
        } else if (size == 1) {
            // 왼
            int left = list[idx].get(0);
            if (num[idx] + dp[left][1] <= max) {
                dp[idx][0] = dp[left][0];
                dp[idx][1] = num[idx] + dp[left][1];
            } else {
                dp[idx][0] = dp[left][0] + 1;
                dp[idx][1] = num[idx];
            }
        } else {
            // 왼 오
            int left = list[idx].get(0);
            int right = list[idx].get(1);
            if (num[idx] + dp[left][1] + dp[right][1] <= max) {
                dp[idx][0] = dp[left][0] + dp[right][0] - 1;
                dp[idx][1] = num[idx] + dp[left][1] + dp[right][1];
            } else if (num[idx] + dp[left][1] <= max || num[idx] + dp[right][1] <= max) {
                dp[idx][0] = dp[left][0] + dp[right][0];
                if (dp[left][1] < dp[right][1]) dp[idx][1] = num[idx] + dp[left][1]; // 왼
                else dp[idx][1] = num[idx] + dp[right][1]; // 오
            } else {
                dp[idx][0] = dp[left][0] + dp[right][0] + 1;
                dp[idx][1] = num[idx];
            }
        }
        if (num[idx] > max || dp[idx][0] > k) check = true;
    }

}
 