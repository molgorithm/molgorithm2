class Node {
  constructor(val) {
    this.val = val;
    this.next = null;
  }
}

class Queue {
  constructor() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }
  push(val) {
    const newNode = new Node(val);
    // 비었으면 head/tail 모두 newNode
    if (!this.head) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      this.tail.next = newNode;
      this.tail = newNode;
    }
    this.size++;
  }
  pop() {
    // 비었으면 null반환
    if (!this.head) return null;
    const temp = this.head;

    // 값이 1개 존재하면 tail = null
    if (this.head === this.tail) {
      this.tail = null;
    }

    this.head = this.head.next;

    this.size--;
    return temp.val;
  }
  isEmpty = () => this.size === 0;
}

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
  const q = new Queue();
  visited[0] = true;
  q.push(0);
  while (!q.isEmpty()) {
    const curNode = q.pop();
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
