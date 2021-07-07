# 프로그래머스 17678 셔틀버스

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/17678)

## 1. 설계 로직

1. 시간은 모두 분(min)을 기준으로 바꾸어 문제풀이를 진행했습니다.
2. 먼저, 가능한 셔틀 시간을 모두 shuttles리스트에 담아줍니다. (셔틀 운행 횟수, 간격 고려)
3. 크루들이 대기열에 오는 시간을 모두 리스트에 담아주고, 정렬해줍니다.
4. 일단 모든 크루를 셔틀에 태워봅니다.
   1. 만차의 기준: 마지막 셔틀 버스가 만차인지 아닌지.
   2. 만차라면, 제일 뒷 시간인 사람보다 1분 빠른 시간이 답이 됩니다.
   3. 만차가 아닌 경우, 마지막 셔틀 버스 시간이 답이 됩니다. (셔틀 도착 시간까지 탈 수 있으므로)



- 시간복잡도: O(N+M)
  - 각 버스에 해당 승객이 탈 수 있는지 없는지 확인해야하므로 O(N+M)입니다. (N = 버스 수, M = 승객 수)

## 2. 코드

```python
def solution(n, t, m, timetable):
    shuttles = [(540 + t * i) for i in range(n)]

    def trans_timetable_to_num():
        trans_lst = []
        for time in timetable:
            _hour, _min = map(int, time.split(':'))
            trans_lst.append((_hour * 60) + _min)
        return trans_lst

    def shuttle_info(people, shuttles):
        infos = [0] * len(shuttles)
        person_idx = 0
        for i in range(len(shuttles)):
            shuttle = shuttles[i]
            while infos[i] < m and person_idx < len(people):
                person = people[person_idx]
                if person <= shuttle:
                    infos[i] += 1
                    person_idx += 1
                else:
                    break
        return infos

    def num_to_time(time):
        _hour = time // 60
        _min = time % 60
        _hour = f'{_hour}' if len(str(_hour)) == 2 else f'0{_hour}'
        _min = f'{_min}' if len(str(_min)) == 2 else f'0{_min}'
        return f'{_hour}:{_min}'

    timetables = sorted(trans_timetable_to_num())
    info = shuttle_info(timetables, shuttles)
    if info[-1] == m:
        _answer = timetables[sum(info)-1] - 1
    else:
        _answer = shuttles[-1]
    answer = num_to_time(_answer)
    return answer
```



## 3. 후기

- 문제에 주어진 요구사항만 제대로 구현하면 되는 문제였습니다.

