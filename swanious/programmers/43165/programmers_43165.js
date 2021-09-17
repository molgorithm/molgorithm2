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
