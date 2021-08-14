# 프로그래머스 72413 합승 택시 요금

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/72413)

## 1. 설계 로직

1. 고려해야할 사항 
   - 지점갯수 (200), fares (200 * 199 / 2) , 요금 100,000 이하.
   - 최단경로 탐색 알고리즘
2. 설계로직
   1. 지점갯수가 200이하 이므로 플로이드-와샬(dp)로 해결할 수 있음.
      - 플로이드-와샬은 다익스트라와 다르게 모든 시작점에서의 최소 거리를 알려주기 때문에 편리함.
   2. 플로이드-와샬로 모든 정점에서 각각의 최소거리를 구함.
   3. 'x 정점에서 A까지의 최소값' + x 정점에서 B까지의 최소값' + '시작점에서 x까지 최소값' -> 정답이 됨.
3. 시간복잡도: O ( n^3 ) 

## 2. 코드

```java
import java.util.*;

class Solution {
    static int[][] cost;
    static final int INF = 10000001;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        cost = new int[n+1][n+1];
        
        for(int i = 1; i <= n; i++) {
            Arrays.fill(cost[i],INF);
            cost[i][i] = 0;
        }
        
        for(int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int c = fare[2];
            cost[start][end] = cost[end][start] = Math.min(cost[start][end], c);
        }
        
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j<=n; j++) {
                    if(cost[i][k] + cost[k][j] < cost[i][j]) cost[i][j] = cost[i][k] + cost[k][j];
                }
            }
        }
      
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <=n; i++) {
            if(cost[i][a] + cost[i][b] + cost[s][i] < answer) 
                answer = cost[i][a] + cost[i][b] + cost[s][i];
        }
        
        return answer;
    }
}

```

## 3. 후기

- 정점의 갯수가 작기 때문에 쉽게 접근할 수 있었습니다.
- 다익스트라로 구하려면 아마 시간안에 풀기 힘들거같음.
