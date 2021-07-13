# 프로그래머스 42889 실패율

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42889)

## 1. 설계 로직

1. 실패율 구하기
   - 실패율은 `해당 stage를 진행하고 있는 사람 수` / `해당 stage를 진행하고 있는 사람 수  + 해당 stage를 성공한 사람 수` 입니다.
   - 이를 위해서 맨 마지막 stage부터 확인해서 total_user를 늘려주며 각 stage별 실패율을 계산했습니다.
2. 실패율을 기준으로 내림차순 정렬, 실패율이 같은 경우 stage번호를 기준으로 오름차순 정렬해주면 됩니다.



- 시간복잡도 : O(N^2) 
  
  - 처음에 sort할 때 NlogN
  - 그 다음 갯수 세면서 실패율 계산할 때 N^2
  - 그 다음 sort할 때 NlogN
  - 마지막에 답 도출할 때 N
  
  -> 대략적으로 O(N^2)정도 걸린다구 생각했슴다

## 2. 코드

```python
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
```



## 3. 후기

```python
def solution(N, stages):
    result = {}
    denominator = len(stages)
    for stage in range(1, N+1):
        if denominator != 0:
            count = stages.count(stage)
            result[stage] = count / denominator
            denominator -= count
        else:
            result[stage] = 0
    return sorted(result, key=lambda x : result[x], reverse=True)
```

- 다른 풀이 중 가장 짧고 좋아요가 많은 풀이인데, 앞에서부터 하는 대신 `total_user` 수를 `len(stages)`로 초기화 한 다음, 앞의 stage 부터 사람 수를 빼는 식으로 진행했습니다.
- 참고로 파이썬에서 `list.count()`는 시간 복잡도가 O(N^2)이기 때문ㅇㅔ 이 풀이의 시간 복잡도는 O(N^3)이 될 듯 합니다. (sorted는 `NlogN`이므로)

