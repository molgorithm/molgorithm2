/**
 * js의 정렬은 문자열일 경우 알파벳 순서이고, 2차원배열에서 첫번째 값이 같으면 자동으로 두번째가 오름차순 정렬된다.
 *    즉, tickets를 그냥 정렬하고 시작하면 문제가 풀린다.
 *
 * dfs의 재귀 종료조건
 *   - 정답 배열의 크기는 tickets의 길이 + 1이다.
 *   - 처음에는 무조건 ICN이 들어가기 때문에 dfs의 파라미터로 ('ICN', depth = 0)를 넘겨주면 된다.
 *   - 즉, depth가 tickets의 길이와 같으면 반환하면 된다.
 *
 * tickets배열의 인덱스로 방문체크
 *   - tickets은 이미 정렬된 상태이기 때문에 dfs내부 반복문에서 알아서 알파벳 순서로 재귀를 돌게 된다.
 *   - 즉, 조건에 맞을 때 해당 인덱스에서 방문체크를 해주면된다.
 *
 */
function solution(tickets) {
  let answer = [];
  const temp = []; // dfs를 돌면서 값을 저장할 배열
  const visit = [];
  const len = tickets.length;

  tickets.sort();

  const dfs = (start, depth) => {
    temp.push(start); // 재귀 시작과 함께 값을 저장

    if (depth === len) {
      // temp를 그냥 반환해도 되지만, boolean타입으로 반환하면 효율적으로 조건에 따른 로직을 구현할 수 있다.
      answer = temp;
      return true;
    }

    for (let i = 0; i < len; i++) {
      if (!visit[i] && tickets[i][0] === start) {
        // 예제1번의 처음이라고 생각하면 ["ICN", "JFK"]에 조건이 부합되어 if문으로 들어옴
        visit[i] = true;

        if (dfs(tickets[i][1], depth + 1)) return true; // depth와 len이 같을 때 여기로 빠져서 바로 답 출력

        visit[i] = false; // 답을 구하지 못했으므로 다시 false로 돌림
      }
    }
    // 여기까지 왔다는 뜻은 반복문을 다 돌아도 답을 구하지 못했다는 의미
    temp.pop();
    return false;
  };

  dfs("ICN", 0);
  return answer;
}
