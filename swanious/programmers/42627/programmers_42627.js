function solution(jobs) {
  jobs.sort((a, b) => a[0] - b[0]);
  
  let q = [];
  let total = 0;
  let duration = 0;
  const l = jobs.length;
  
  while (jobs.length || q.length) {
      while (jobs[0] && jobs[0][0] <= total) {
          q.push(jobs.shift())
      }
      
      // 시간이 소요 순서로 오름차순 정렬 ([2, 6] -> [1, 9])
      q.sort((a, b) => a[1] - b[1]);
      
      if (q.length > 0) {
          const temp = q.shift()
          total += temp[1];
          duration += total - temp[0]
      } else total = jobs[0][0] // 겹치치 않으면 total은 다음 작업의 시작시점
      
  }
  return Math.floor(duration / l);
};