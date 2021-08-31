# 프로그래머스 42628 이중우선순위큐

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42628)

## 1. 설계 로직

1. 최소힙, 최대힙을 생성합니다.
2. 주어진 연산을 진행합니다.
  1) `I`인 경우
    최소힙, 최대힙에 각각 heappush
  2) `D 1`인 경우
    최대힙에 요소가 존재하지 않는 경우 해당 연산은 무시합니다.
    최대힙에서 최댓값을 삭제한 후, 해당 요소를 최소 힙에서도 제거해줍니다.
  3) `D -1`인 경우
    최소힙에 요소가 존재하지 않는 경우 해당 연산은 무시합니다.
    최소힙에서 최솟값을 삭제한 후, 해당 요소를 최대 힙에서도 제거해줍니다.
3. 연산 후 큐가 비었다면 `[0, 0]`을, 비어있지 않는 다면 `[최댓값, 최솟값]`을 반환합니다.


- 시간복잡도: O(N^2)
  - 연산 중 `D`로 시작하는 연산에서 삭제된 요소를 다른 힙에서 삭제하는 데 O(N)만큼의 시간이 요구되므로 최대 O(N^2)입니다.

## 2. 코드

```python
import heapq

def solution(operations):
    max_q, min_q = [], []
    
    def del_item(item, _type):
        nonlocal max_q, min_q
        if _type == 'max':
            min_q.remove(-item)
        else:
            max_q.remove(-item)
    
    for operation in operations:
        if operation[0] == 'I':
            nums = int(operation[2:])
            heapq.heappush(max_q, -nums)
            heapq.heappush(min_q, nums)
        elif operation == 'D 1':
            if max_q:
                item = heapq.heappop(max_q)
                del_item(item, 'max')
        else:
            if min_q:
                item = heapq.heappop(min_q)
                del_item(item, 'min')
    
    return[-max_q[0], min_q[0]] if max_q and min_q else [0, 0]
```


## 3. 후기

- heapq 최고 python 최고

