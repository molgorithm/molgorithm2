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
    // 미생물 이동 + 벽에 닿을때 미생물 죽이기
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
    // console.log(tempMap);

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
