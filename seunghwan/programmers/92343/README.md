# 프로그래머스 92343 양과늑대

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/92343)

## 1. 설계 로직

dfs를 이용한 완전탐색

단 각 상황에서 갈 수 있는 모든 길을 다음 장소 후보로 두어야 한다.

 

1. info 글로벌 변수로 옮기기

2. 관계리스트 만들기

3. dfs 실행

4. 현재 노드에 따라 양과 늑대 수 증가

5. 양과 늑대수가 같아지면 종료

6. 전 노드에서 받은 대기큐의 정보 내 큐에 옮기기

7. 현재 노드에서 갈 수 있는 노드 큐에 저장

8. 큐가 비어있으면 종료

9. 현재 큐 size만큼 돌면서 다음 노드로 dfs 실행

10. dfs돌고 나오면 방금 갔던 노드 다시 큐에 넣기



- 시간복잡도

  O(N^2)

## 2. 코드

```java
import java.util.ArrayList;
import java.util.LinkedList;

public class P92343양과늑대 {

	int[] ginfo;
	ArrayList<Integer>[] list;
	int answer = 0;

	public int solution(int[] info, int[][] edges) {
		int n = info.length;
		ginfo = info;
		list = new ArrayList[n];
		for (int i = 0; i < n; i++)
			list[i] = new ArrayList<Integer>();
		for (int i[] : edges)
			list[i[0]].add(i[1]);
		dfs(0, 0, 0, new LinkedList<Integer>());
		return answer;
	}

	public void dfs(int node, int sheep, int wolf, LinkedList<Integer> waitingQue) {
		if (ginfo[node] == 0) {
			sheep++;
		} else {
			wolf++;
			if (sheep <= wolf) {
				answer = Math.max(sheep, answer);
				return;
			}
		}
		LinkedList<Integer> myQue = (LinkedList<Integer>) waitingQue.clone();
		for (int i : list[node])
			myQue.add(i);
		if (myQue.isEmpty()) {
			answer = Math.max(sheep, answer);
			return;
		}
		
		int size = myQue.size();
		while (size-- > 0) {
			int next = myQue.poll();
			dfs(next, sheep, wolf, myQue);
			myQue.add(next);
		}

	}
}
```



## 3. 후기

- 실제 시험에서 풀었던 문제

  풀고보니 저번에 푼 코드랑 거의 비슷하다