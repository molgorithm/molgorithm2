# 프로그래머스 17679 프렌즈4블록

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17679)

## 1. 설계 로직

1. 주어진 board를 모두 순회한다.

   이 때, 현재 좌표 (x, y)를 기준으로 오른쪽`(x+1, y)`, 아래`(x, y+1)`, 오른쪽 아래`(x+1, y+1)`가 모두 같은 모양이라면 -> 추후에 지워질 블록이기 때문에 리스트에 `(x, y)`를 담아놓는다.

   (바로 지우지 않는 이유 : 겹치는 경우가 존재하므로)

2. 순회 후 지워질 블록 리스트를 하나씩 확인하며 오른쪽, 아래, 오른쪽 아래 블록까지 모두 지운다. 지우면서 이 블록이 겹치는 블록인지 확인하고, 겹치지 않는 블록이라면 count +1, 겹치는 블록인데 이미 지워진 블록이라면 pass한다.
3. 블록을 지운 후 위에 있는 블록이 아래로 떨어져 빈 공간을 채운다.
4. `1-3`의 로직을 지울 수 있는 블록이 존재하지 않을 때까지 반복한다.



- 시간복잡도: O(N*M)이라고 생각했습니다. (순회하므로)

## 2. 코드

```python
def solution(m, n, board):
    answer = 0
    dx = [0, 1, 0, 1]
    dy = [0, 0, 1, 1]
    board = [list(board[i]) for i in range(m)]

    def is_board(x, y):
        if 0 <= x < n and 0 <= y < m and board[y][x] != 0:
            return True
        return False

    def near_same():
        arr = []
        for y in range(m):
            for x in range(n):
                val = board[y][x]
                cnt = 0
                for i in range(1, 4):
                    tx, ty = x + dx[i], y + dy[i]
                    if is_board(tx, ty) and val == board[ty][tx]:
                        cnt += 1
                    else:
                        break
                if cnt == 3:
                    arr.append((x, y))
        return arr

    def delete_block(blocks):
        cnt = 0
        for block in blocks:
            _x, _y = block[0], block[1]
            for i in range(4):
                tx, ty = _x + dx[i], _y + dy[i]
                if board[ty][tx] != 0:
                    board[ty][tx] = 0
                    cnt += 1
        return cnt

    def move_block():
        for x in range(n):
            temp = []
            for y in range(m):
                if board[y][x] != 0:
                    temp.append(board[y][x])
                    board[y][x] = 0
            idx = m - 1
            while temp:
                board[idx][x] = temp.pop()
                idx -= 1

    while True:
        arr = near_same()
        if not arr:
            return answer
        answer += delete_block(arr)
        move_block()
```



## 3. 후기

- 문제에 주어진 요구사항만 제대로 구현하면 되는 문제였습니다.

