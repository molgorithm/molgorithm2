# 프로그래머스 42898 등굣길

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42898)



## 1. 설계 로직

bfs를 이용한 경로탐색

1. 거리와 갯수가 중요, 최소 거리와 갯수를 저장할 map 생성

2. bfs 경로 탐색을 하며

   2-1. 처음 방문 한 위치라면 최소거리를 min맵에 저장, 내 갯수를 cnt맵에 저장, que에 위치 넣기

   2-2. 이미 방문 한 위치인데 저장된 거리가 내 거리보다 작다면 continue

   2-3. 거리가 같다면 내 갯수만 cnt맵에 +

3. 모든 경로를 돌고나서 학교위치에 저장된 cnt를 return

 

- 시간복잡도

  O(n^2) 맵 완탐

## 2. 코드

```java
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int [][] min = new int [n][m];
        int [][] cnt = new int [n][m];
        for(int [] i : puddles)
            min[i[1]-1][i[0]-1] = -1;
        cnt[0][0] = 1;
        min[0][0] = 1;
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(0,0));
        int [] dr = {0,0,-1,1};
        int [] dc = {1,-1,0,0};
        int length = 1;
        while(!que.isEmpty()){
            int size = que.size();
            length++;
            while(size-- >0) {
                Point now = que.poll();
                for (int i = 0; i < 4; i++) {
                    int row = now.x + dr[i];
                    int col = now.y + dc[i];
                    if (row < 0 || col < 0 || row >= n || col >= m)
                        continue;
                    if (min[row][col] !=0 && min[row][col] < length)
                        continue;
                    if(min[row][col] == 0) {
                        min[row][col] = length;
                        que.add(new Point(row,col));
                    }
                    cnt[row][col] += cnt[now.x][now.y];
                    cnt[row][col] %= 1000000007;
                }
            }
        }
        return cnt[n-1][m-1];
    }
}
```



## 3. 후기

- https://www.acmicpc.net/problem/1520

  풀고나니 비슷하진 않은데 처음 봤을땐 비슷하다 느꼈던 문제
