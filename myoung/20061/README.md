# 백준 20061 모노미노도미노2

[문제 링크](https://www.acmicpc.net/problem/20061)

## 1. 설계 로직

1. 파란색 보드, 초록색 보드를 같은 모양으로 만들어서 생각했습니다. (6행 4열 보드판)
2. `t, x, y`를 입력받으면 다음과 같은 3가지 로직을 진행합니다.
   1.  블록을 아래로 내립니다.
   2. 한 줄이 블록으로 채워졌을 때, 점수를 추가하고 해당 줄을 지워줍니다.
   3. 0, 1번째 줄에 블록이 존재할 때, 맨 아래의 줄을 지워주면서 0, 1번째가 모두 비어있을 수 있도록 만들어줍니다.
3. 파란색 보드의 경우 6행 4열로 만들어줬을 때 열을 나타내는 좌표도 수정을 해줘야하지만 좌우 반전된 상태로 진행해도 결과 값에는 영향이 없기 때문에 이대로 진행했습니다.



- 시간복잡도 : O(N^2)

## 2. 코드

```python
def game():
    global blue, green, score
    t, x, y = map(int, input().split())

    # 무조건 열방향으로 블록 내리는 것으로 생각
    def go_block(t, y, board):
        _x = -1
        if t == 1:
            while _x+1 < 6 and board[_x+1][y] == 0:
                _x += 1
        elif t == 2:
            while _x+1 < 6 and board[_x+1][y] == 0 and board[_x+1][y+1] == 0:
                _x += 1
            board[_x][y+1] = 1
        elif t == 3:
            while _x+2 < 6 and board[_x+1][y] == 0 and board[_x+2][y] == 0:
                _x += 1
            board[_x+1][y] = 1
        board[_x][y] = 1
        return board

    # 열갯수, 행갯수, 보드 이름
    def get_score(board):
        _score = 0
        _i = 5
        while _i >= 0:
            _cnt = 0
            for _j in range(4):
                if board[_i][_j] == 1:
                    _cnt += 1
            if _cnt == 4:
                _score += 1
                board.pop(_i)
                board = [[0, 0, 0, 0]] + board
            else:
                _i -= 1
        return board, _score

    def remove_block(board):
        cnt = 0
        for _i in range(1, -1, -1):
            if sum(board[_i]) > 0:
                cnt += 1
        for _ in range(cnt):
            board.pop()
            board = [[0, 0, 0, 0]] + board
        return board

    green = go_block(t, y, green)
    if t == 1:
        blue = go_block(t, x, blue)
    elif t == 2:
        blue = go_block(3, x, blue)
    elif t == 3:
        blue = go_block(2, x, blue)

    green, temp_score1 = get_score(green)
    blue, temp_score2 = get_score(blue)
    score += (temp_score1 + temp_score2)
    green = remove_block(green)
    blue = remove_block(blue)


def get_block():
    _sum = 0
    for i in range(len(blue)):
        _sum += (sum(blue[i]) + sum(green[i]))
    return _sum


N = int(input())
blue = [[0] * 4 for _ in range(6)]
green = [[0] * 4 for _ in range(6)]
score = 0
for _ in range(N):
    game()
print(score)
print(get_block())
```



## 3. 후기

2가지 이유때문에 삽질을 했습니다.

1. 문제에서는 x가 행, y가 열을 의미합니다. 저는 주로 x를 열로 사용하고 y를 행으로 썼는데, 문제에서는 그 반대로 되어 있어서 처음에 삽질을 좀 했습니다.  (문제 처음 풀이할 때도 이걸로 삽질을 조금 했었는데, 한 달만에 까먹은 나입니다요 ~!)
2. 한 줄이 블록으로 꽉 찼을 때 해당 부분을 pop해주는 부분에서 열의 인덱스를 넣지 않고, pop()으로 했다가 오류가 났었습니다.



처음에는 green, blue board 각각을 함수로 만들고 문제에 주어진 대로 완전 빡 구현한 풀이를 진행했는데, 이렇게 할 경우 시간 초과가 났습니다. 

그래서 하나의 함수로 비슷한 로직은 묶어서 만들어줘서 풀이를 진행했습니다.