# 프로그래머스 43162 네트워크

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/43162)

## 1. 설계 로직

1. 각 컴퓨터마다 연결된 컴퓨터를 돌며 방문 체크를 한다.
2. 각 dfs 시작을 네트워크 하나로 간주하고, 카운트한다.

- 시간복잡도 : N^2

## 2. 코드

```java
import java.util.*;

class Solution {
    boolean[] v;
    public int solution(int n, int[][] computers) {
        v = new boolean[n];
        int answer = 0;
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                if(computers[i][j] == 1 && !v[i]){
                    dfs(computers, i, n);
                    answer++;
                }
            }
        }
        return answer;
    }

    public void dfs(int[][] computers, int idx, int n){
        v[idx] = true;
        for(int i=0; i<n; ++i){
            if(computers[idx][i] == 1 && !v[i]) dfs(computers, i, n);
        }
    }
}
```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
