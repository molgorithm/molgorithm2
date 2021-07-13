
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
def solution(N, stages):
    answer = []
    stages.sort()
    stage_idx = len(stages) - 1
    total_user = 0
    for stage in range(N + 1, 0, -1):
        now_user = 0
        is_progress = False
        if stage == N + 1:
            while stage_idx >= 0 and stages[stage_idx] == stage:
                total_user += 1
                stage_idx -= 1
        else:
            while stage_idx >= 0 and stages[stage_idx] == stage:
                is_progress = True
                now_user += 1
                total_user += 1
                stage_idx -= 1
            if is_progress:
                fail_percent = now_user / total_user
            else:
                fail_percent = 0
            answer.append((fail_percent, stage))

    answer.sort(key=lambda x: (-x[0], x[1]))
    return [answer[i][1] for i in range(len(answer))]