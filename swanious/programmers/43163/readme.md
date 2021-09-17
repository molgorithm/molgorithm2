# 프로그래머스 43163 단어 변환

[문제 링크](https://https://programmers.co.kr/learn/courses/30/lessons/43163)

## 1. 설계 로직

- dfs로 모든 가능한 경로를 탐색하여 가장 적은 횟수만에 갈 수 있는 경우를 출력해줬습니다.

## 2. 코드

```python
import sys

answer = sys.maxsize

def checkDiffOneWord(str1, str2):
    cnt = 0
    for i in range(len(str1)):
        if str1[i] != str2[i]:
            cnt += 1
    return cnt == 1


def dfs(start, count, target, visit, words):
    global answer

    if start == target:
        answer = min(count, answer)
        return True

    for i in range(len(words)):
        if not visit[i] and checkDiffOneWord(start, words[i]):
            visit[i] = True
            if dfs(words[i], count + 1, target, visit, words): return True
            visit[i] = False
            count -= 1

    return False


def solution(begin, target, words):
    l = len(words)
    visit = [False] * len(words)

    return 0 if not dfs(begin, 0, target, visit, words) else answer
```

## 3. 후기

- dfs를 사용하여 풀이했습니다.
