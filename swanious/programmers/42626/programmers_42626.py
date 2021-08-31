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