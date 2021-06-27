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

