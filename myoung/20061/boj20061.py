'''
4
1 1 1
2 3 0
3 2 2
3 2 3
->
1
10
'''
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
