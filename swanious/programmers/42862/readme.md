# 프로그래머스 42862 체육복

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42862)

## 1. 설계 로직
1. lost배열을 순회하면서 visit -> false 및 reserveSet에 추가
2. reserve에 있는 사람은 체육수업가능하므로 visit -> true
3. reserveSet에 존재하지 않은 사람은 도난당한 사람에게 빌려줄 수 있는 사람이므로 newReserve에 저장
4. lost배열을 순회하면서 조건별 visit 처리
5. 정답: [전체인원 - visit이 false인 인원]

- 시간복잡도: O(n^2)

## 2. 코드

```javascript
function solution(n, lost, reserve) {
  lost.sort((a,b) => a - b);
  
  const reserveSet = new Set();
  const visit = new Array(n + 1).fill(true);
  
  lost.forEach(l => {
      visit[l] = false;
      if (reserve.includes(l)) {
          reserveSet.add(l);
      }
  })
  
  reserve.forEach(r => visit[r] = true);
  
  let newReserve = reserve.filter(v => ![...reserveSet].includes(v))
  lost.forEach(l => {
      const minusIdx = newReserve.indexOf(l - 1);
      const plusIdx = newReserve.indexOf(l + 1);
      if (minusIdx !== -1) {
          visit[l] = true
          newReserve.splice(minusIdx, 1)
      }
      else if (plusIdx !== -1) {
          newReserve.splice(plusIdx, 1)
          visit[l] = true;
      }
  })
  
  return n - visit.filter(v => !v).length;

}
```

## 3. 후기
- 처음 lost배열을 오름차순으로 정렬한 후 왼쪽 -> 오른쪽 순서대로 체육복을 빌려주면 풀림!
- 도난당한사람 중 여분의 체육복이 있는 사람은 먼저 빼주기!