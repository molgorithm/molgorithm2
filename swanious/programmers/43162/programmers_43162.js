function solution(n, computers) {
  var answer = 0;
  var visit = new Set();

  for (let start = 0; start < n; start++) {
    const count = dfs(start, computers, visit);
    answer += count;
  }
  return answer;
}

function dfs(start, computers, visit) {
  if (visit.has(start)) return 0;
  visit.add(start);

  for (let i = 0; i < computers[start].length; i++) {
    if (i == start || !computers[start][i]) continue;
    if (!visit.has(i)) {
      dfs(i, computers, visit);
    }
  }
  return 1;
}
