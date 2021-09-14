# 프로그래머스 42898 등굣길

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42898)

## 1. 설계 로직

1. 고려해야할 사항 
   - m  x  n (100 x 100)
   - 1,000,000,007로 나눈 나머지 리턴
   - 오른쪽과 아래쪽으로만 움직여 집에서 학교까지 갈 수 있는 최단경로
2. 설계로직
   1. dfs와 메모이제이션(dp배열)을 활용하여 해결
   2. dp배열을 초기값인 -1로 세팅
   3. 0,0 좌표에서 dfs탐색을 통해 **현재 위치의 dp배열의 값 +1 ( 현재의 길로 갈 수 있는 경우의 수)** 
      1. 만약 현재 위치의 dp배열이 -1이 아니라면 **(이전에 왔던 길)** , 뒤로 돌아가면서 그 값 + 1로 dp배열 업데이트
3. 시간복잡도: O ( n x m ) 

## 2. 코드

```java
import java.util.*;

class Solution {
  	static int map[][] ,dp[][];
  	static final int div = 1000000007;
		static int[] dr = { 1, 0 };
		static int[] dc = { 0, 1 };
    public int solution(int m, int n, int[][] puddles) {
        map = new int[n][m];
        dp = new int[n][m];
        
        for(int i = 0; i < puddles.length; i++) 
            map[puddles[i][1]-1][puddles[i][0]-1] = 1;
        
        
        for(int i= 0; i < dp.length; i++) 
            Arrays.fill(dp[i],-1);    
        
        
        int answer = cal(0,0,n,m);
            
        return answer;
    }
    
    private static int cal(int r, int c,int n,int m) {
		if(r == n-1 && c == m-1) return 1;
		
		if(dp[r][c] != -1) return dp[r][c];
		dp[r][c] = 0;
		
		for(int k = 0; k < 2; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			
			if(nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] == 1) continue;
			dp[r][c] += ( cal(nr, nc, n, m) % div );
		}
		
		return dp[r][c] % div;
		
	}
}
```

## 3. 후기

- 어려운 문제...
