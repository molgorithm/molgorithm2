# 프로그래머스 42898 등굣길

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42898)

## 1. 설계 로직
1. n행, m열의 mapp 초기화, 방향 초기화 (방향은 우측과 아래로만 가면됨)
2. mapp에 웅덩이 위치 0으로 초기화(행, 열 반대로 받아줘야함..)
- - 만약 웅덩이가 0행이나 0열에 있으면 그 위치부터 n행 혹은 m열까지 0으로 초기화한다.(우측, 아래로만 움직일 것이므로)
3. 현재에 올 수 있는 경우의 수를 구한다.(이전행, 이전열의 경우의 수를 더해줌)

- 시간복잡도: O(n^2)

## 2. 코드

```python
def solution(m, n, puddles):
    mapp = [[1] * m for _ in range(n)]
    for x, y in puddles: # 첫행, 첫열에 함정이 있으면 그곳부터 끝까지 갈 수 없음(우측, 아래로만 움직일 수 있으므로)
        mapp[y-1][x-1] = 0
        if y-1 == 0:
            for k in range(x-1, m):
                mapp[0][k] = 0
        if x-1 == 0:
            for k in range(y-1, n):
                mapp[k][0] = 0
        
    for i in range(1, n):
        for j in range(1, m):
            if mapp[i][j] != 0:
                mapp[i][j] = mapp[i-1][j] + mapp[i][j-1]
    
         
    return mapp[n-1][m-1]  % 1000000007
```

## 3. 후기
- 경우의 수 구하기 문제