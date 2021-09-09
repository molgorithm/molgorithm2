# 프로그래머스 42628 이중운선순위큐

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42628)

## 1. 설계 로직
- I일때 -> 배열에 삽입 후 오름차순 정렬
- D일때 -> 최소값일때 왼쪽에서 뽑고, 최대값일때 오른쪽에서 뽑아준다.
- 모든 작업이 끝나고 다시 queue를 오름차순 해준다.
- 큐에 값이 있다 ? [최대값, 최소값] : [0, 0]

## 2. 코드

```javascript
function solution(operations) {
    var queue = [];
    
    operations.forEach((op) => {
        const [command, val] = op.split(' ')
        if (command === "I") {
            // Number로 변환
            queue.push(val * 1)
        }
        else if (command === 'D' && queue.length) {
          queue.sort((a, b) => a - b)
          val === '1' ? queue.pop() : queue.shift()
        }
    })
        
    queue.sort((a, b) => a - b)
    return queue.length ? [queue[queue.length-1], queue[0]] : [0, 0];
}
```

## 3. 후기
- js에는 파이똔의 deque같은 핵꿀 라이브러리는 없지만, 정렬과 shift()만 있다면 충분히 이중우선순위큐를 만들 수 있다.