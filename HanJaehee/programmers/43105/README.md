# 프로그래머스 43105 정수 삼각형

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/43105)

## 1. 설계 로직

1. 이전에는 상향식으로 구현해 이번엔 하향식으로 구현했다.
2. 정삼각형 형태를 직각 삼각형으로 변경해 계산했다.
3. 현재 인덱스가 arr[Y][x] 라면, arr[Y+1][x]에는 arr[Y+1][x] 와 arr[Y+1][x] + t[Y][x] 중 큰값을 넣고 arr[Y+1][x+1]에도 동일하게 적용해 가장 밑 층의 값들 중 최대값을 추출했다.

- 시간복잡도 : N^2

## 2. 코드

```java
class Solution {
    public int solution(int[][] t) {
        int len = t[t.length-1].length;
        int[][] result = new int[len][len];
        result[0][0] = t[0][0]; // 초기값 세팅

        for(int i=0; i<len-1; ++i){
            for(int j=0; j<t[i].length; ++j){
                result[i+1][j] = Math.max(result[i][j] + t[i+1][j], result[i+1][j]);
                result[i+1][j+1] = Math.max(result[i][j] + t[i+1][j+1], result[i+1][j+1]);
            }
        }

        int max = -1;
        for(int i=0; i<len; ++i){
            max = Math.max(max, result[len-1][i]);
        }

        // print(result, len);

        return max;
    }

    public void print(int[][] result, int len){
        for(int i=0; i<len; ++i){
            for(int j=0; j<len; ++j){
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
