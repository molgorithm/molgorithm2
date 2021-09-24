# 2382 미생물 격리

> [문제 링크](https://swexpertacademy.com/main/learn/course/lectureProblemViewer.do)

## 고려해야할 사항

1. 미생물이 동일한 위치로 움직이는 경우 처리
2. 벽에 닿는 경우(벽에 닿는 경우는 겹치지 않으므로 걍 Math.floor(미생물개수 / 2)해준다.)

시간복잡도: O(N\*M)

## 나의 코드

```javascript
const fs = require("fs");
const stdin = fs.readFileSync("test.txt").toString().split("\n");
const input = (() => {
  let line = 0;
  return () => stdin[line++];
})();

const DIR = { 1: [-1, 0], 2: [1, 0], 3: [0, -1], 4: [0, 1] };
const REVERSE = { 1: 2, 2: 1, 3: 4, 4: 3 };

const solution = (N, time, mapp) => {
  // 시간이 0보다 클때 반복
  while (time > 0) {
    // 미생물 이동
    const tempMap = new Map();
    mapp.forEach((v) => {
      let [y, x, vol, dir] = v;
      const ny = y + DIR[dir][0];
      const nx = x + DIR[dir][1];

      if (ny === 0 || nx === 0 || ny === N - 1 || nx === N - 1) {
        vol = Math.floor(vol / 2);
        dir = REVERSE[dir];
      }
      tempMap.set([ny, nx], [[vol, dir]]);
    });

    // 같은 위치인 미생물 구하기
    let tempObj = {};
    for (let [k, v] of tempMap[Symbol.iterator]()) {
      tempObj[k] === undefined ? (tempObj[k] = v) : tempObj[k].push(...v);
    }

    // 같은 위치 미생물 통합
    let tempArr = [];
    for (let [k, values] of Object.entries(tempObj)) {
      // 같은 위치인 미생물이 있으면 미생물의 수가 가장 많은 미생물로 통합
      if (values.length > 1) {
        let newVol = 0,
          newDir = 0,
          maxVol = 0;

        values.forEach((v) => {
          const [vol, dir] = v;
          if (vol > maxVol) {
            maxVol = vol;
            newDir = dir;
          }
          newVol += vol;
        });
        tempArr.push([...k.split(",").map(Number), newVol, newDir]);
      } else {
        // 위치가 다르면 그냥 기존의 값을 저장
        tempArr.push([...k.split(",").map(Number), ...values.flat()]);
      }
    }
    mapp = tempArr;
    time--;
  }
  return mapp;
};

T = Number(input());
for (let tc = 1; tc < T + 1; tc++) {
  const [N, M, K] = input().split(" ").map(Number); // 배열크기, 격리시간, 미생물개수
  const mapp = Array.from(Array(N), () => Array(N).fill(-1));

  const arr = [];

  for (let i = 0; i < K; i++) {
    arr.push(input().split(" ").map(Number));
  }

  const newMapp = solution(N, M, arr);
  console.log(
    `#${tc} ${newMapp.reduce((acc, cur) => {
      acc += cur[2];
      return acc;
    }, 0)}`
  );
}
```

## 후기

- 처음에는 2차원 배열에 직접 미생물을 찍으면서 진행하려고 입출력까지 받았으나 미생물 정보만 가지고 구하는게 효율적이다 싶어서 변경
- 삼성도 쟈스는 언어로 안보나보다 .......
