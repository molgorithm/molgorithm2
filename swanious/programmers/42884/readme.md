# 프로그래머스 42884 단속카메라

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42884)

## 1. 설계 로직
1. 진출 지점을 기준으로 정렬한다. 
2. A의 진출지점과 이외의 진입지점을 비교한다.
3. A의 진출지점보다 진입지점이 더 작은 차량은 단속카메라에 찍혔으므로, 배열에서 제거한다.
4. 위의 2, 3을 반복한다.

- 시간복잡도: O(n^2)

## 2. 코드

```javascript
function solution(routes) {
  routes.sort((a, b) => a[1] - b[1]);
  let answer = 0;
  
  while (routes.length > 0) {
      const exitPosition = routes[0][1];
      
      for (let i = 0; i < routes.length; i++) {
          if (exitPosition >= routes[i][0]) {
              routes.splice(i, 1);
              i--
          }
      }
      answer++
  }
  return answer;
}
```

## 3. 후기
- 차가 나가는 지점을 기점으로 오름차순 정렬하는 것이 핵심인듯