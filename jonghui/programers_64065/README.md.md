# 프로그래머스 64065 튜플

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/64065)

## 1. 설계 로직

1. 슬라이싱과 split을 이용하여 문자를 제거했습니다.
2. ''과 , 만 남는데 빼기를 하기위해 set으로 담았습니다.
3.  리스트에 숫자 갯수에 따라 정리하고 빼기하여 순서대로 담았습니다.

- 시간복잡도: 최대 1000000 (+ 500 + 500)

## 2. 코드

```python
def solution(s):
    answer = []
    tuples = list(map(str, s[2:-2].split('},{')))
    N = len(tuples)
    temp = [{} for _ in range(N)]
    for t in tuples:
        toint = set(map(int, t.split(',')))
        temp[len(toint)-1] = toint
    answer += list(temp[0])
    for n in range(N-1):
        answer += list(temp[n+1]-temp[n])
    return answer
```

## 3. 후기

- 차집합을 하기 위해 리스트로 구현하려고 했으나 set을 이용하여 해결했습니다.

