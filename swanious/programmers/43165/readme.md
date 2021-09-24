# 43165 타겟넘버

> [문제 링크](https://programmers.co.kr/learn/courses/30/lessons/43165)

## 설계 로직

1. 최대 숫자의 개수는 20개이므로 2^20까지 돔
2. 처음 queue에 `[-첫번째값, +첫번째값]`으로 초기화 한 후 값에 `numbers[idx]`의 값을 플러스/마이너스하여 배열에 삽입
3. 마지막 배열에 있는 target의 개수가 정답

## 나의 코드

```python
from collections import deque

def solution(numbers, target):
    idx, ans = 0, 0
    q = deque()
    q.append([-numbers[idx], numbers[idx]])
    idx += 1

    while q:
        arr = q.popleft()
        if idx != len(numbers): # 숫자의 개수만큼 탐색
            temp = []
            for number in arr:
                temp.append(number - numbers[idx])
                temp.append(number + numbers[idx])
            q.append(temp)
            idx += 1
        else: # 탐색 끝
            for number in arr:
                if number == target: ans += 1

    return ans
```

```javascript
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

  push = (val) => {
    const node = new Node(val);
    if (this.size) {
      this.tail.next = node;
    } else {
      this.head = node;
    }
    this.tail = node;
    this.size++;
  };
  popleft = () => {
    if (this.size == 0) return null;
    const value = this.head.val;
    this.head = this.head.next;
    this.size--;
    if (this.size == 0) this.tail = null;
    return value;
  };
  isEmpty = () => this.size === 0;
}

const bfs = (startIdx, numbers, target) => {
  const Q = new Queue();
  Q.push([numbers[startIdx], -numbers[startIdx]]);

  let idx = startIdx + 1;
  let answer = 0;
  while (!Q.isEmpty()) {
    let list = Q.popleft();

    if (idx !== numbers.length) {
      let newList = [];

      for (let num of list) {
        newList.push(num + numbers[idx]);
        newList.push(num - numbers[idx]);
      }
      Q.push(newList);
      idx++;
    } else {
      for (let num of list) {
        if (num === target) {
          answer++;
        }
      }
    }
  }
  return answer;
};

function solution(numbers, target) {
  let answer = bfs(0, numbers, target);
  return answer;
}
```

## 후기

- bfs로 접근하면 풀 수 있는 문제였습니다.
