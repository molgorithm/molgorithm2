import heapq

def solution(jobs):
    N = len(jobs)
    _time = 0
    answer = 0
    jobs.sort()
    waiting_lst = []
    while jobs or waiting_lst:
        while jobs:
            now = jobs[0]
            if _time >= now[0]:
                waiting_lst.append(heapq.heappop(jobs))
            else:
                break
        if waiting_lst:
            waiting_lst.sort(key=lambda x: x[1])
            now = heapq.heappop(waiting_lst)
            answer += (_time-now[0]+now[1])
            _time += now[1]
        else:
            if jobs:
                now = heapq.heappop(jobs)
                answer += now[1]
                _time = (now[0]+now[1])
    return answer//N

print(solution([[0, 3], [1, 9], [2, 6], [30, 3]]))