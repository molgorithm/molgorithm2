# 프로그래머스 72413 합승 택시요금

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/72413)

## 1. 설계 로직

0. 최종 요금: `(시작지점~공통지점) + (공통지점~A) + (공통지점~B)`

   따라서 최종 요금의 합이 가장 작은 경우의 공통지점을 구하면 됩니다.

1. 공통지점을 1~n으로 지정하고 각각의 경우에서의 최종 요금을 계산합니다.

   1. 다익스트라와 우선순위큐를 사용하여 현재 위치(s)에서 각각의 노드에 갈 수 있는 최소 거리들을 visited에 담은 후 e까지의 거리를 반환합니다.

- 시간복잡도: O(NElogN), E는 간선의 갯수, N는 노드의 갯수 -> 수정 후 O(ElogN)으로 수정
  - 다익스트라의 시간 복잡도가 ElogN이고, 각각을 3N만큼 진행하기 때문에 NElogN라고 생각했습니다.
  - E는 2이상 N(N-1)/2이하이므로 N으로만 나타내면 N^3logN입니다.

## 2. 코드

```python
import heapq


def solution(n, s, a, b, fares):
    answer = 1e9
    graph = [[] for _ in range(n + 1)]
    visited = [[1e9] * (n + 1) for _ in range(3)]

    for fare in fares:
        _s, _g, _cost = fare
        graph[_s].append([_g, _cost])
        graph[_g].append([_s, _cost])

    def dijkstra(s, idx):
        visited[idx][s] = 0
        pq = [[0, s]]
        heapq.heapify(pq)

        while pq:
            cost, node = heapq.heappop(pq)
            if cost > visited[idx][node]:
                continue

            for i in graph[node]:
                new_node, new_cost = i[0], i[1]
                new_cost += cost
                if new_cost < visited[idx][new_node]:
                    visited[idx][new_node] = new_cost
                    heapq.heappush(pq, [new_cost, new_node])

    dijkstra(s, 0)
    dijkstra(a, 1)
    dijkstra(b, 2)
    for i in range(1, n + 1):
        answer = min(answer, visited[0][i] + visited[1][i] + visited[2][i])
    return answer
```

## 3. 후기

- 그래프 보고 하 ~! 하다가 아예 방법이 떠오르지 않아 검색했습니다. 그랬더니 키워드가 **다익스트라**더라구요 ^-^~~ 그래서 다익스트라 공부좀 하고 해결해보았습니다 ,, ㅇ<-< 
- python에서 우선순위 큐를 사용하는 방법에는 PriorityQueue, heapq가 있는데 비교적 heapq가 더 빠르게 동작한다고 합니다.

```python
import heapq


def solution(n, s, a, b, fares):
    answer = 1e9
    graph = [[] for _ in range(n + 1)]
    visited = [[1e9] * (n + 1) for _ in range(3)]

    for fare in fares:
        _s, _g, _cost = fare
        graph[_s].append([_g, _cost])
        graph[_g].append([_s, _cost])

    def dijkstra(s, idx):
        visited[idx][s] = 0
        pq = [[0, s]]
        heapq.heapify(pq)

        while pq:
            cost, node = heapq.heappop(pq)
            if cost > visited[idx][node]:
                continue

            for i in graph[node]:
                new_node, new_cost = i[0], i[1]
                new_cost += cost
                if new_cost < visited[idx][new_node]:
                    visited[idx][new_node] = new_cost
                    heapq.heappush(pq, [new_cost, new_node])

    dijkstra(s, 0)
    dijkstra(a, 1)
    dijkstra(b, 2)
    for i in range(1, n + 1):
        answer = min(answer, visited[0][i] + visited[1][i] + visited[2][i])
    return answer
```

- 모르고리즘 리뷰 중 제 코드의 시간 복잡도를 줄일 수 있는 방법에 대해 이야기를 나누고 해당 코드로 수정했습니다.
- 기존의 코드는 중복되는 로직이 많았지만, 수정 후 s->n, n->a a->b를 한 번만 계산하고 해당 값을 담아 놓은 다음 풀이를 진행함으로써 시간 복잡도를 N^2logN으로 바꿀 수 있었습니다.

- 또한 리뷰 후 지점 갯수가 500개 이하라면 다익스트라보다 플로이드 워셜 알고리즘을 사용하는 것이 알고리즘을 구현하는 시간을 줄일 수 있다는 것을 배웠습니다. (500개가 기준인 이유는 플로이드 워셜의 시간 복잡도가 N^3이기 때문입니다.)

