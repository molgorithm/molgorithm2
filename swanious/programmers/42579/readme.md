# 프로그래머스 42579 베스트앨범

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/42579)

## 1. 설계 로직

1. object에 모든 정보를 저장
  -  `{total : 0, arr: [[idx1, play1], [idx2, play2], ...] }` 형태로 저장했다
2. 장르, 재생 수, 고유번호 순으로 정렬한다.
  - 재생횟수가 같은 경우에 고유번호가 작은 순서대로 정렬할 필요 x (index 순서대로 push했으므로)

## 2. 코드

```javascript
function solution(genres, plays) {
    var answer = [];
    const obj = {};
    for (let i = 0; i < genres.length; i++) {
        if (!obj[genres[i]]) obj[genres[i]] = {total : 0, arr: [] };
        obj[genres[i]].total += plays[i]
        obj[genres[i]].arr.push([i, plays[i]]);
    }
    const newArr = Object.entries(obj).sort((a, b) => b[1].total - a[1].total); // 총 합계 내림차순
    
    newArr.forEach(([genre, object]) => {
        const arr = object.arr.sort((a, b) => b[1] - a[1]);
        const l = arr.length >= 2 ? 2 : arr.length; // 배열의 크기가 2보다 작으면 배열의 크기만큼
        for (let i = 0; i < l; i++) {
            answer.push(arr[i][0])
        }
    })
    return answer;
}
```

## 3. 후기

- 데이터를 어떻게 저장해야 정렬할 때 편할지 판단하여 문제를 풀어야 한다. 
- 객체를 정렬하기 위해서 `Object.entries()`같은 메서드로 객체를 배열화하여 정렬해야 한다.
