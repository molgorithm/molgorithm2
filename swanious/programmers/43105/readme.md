# 프로그래머스 43105 정수 삼각형

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/43105)

## 1. 설계 로직
- 현재의 위치에 따라 이전 행의 값을 더해준다.
- - 현재 행 첫번째 값: 현재 값 + 이전행 첫째 값

- - 현재 행 마지막 값: 현재 값 + 이전행 마지막 값

- - 현재 행 중간 값: 현재 값 + 이전행 두 개 중 큰 값

- 시간복잡도: O(n^2)

## 2. 코드

```python
def solution(triangle):
    n = len(triangle)
    for i in range(1, n): # 이전행의 값을 더해주기 위해 1행부터 시작
        for j in range(i + 1):
            if (j == 0): # i행의 첫번째 값
                triangle[i][j] += triangle[i-1][j]
            elif (j == i): # i행의 마지막 값
                triangle[i][j] += triangle[i-1][j-1]
            else: # i행의 중간 값
                triangle[i][j] += max(triangle[i-1][j-1], triangle[i-1][j])
                
    return max(triangle[-1])
```

## 3. 후기
- 위치에 따라 이전 행 중 큰 값을 더해주고 마지막 행에 도달할 경우 제일 큰 값을 뽑아주기