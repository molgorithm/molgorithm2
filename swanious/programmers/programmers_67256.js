/** 전역변수 초기화
 * @returns { Map[y, x], Array[][] }
 */
const initVal = () => {
  let cnt = 1;
  const posMap = new Map();
  const keypad = Array.from(Array(4), () => Array(3));
  for (let y = 0; y < 4; y++) {
    for (let x = 0; x < 3; x++) {
      posMap.set(cnt, [y, x]);
      keypad[y][x] = cnt++;
    }
  }
  return [posMap, keypad];
};

/** 거리체크
 * @returns { number }
 */
function checkDistance(from, to) {
  const [y1, x1] = posMap.get(from);
  const [y2, x2] = posMap.get(to);
  return Math.abs(y1 - y2) + Math.abs(x1 - x2);
}

const [posMap, keypad] = initVal();

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
