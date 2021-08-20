# 프로그래머스 67256 키패드 누르기

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67256)

## 1. 설계 로직

1. 거리를 체크하기 위해 `keynumber -> [y, x]` 형식의 Map에 저장

```javascript
/** 전역변수 초기화
 * @returns { Map[y, x] }
 */
const posMap = (() => {
  let cnt = 1;
  const posMap = new Map();
  for (let y = 0; y < 4; y++) {
    for (let x = 0; x < 3; x++) {
      posMap.set(cnt++, [y, x]);
    }
  }
  return posMap;
})();
```

2. 전역변수 `posMap`을 통해 거리 체크

```javascript
/** 거리체크
 * @returns { number }
 */
function checkDistance(from, to) {
  const [y1, x1] = posMap.get(from);
  const [y2, x2] = posMap.get(to);
  return Math.abs(y1 - y2) + Math.abs(x1 - x2);
}
```

3. 왼쪽 손과 오른쪽 손의 거리를 비교하여 가까운 거리의 손을 답에 더해줌

- 시간복잡도 O(1)

## 2. 코드

```javascript
/** 전역변수 초기화
 * @returns { Map[y, x], Array[][] }
 */
const posMap = (() => {
  let cnt = 1;
  const posMap = new Map();
  for (let y = 0; y < 4; y++) {
    for (let x = 0; x < 3; x++) {
      posMap.set(cnt++, [y, x]);
    }
  }
  return posMap;
})();

/** 거리체크
 * @returns { number }
 */
function checkDistance(from, to) {
  const [y1, x1] = posMap.get(from);
  const [y2, x2] = posMap.get(to);
  return Math.abs(y1 - y2) + Math.abs(x1 - x2);
}

function solution(numbers, hand) {
  const leftSide = [1, 4, 7];
  const rightSide = [3, 6, 9];

  // 처음위치 (* -> 10, # -> 12)
  let posL = 10;
  let posR = 12;

  let answer = "";

  numbers.forEach((num) => {
    if (leftSide.indexOf(num) !== -1) {
      answer += "L";
      posL = num;
    } else if (rightSide.indexOf(num) !== -1) {
      answer += "R";
      posR = num;
    } else {
      // 0 -> 11
      if (num === 0) num = 11;
      var disL, disR;

      // 거리 구하기
      disL = checkDistance(posL, num);
      disR = checkDistance(posR, num);

      // 가까운 순서에 따라 위치 구하기 (오?, 왼?, 동일?)
      if (disL > disR) {
        answer += "R";
        posR = num;
      } else if (disL < disR) {
        answer += "L";
        posL = num;
      } else {
        if (hand == "right") {
          answer += "R";
          posR = num;
        } else {
          answer += "L";
          posL = num;
        }
      }
    }
  });
  return answer;
}
```

## 3. 후기

- Map 너무 좋아요.
