# 프로그래머스 42627 디스크 컨트롤러

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42627)

## 1. 설계 로직

1. 처음에 **요청 시점** 오름차순 정렬
2. 시간이 겹치는 작업
3. **소요 시간**이 더 적은 작업을 먼저 처리하도록 q를 오름차순 정렬
  - q에 2개 이상의 작업이 추가된다는 것 -> 시간이 겹친다는 의미
  - 이 때, 작업시간이 더 적은 작업을 먼저 처리해야함
4. 겹치지 않으면 총소요시간(total)은 다음 작업의 시작시점으로 초기화

- 시간복잡도: O(n^2 logn)


## 2. 코드

```javascript
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
        } else total = jobs[0][0] // 작업이 겹치치 않으면 total은 다음 작업의 시작시점
        
    }
    return Math.floor(duration / l);
};
```

## 3. 후기
- jobs의 길이가 500까지라서 sort()나 shift() 메서드를 무리없이 사용할 수 있었다.
- 일정시점에 겹친 작업들을 시간소요가 적은 순서로 정렬하는게 키뽀인트인거같다.