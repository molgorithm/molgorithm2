# 프로그래머스 67259 경주로 건설

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67259)

## 1. 설계 로직

1. 고려해야할 사항 
   - board 배열 (25 x 25) 
   - 중복해서 방문 가능하기 때문에 방문배열에 신경써야 함.
   - 이전에 방향을 기억해야 함.
2. 설계로직
   1. 메모이제이션을 이용해야 하기 때문에 방문배열을 int로 만듦.
   2. ( 0,0 ) 부터 탐색 시작.
   3. 이동
      1. 이전의 방향과 현재의 방향이 같다면 + 100
      2. 이전의 방향과 현재의 방향이 다르다면 ( 코너 ) + 600
      3. 방문배열로 현재 위치까지의 값보다 작은 경우만 이동해야 함.
3. 시간복잡도: O ( n^2 x a )

## 2. 코드

```java
import java.util.*;

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;
    static Queue<Point> q = new LinkedList<>();
    static class Point {
        int r, c, cnt, dir;
        
        public Point(int r, int c, int cnt, int dir) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }
        
    }
    public int solution(int[][] board) {        
        cal(board);
        return answer;
    }
    
    public static void cal(int[][] board) {
        int N = board.length;
        int[][] v = new int[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(v[i],Integer.MAX_VALUE);
        }
        v[0][0] = 0;
        q.add(new Point(0,0,0,-1));
        
        Point p = null;
        while(!q.isEmpty()) {
            p = q.poll();
            if(p.r == N-1 && p.c == N-1) {
                answer = Math.min(answer,p.cnt);
            }
            
            for(int k = 0; k < 4; k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                
                if(nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 1) continue;
                if(p.dir != -1 && p.dir != k) {
                    if(v[nr][nc] < p.cnt + 600) continue;
                    v[nr][nc] = p.cnt + 600;
                    q.add(new Point(nr,nc,p.cnt + 600,k));
                }else{
                   if(v[nr][nc] < p.cnt + 100) continue; 
                    v[nr][nc] = p.cnt+100;
                    q.add(new Point(nr,nc,p.cnt + 100,k));
                }
                
            }
        }
    }
}
```

## 3. 후기

- 우선순위큐가 왜 안되는지 모르겠음. 이유좀 알려주실분!
