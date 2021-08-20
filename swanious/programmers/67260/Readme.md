# 프로그래머스 67256 키패드 누르기

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67260)

## 1. 설계 로직

1. 설계로직

- bfs 활용 (dfs의 경우 rootNode로 다시 돌아올 경우 방문체크가 힘들었습니다..)

2. 고려해야할 사항

- 선 방문 노드가 있을 경우 배열에 임시(접근금지)로 저장하고 continue
- 선 방문 노드에 도달했을 때 이후 방문할 노드를 q에 삽입하기(접근금지 해제)

3. 시간복잡도 O(N + E)

## 2. 코드

```javascript
function solution(n, path, order) {
  const adj = Array.from(Array(n), () => []);
  const visited = new Array(n).fill(false);
  const beforePath = new Array(n);
  const closedNode = new Map();

  // 인접리스트 초기화
  path.forEach(([a, b]) => {
    adj[a].push(b);
    adj[b].push(a);
  });

  // 먼저 방문해야할 곳 저장
  order.forEach(([from, to]) => {
    beforePath[to] = from;
  });

  // 제약사항 3번 (A -> B -> C)인 경우는 없으므로, 최상위 노드인 0이 order에 있으면 안됨
  if (beforePath[0] !== undefined) return false;

  // bfs
  const q = [0];
  visited[0] = true;

  while (q.length > 0) {
    const curNode = q.shift();
    for (let nextNode of adj[curNode]) {
      const mustVisitBefore = beforePath[nextNode]; // 먼저 가야할 노드
      const mustVisitAfter = closedNode.get(nextNode); // 이후 가야할 노드

      // 예제 1. Node 1번의 경우 4번을 먼저 찍고 와야하므로 1번노드를 접근금지배열(closedNode)에 저장
      if (!visited[mustVisitBefore] && mustVisitBefore !== undefined) {
        closedNode.set(mustVisitBefore, nextNode);
        continue;
      }

      if (visited[nextNode]) continue;

      // nextNode 이후에 방문해야할 노드가 있으면, 접근금지 해제
      // 예제 1. Node 4번을 방문하면 1번 노드는 접근금지 해제해주고 q에 삽입
      if (mustVisitAfter !== undefined) {
        q.push(mustVisitAfter);
        visited[mustVisitAfter] = true;
      }

      q.push(nextNode);
      visited[nextNode] = true;
    }
  }
  return visited.filter((v) => v).length === n ? true : false;
}
```

## 3. 후기

- javascript에서 queue를 사용하기 위해서 크게 3가지 방법이 있다.

1. 링크드리스트로 만들어 사용
2. `shift()` - 왼쪽으로 한칸 shift(반환값은 queue와 동일)
3. `splice(0, 1)` - 0번째 idx에서 1개 삭제

직접 만들기 어렵기도 귀찮기도해서 2번을 가장 많이 사용했는데, shift의 경우 한 칸씩 밀어야해서 O(n)의 시간복잡도, splice는 더 많이 걸린다고..
속도가 중시되는 문제의 경우 shift() 사용이 안될듯하여 linked List를 사용한 풀이를 연습했다.
속도면에서 얼마나 차이가 나는지 [참고 블로그](https://velog.io/@grap3fruit/JS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B5%AC%ED%98%84-%ED%81%90Queue-%EA%B5%AC%ED%98%84%ED%96%88%EC%9D%84%EB%95%8C-vs-Array-%EB%A9%94%EC%84%9C%EB%93%9Cshift-splice-%EC%82%AC%EC%9A%A9%ED%96%88%EC%9D%84%EB%95%8C-%EC%86%8D%EB%8F%84-%EB%B9%84%EA%B5%90)을 참고했다.

+근데 시간 얼마 차이 안난다... 걍 shift() 써야지..
