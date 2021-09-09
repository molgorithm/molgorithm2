function solution(routes) {
  routes.sort((a, b) => a[1] - b[1]);
  let answer = 0;
  
  while (routes.length > 0) {
      const exitPosition = routes[0][1];
      
      for (let i = 0; i < routes.length; i++) {
          if (exitPosition >= routes[i][0]) {
              routes.splice(i, 1);
              i--
          }
      }
      answer++
  }
  return answer;
}