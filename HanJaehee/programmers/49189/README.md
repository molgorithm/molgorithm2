# 프로그래머스 49189 가장 먼 노드

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/49189)

## 1. 설계 로직

1. BFS사용해서 탐색.

- 시간복잡도 : N^2

## 2. 코드

```java
import java.util.*;

class Solution {

    List<Integer>[] list;
    int[] result;
    int max = 0;
    public int solution(int n, int[][] edge) {
        list = new ArrayList[n+1];
        result = new int[n+1];

        for(int i=1; i<=n; ++i){
            list[i] = new ArrayList<>();
        }

        for(int[] line : edge){
            list[line[0]].add(line[1]);
            list[line[1]].add(line[0]);
        }

        bfs();

        int answer = 0;
        for(int i=1; i<=n; ++i){
            if(max == result[i]) answer++;
            System.out.println(result[i]);
        }

        return answer;
    }

    public void bfs(){

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(1, 1));
        result[1] = 1;

        while(!q.isEmpty()){
            Point p = q.poll();

            max = Math.max(max, p.cnt);
            for(int next : list[p.now]){
                if(result[next] != 0) continue;
                result[next] = p.cnt+1;
                q.add(new Point(next, p.cnt+1));
            }
        }
    }

    public class Point{
        int now, cnt;
        Point(int now, int cnt){
            this.now = now;
            this.cnt = cnt;
        }
    }

}
```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
