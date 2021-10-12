import sys
from heapq import heappop, heappush
from collections import defaultdict

input = sys.stdin.readline

N = int(input())
max_p = []
min_p = []
solved = defaultdict(bool) # solved 여부

for i in range(N):
    p, l = map(int, input().split())
    heappush(max_p, (-l, -p)) 
    heappush(min_p, (l, p))
    solved[p] = False

M = int(input())
for i in range(M):
    temp = input().split()
    cmd = temp[0]
    rest = list(map(int, temp[1:]))

    if cmd == 'add':
        while solved[-max_p[0][1]]:
            heappop(max_p)
        while solved[min_p[0][1]]:
            heappop(min_p)
        solved[rest[0]] = False
        heappush(max_p, (-rest[1], -rest[0]))
        heappush(min_p, (rest[1], rest[0]))

    elif cmd == 'solved':
        solved[rest[0]] = True

    # 문제 추천
    else:
        if rest[0] == 1:
            while solved[-max_p[0][1]]: # 난이도가 제일 높은 문제가 이미 해결된 문제면 지워줌
                heappop(max_p)
            print(-max_p[0][1])
        elif rest[0] == -1:
            while solved[min_p[0][1]]:
                heappop(min_p)
            print(min_p[0][1])