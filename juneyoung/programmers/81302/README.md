# 프로그래머스 81302 거리두기 확인하기

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/81302)

## 1. 설계 로직

1. 고려해야할 사항 
   - places 배열 (5 x 5) -> 완탐

   - 맨해튼 거리 2
2. 설계로직
   1. 너비 우선 탐색을 이용하여 depth가 2 인 너비우선 탐색 -> 맨해튼 거리가 2
   2. '배열크기를 벗어나는경우' or '이미 지나왔던 위치' or '파티션' 이면 패스
   3. 너비 우선 탐색중 'P' 를 발견했다면 거리두기가 지켜지지 않음.
3. 시간복잡도: O ( 5 x 5 x bfs탐색(13)) 이하 

## 2. 코드

```java
import java.util.*;
class Solution {
    static int[] dr = {1, -1, 0, 0}; 
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] v;
    static Queue<Point> q = new LinkedList<>();
    static char[][] map = new char[5][];
    static class Point {
        int r, c, depth;
        
        public Point(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        Arrays.fill(answer, 1);
        for(int i = 0; i < places.length; i++) {
            for(int j = 0; j < places[i].length; j++) {
                    map[j] = places[i][j].toCharArray();
            }   
            if(cal()) answer[i] = 0;
        }
        return answer;
     }
    
    public static boolean check(int row, int col) {
        q.clear();
        v = new boolean[5][5];
        v[row][col] = true;
        q.add(new Point(row,col,0));
        Point p = null;
        while(!q.isEmpty()) {
            p = q.poll();
            
            if(p.depth == 3) continue;
            if(map[p.r][p.c] == 'P' && p.depth != 0) return true;
            
            for(int k = 0; k < 4; k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                
                if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5 || v[nr][nc] || map[nr][nc] == 'X') continue;
                v[nr][nc] = true;
                q.add(new Point(nr,nc,p.depth + 1));
            }
        }
        
        return false;
    }
    
    public static boolean cal() {
        for(int row = 0; row < 5; row++) {
            for(int col = 0; col < 5; col++) {
                if(map[row][col] == 'P' && check(row,col)) return true;
            } 
        }
        return false;
    }

}
```

## 3. 후기

- 완탐문제라 쉬웠음.
- dfs보다 bfs가 유리하다고 판단. -> dfs는 상대적으로 중간에 빠져 나오기 어려움.
