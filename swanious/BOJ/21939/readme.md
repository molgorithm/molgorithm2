# 21939 문제추천시스템

> [문제 링크](https://www.acmicpc.net/problem/21939)

## 고려해야할 사항

1. max heap, min heap 생성하여 recommend에 따른 값 뽑기
2. 같은 난이도면 더 작은 문제번호가 나와야하므로 max heap의 경우 문제번호도 음수로 받기

## 나의 코드

```python
import sys
from heapq import heappop, heappush
from collections import defaultdict

input = sys.stdin.readline

N = int(input())
max_p = []
min_p = []
solved = defaultdict(bool)
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
```

## 후기

- solved 여부에 따라 가장 작은 값/큰 값만 고려하면됨
- 항상 문제를 잘 읽도록하자 ...!
