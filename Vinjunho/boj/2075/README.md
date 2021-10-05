# 백준 2075 N번째 큰 수

[문제 링크](https://www.acmicpc.net/problem/7662)

## 1. 설계 로직

1. n^2개의 수 중에서 n번째의 수를 구하는 문제이다.
2. n^2개의 데이터를 모두 관리할 필요가 없다
3. 상위 n개만 관리하여 n번째 수를 뽑아내면 되겠다.
4. 이분탐색을 통해 랭킹을 관리하면 더 효율적.



- (n^2)log(n) , 1<=n<=1500

## 2. 코드

```python
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

```



## 3. 후기

- 어제 우선순위큐와 이분탐색에 대해 공부하다 bisect라는 말도 안 되는 라이브러리를 알게되었다.
- 앞으로 이분탐색이 필요할 때 많이 써먹어야겠다.
- 파이썬 사기

