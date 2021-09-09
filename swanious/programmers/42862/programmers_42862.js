/** 시작시 방문체크배열, reserveSet(여벌옷보유자가 도난당한 사람일 때 저장할 Set) 초기화
* 1. lost배열을 순회하면서 visit -> false 및 reserveSet에 추가
* 2. reserve에 있는 사람은 체육수업가능하므로 visit -> true
* 3. reserveSet에 존재하지 않은 사람은 도난당한 사람에게 빌려줄 수 있는 사람이므로 newReserve에 저장
* 4. lost배열을 순회하면서 조건별 visit 처리
* 5. 정답: [전체인원 - visit이 false인 인원]
*/
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