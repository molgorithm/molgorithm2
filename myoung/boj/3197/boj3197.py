from collections import deque
import sys


def is_board(x, y):
    if 0 <= x < C and 0 <= y < R:
        return True
    return False

def initial():
    global q1, visited, board

    _swans = []
    for y in range(R):
        for x in range(C):
            if board[y][x] == 'L':
                _swans.append((x, y))
                board[y][x] = '.'
                q1.append((x, y))
            elif board[y][x] == '.':
                q1.append((x, y))
                visited[y][x] = True
    return _swans


def move_swan():
    global swan_q1
    while swan_q1:
        x, y = swan_q1.popleft()
        if (x, y) == (goal_swan_x, goal_swan_y):
            return True
        for i in range(4):
            tx, ty = x + dx[i], y + dy[i]
            if is_board(tx, ty) and not swan_visited[ty][tx]:
                if board[ty][tx] == '.':
                    swan_q1.append((tx, ty))
                elif board[ty][tx] == 'X':
                    swan_q2.append((tx, ty))
                swan_visited[ty][tx] = True
    return False


def melt_ice():
    global q1
    while q1:
        x, y = q1.popleft()
        board[y][x] = '.'
        for i in range(4):
            tx, ty = x + dx[i], y + dy[i]
            if is_board(tx, ty) and not visited[ty][tx]:
                if board[ty][tx] == '.':
                    q1.append((tx, ty))
                elif board[ty][tx] == 'X':
                    q2.append((tx, ty))
                visited[ty][tx] = True
    return False


R, C = map(int, input().split())
board = [list(sys.stdin.readline()) for _ in range(R)]
dx = (1, 0, -1, 0)
dy = (0, 1, 0, -1)

swan_visited = [[False] * C for _ in range(R)]
visited = [[False] * C for _ in range(R)]
swan_q1, swan_q2 = deque(), deque()
q1, q2 = deque(), deque()
time = 0

swans = initial()
(start_swan_x, start_swan_y) = swans[0]
(goal_swan_x, goal_swan_y) = swans[1]
swan_visited[start_swan_y][start_swan_x] = True
swan_q1.append((start_swan_x, start_swan_y))


while True:
    melt_ice()
    if move_swan():
        break
    q1, swan_q1 = q2, swan_q2
    q2, swan_q2 = deque(), deque()
    time += 1
print(time)