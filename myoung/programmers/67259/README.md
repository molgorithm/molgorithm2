# 프로그래머스 67259 경주로 건설

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67258)

## 1. 설계 로직

0. BFS사용

1. 시작 지점: (0, 0), 도착 지점: (N-1, N-1)

2. 갈 수 있는 방향 : 상, 하, 좌, 우 -> 4가지 존재

3. 현재 지점에서 갈 수 있는 방향으로 움직여보고(tx, ty)

   1. 만약 tx, ty가 범위를 벗어나거나, 1이 들어있으면 queue에 담지 않는다.

   2. 만약 `board[ty][tx]`값이 0이라면

      1. 현재 방향과 tx, ty의 방향이 동일한 경우, 

         - `현재 money + 100`을 `board[ty][tx]`에 담고, queue에 담는다.

      2. 현재 방향과 tx, ty의 방향이 다른 경우,

         - `현재 money + 600`을 `board[ty][tx]`에 담고, queue에 담는다.

         - money+600 인 이유는, 코너(500) + 직선(100) 이므로

   3. 만약 `board[ty][tx]`값에 1, 0이 아닌 `money` 값이 들어 있다면 (이미 방문한 적이 있는 경우)

      1. 예상되는 money (`현재 money + 100`or `현재 money + 600`)와 `board[ty][tx]`에 담겨있는 `money` 값 비교
         1. 만약 기존에 담겨있는 값보다 예상되는 money 값이 더 작거나 같은 경우 -> tx, ty를 queue에 담음

   

- 시간복잡도 : O(N^2) 
  - 탐색을 통해 거의 맵 전체를 확인하기 때문에 O(N^2)입니다ㅏ욧

## 2. 코드

```python
from collections import deque


def solution(board):
    N = len(board)
    board[0][0] = -1

    def is_board(x, y):
        if 0 <= x < N and 0 <= y < N:
            return True
        return False

    def bfs(sx, sy):
        nonlocal board
        q = deque()
        q.append((sx, sy, -1, -500))
        dx = (1, 0, -1, 0)
        dy = (0, 1, 0, -1)

        while q:
            _x, _y, _dir, _money = q.popleft()
            for i in range(4):
                tx, ty = _x + dx[i], _y + dy[i]
                if is_board(tx, ty) and board[ty][tx] != 1:
                    if board[ty][tx] == 0:
                        if _dir == i:
                            _temp_money = _money + 100
                        else:
                            _temp_money = _money + 600
                        board[ty][tx] = _temp_money
                        q.append((tx, ty, i, _temp_money))
                    else:
                        _temp_money = _money + 100 if _dir == i else _money + 600
                        if _temp_money <= board[ty][tx]:
                            board[ty][tx] = _temp_money
                            q.append((tx, ty, i, _temp_money))

    bfs(0, 0)
    return board[N - 1][N - 1]
```



## 3. 후기

- 처음에 N의 범위가 3~25인 것을 보고 완탐 문제라고 생각했구, 미로 찾기 문제여서 BFS 문제라고 생각했습니다.
