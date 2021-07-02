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


print(solution(1, 1, 5, ["08:00", "08:01", "08:02", "08:03"]))
print(solution(2, 10, 2, ["09:10", "09:09", "08:00"]))
print(solution(2, 1, 2, ["09:00", "09:00", "09:00", "09:00"]))
print(solution(1, 1, 5, ["00:01", "00:01", "00:01", "00:01", "00:01"]))
print(solution(1, 1, 1, ["23:59"]))
print(solution(10, 60, 45, ["23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"]))