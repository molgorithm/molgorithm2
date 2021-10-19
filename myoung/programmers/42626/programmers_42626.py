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