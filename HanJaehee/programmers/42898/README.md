# 프로그래머스 42898 크레인 인형뽑기 게임

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42898)

## 1. 설계 로직

1. 초등학교 수학시간에 배웠던 최단경로 구하는 방법을 그대로 사용했다.
2. 원래는 집을 기준으로 X축 벽, Y축 벽에 1을 채워넣는 방법이지만, 연산 편의를 위해 집에만 1을 넣어두고 시작
3. 2중 for문 중 한 지점에서 위의 값, 왼쪽 값을 더한다. 그 지점의 값이 -1(웅덩이)라면, 0을 삽입하고 다음 연산으로
4. 최종적으로 학교 위치인 m-1, n-1의 값을 출력한다.
5. 각 연산에 % 연산을 하지 않으면 효율성 오류가 난다..

- 시간복잡도 : N^2

## 2. 코드

```java
class Solution {
  public int solution(int m, int n, int[][] puddles) {
    int[][] map = new int[n][m];

    for (int[] puddle : puddles)
      map[puddle[1] - 1][puddle[0] - 1] = -1;

    map[0][0] = 1;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {

        if(map[i][j] == -1) {
          map[i][j] = 0;
          continue;
        }

        if(i != 0) map[i][j] += map[i - 1][j] % 1000000007; // 이거 나머지 처리 안하면 효율성 못뚫음;
        if(j != 0) map[i][j] += map[i][j - 1] % 1000000007;
      }
    }

    return map[n - 1][m - 1] % 1000000007;
  }

}
```

## 3. 후기

- 착실하게 문제의 요구사항을 따라가면 되는 문제였습니다.
