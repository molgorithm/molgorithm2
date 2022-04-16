import sys, bisect

input = sys.stdin.readline
n = int(input())
lank = [] # 상위 n개의 수만 관리할 list
for _ in range(n):
    li = list(map(int, input().split())) # readline
    for i in range(n):
        bisect.insort(lank, -li[i]) # 크기순으로 정렬하며 삽입한다(bisect는 이분탐색활용하므로 O(log(n))
        if len(lank) > n: # 상위 n번째 이 후의 수는 볼 이유 없으므로 삭제
            lank.pop() # 리스트 맨 끝을 날리는 pop()함수는 O(1)
print(-lank[-1]) # n번째 수를 출력
