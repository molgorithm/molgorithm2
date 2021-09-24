# 프로그래머스 43162 네트워크

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/43162)

## 1. 설계 로직

- 연결된 네트워크의 묶음을 dfs로 찾으면 된다.
- 연결된 네트워크를 찾은 경우 cnt + 1

## 2. 코드

- python

```python
def dfs(start, computers, visit):
    if start in visit:
        return 0, visit
    # visit에 방문체크
    visit.add(start)

    # 재귀(인접 리스트)
    for i in range(len(computers[start])):
        # 자신의 위치거나 연결되지 않은 노드의 경우 continue
        if i == start or not computers[start][i]: continue

        # 연결됐고 방문을 안했으면 재귀
        if i not in visit:
            dfs(i, computers, visit)

    # 섬의 개수 반환
    return 1, visit

def solution(n, computers):
    answer = 0
    visit = set()
    for start in range(n):
        count, visit = dfs(start, computers, visit)
        answer += count
    return answer
```

- js

```javascript
function solution(n, computers) {
  var answer = 0;
  var visit = new Set();

  for (let start = 0; start < n; start++) {
    const count = dfs(start, computers, visit);
    answer += count;
  }
  return answer;
}

function dfs(start, computers, visit) {
  if (visit.has(start)) return 0;
  visit.add(start);

  for (let i = 0; i < computers[start].length; i++) {
    if (i == start || !computers[start][i]) continue;
    if (!visit.has(i)) {
      dfs(i, computers, visit);
    }
  }
  return 1;
}
```

## 3. 후기

- 백준 섬의 개수 구하기 문제와 동일!
