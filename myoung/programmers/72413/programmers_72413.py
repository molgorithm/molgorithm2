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

print(solution(6, 4, 6, 2, [[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22], [1, 6, 25]]))