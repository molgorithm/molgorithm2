# 프로그래머스 42626 더 맵게

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42626)

## 1. 설계 로직

1. 주어진 scoville을 최소 힙으로 정렬합니다.
2. scoville 중 가장 작은 값이 K보다 작을 경우 
`섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)`
방법으로 새로운 음식을 생성하여 scoville에 추가합니다.
3. 섞을 수 없는 경우가 되면(남은 scoville의 요소 갯수가 2개보다 작을 때) `-1`을 반환합니다.
4. 가장 작은 원소가 K보다 크면 `answer`를 반환합니다.

- 시간복잡도: O(NlogN)
  - heapq의 heapify 시간 복잡도: O(N)
  - heapq의 heappop 시간 복잡도: O(logN)
  - heapq의 heappush 시간 복잡도: O(logN)
  - scoville의 요소 N개가 주어졌을 때, 섞을 수 있는 최대 횟수는 N-1
## 2. 코드

```python
import heapq

def solution(scoville, K):
    answer = 0
    heapq.heapify(scoville)
    while len(scoville) > 1 and scoville[0] < K:
        answer += 1
        _first = heapq.heappop(scoville)
        _second = heapq.heappop(scoville)
        _new = _first + _second*2
        heapq.heappush(scoville, _new)
    if scoville[0] < K:
        return -1       
    return answer
```



## 3. 후기

- heapq 최고 python 최고

