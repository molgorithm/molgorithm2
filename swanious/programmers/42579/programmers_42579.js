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