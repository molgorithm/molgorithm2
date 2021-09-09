# 프로그래머스 42626 더맵게

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42626)

## 1. 설계 로직
```
섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
```
- heapq 모듈 사용해서 배열을 최소힙으로 저장
- 2개를 빼서 위의 계산식으로 값을 구하고 다시 heap에 저장 반복
- while문 안의 배열의 길이가 2보다 작으면 -1리턴

## 2. 코드

```python
import heapq

def solution(scoville, K):
    heap = []
    cnt = 0
    for s in scoville:
        heapq.heappush(heap, s)
    
    while heap[0] < K:
        if len(heap) > 1:
            heapq.heappush(heap, heapq.heappop(heap) + (heapq.heappop(heap) * 2))
        else: return -1
        
        cnt += 1
        
    return cnt
```

## 3. 후기
- heapq 모듈을 활용해서 풀 수 있었다. 파이썬 짱..