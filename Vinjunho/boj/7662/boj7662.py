import sys
import heapq

input = sys.stdin.readline
T = int(input())
for _ in range(T):
    n = int(input())
    nq, mq = [], []
    visit = [False] * (n + 1)  # 지워진 번호 (입력된 순서)
    for i in range(n):
        command, num = input().split()
        num = int(num)
        if command == 'I':  # 입력 명령이면
            heapq.heappush(nq, (num, i))  # 작은수우선
            heapq.heappush(mq, (-num, i))  # 큰수우선
            continue
        elif num == 1 and mq:  # 지우라는 명령어에 지울 값이 있으면
            visit[heapq.heappop(mq)[1]] = True  # 입력된 값을 지우고 지웠음을 저장한다
        elif num == -1 and nq:  # 지우라는 명령어에 지울 값이 없으면
            visit[heapq.heappop(nq)[1]] = True  # 입력된 값을 지우고 지웠음을 저장한다
        while nq and visit[nq[0][1]]:  # nq에서 제거할 수 있을만큼 제거하겠다.
            heapq.heappop(nq)
        while mq and visit[mq[0][1]]:  # mq에서 제거할 수 있을만큼 제거하겠다.
            heapq.heappop(mq)
    print(f'{-mq[0][0]} {nq[0][0]}' if nq else 'EMPTY')
