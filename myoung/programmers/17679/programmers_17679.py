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

