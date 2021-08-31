# 프로그래머스 42627 디스크 컨트롤러

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42627)

## 1. 설계 로직

1. 주어진 job을 정렬합니다.
2. waiting list에 현재 대기 중인 job들을 담습니다.
3. 현재 진행하고 있는 job이 끝나면 waiting list를 갱신합니다.
  (주어진 job 중 현재 대기 중인 job이나 waiting list에 존재하지 않는 job 추가)
4. waiting list를 소요시간 기준으로 정렬 후 가장 짧은 소요 시간을 가진 job을 실행합니다.
5. waiting list에 존재하는 job이 없는 경우 현재 대기 중인 job이 없으므로 아직 요청되지 않은 job 중 가장 먼저 요청되는 job을 작업합니다.
6. 모든 작업이 끝나면 작업에 소요된 시간과 작업 개수를 나누어 평균을 구합니다.

- 시간복잡도: O(N^2logN)
  - 하나의 작업을 수행할 때마다 waiting list를 정렬해주는 로직이 들어있어 최대 N^2logN이라고 생각했습니다.

## 2. 코드

```python
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
```



## 3. 후기

- 처음에는 요청되는 시간 순으로 하면 되겠다 싶었는데 문제를 읽으니 그것이 아니었다!

