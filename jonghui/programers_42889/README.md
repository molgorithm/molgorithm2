# 프로그래머스 42889 실패율

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42889)

## 1. 설계 로직

1. 스테이지 갯수에 만큼 딕셔너리를 초기화 해줍니다.
   - 1부터 순서대로 딕셔너리를 만들어야 실패율을 구할 수 있음
   - 아래 for문에서 없으면 딕셔너리 만들기로 하면 정렬한번 해줘야됨
2. 도달한 스테이지에 value값을 1씩 증가시킵니다.
3. 딕셔너리를 돌면서 0이 아닌 경우만 사람 수로 나누어 실패율을 구한다.
4. 실패율을 내림차순으로 정렬한다.



- 시간복잡도 = N (500 + 200000 + 500 + 500log500)

## 2. 코드

```python
def solution(N, stages):
    fail = dict()
    for n in range(1,N+1):
        fail[n] = 0
    S = len(stages)
    for s in stages:
        if s <= N:
            fail[s] += 1
    for key, value in fail.items():
        if value:
            new_value = value/S
            S -= value
            fail[key] = new_value
    answer = sorted(fail, key=lambda x:fail[x], reverse=True)
    return answer
```



## 3. 후기

- 내림차순 구할 때 힘들었습니다.

