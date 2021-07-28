package programmers;

import java.util.*;

public class 합승택시요금_2021KAKAO_BLIND_REC {
    static int[][] cost;
    static final int INF = 10000001;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        cost = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(cost[i], INF);
            cost[i][i] = 0;
        }

        for (int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int c = fare[2];
            cost[start][end] = cost[end][start] = Math.min(cost[start][end], c);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (cost[i][k] + cost[k][j] < cost[i][j]) cost[i][j] = cost[i][k] + cost[k][j];
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (cost[i][a] + cost[i][b] + cost[s][i] < answer)
                answer = cost[i][a] + cost[i][b] + cost[s][i];
        }
        return answer;
    }
}
