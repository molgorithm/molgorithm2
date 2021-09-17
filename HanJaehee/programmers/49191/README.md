# 프로그래머스 49191 순위

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/49191)

## 1. 설계 로직

1. 선수가 100명이하 이기 때문에 플루이드 와샬 사용.

- 시간복잡도 : N^3

## 2. 코드

```java
import java.util.*;

class Solution {

    public int solution(int n, int[][] results) {

        int INF = 100000;
        int answer = 0;
        int[][] map = new int[n+1][n+1];

        for(int[] tmp : map){
            Arrays.fill(tmp, INF);
        }

        for(int[] result : results){
            map[result[0]][result[1]] = 1;
        }

        for(int k=1; k<=n; ++k){
            for(int i=1; i<=n; ++i){
                for(int j=1; j<=n; ++j){
                    if(map[i][j] > map[i][k] + map[k][j]){
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        for(int i = 1; i < map.length; i++){
            boolean flag = true;
            for(int j = 1; j < map[i].length; j++) {
                if(i == j) continue;
                if(map[i][j] == INF && map[j][i] == INF) {
                    flag = false; break;
                }
            }
            if(flag) answer++;
        }
        return answer;

    }
}

```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
