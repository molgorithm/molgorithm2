# 프로그래머스 67257 수식최대화

[문제 링크](https://programmers.co.kr/learn/courses/30/lessons/67259)



## 1. 설계 로직

![img](https://blog.kakaocdn.net/dn/UUgvP/btq8iwDOd3l/3zT2yEFCRErA1S21ATjIEk/img.png)



처음엔 pq를 활용해서 현재까지 값이 가장 싼 도로부터 만들어 가장 먼저 도착지에서 나오는 도로의 값을 결과로 나오게 했는데 몇 개의 테케에서 시간초과가 나왔다.

최초 도착지에 도착한 뒤 그 값보다 비싸지는 모든 분기는 고려가 안 되는 것인데 내 예상보다 시간이 더 걸렸나보다.

 

그래서 풀이방법은

각 자리의 최솟값을 저장해 가며

최솟값이 되게 하는 경로로만 가도록 가지치기 하여

bfs로 모든 경로를 탐색하는 것이다.



- 시간복잡도

  O(n^2) 

## 2. 코드

```java
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P67259경주로건설 {

	class node {
		int x;
		int y;
		int cost;
		int d;

		public node(int x, int y, int cost, int d) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.d = d;
		}
	}

	public int solution(int[][] board) {
		Queue<node> q = new LinkedList<node>();
		int[][] costs = new int[board.length][board.length];
		for (int i = 0; i < costs.length; i++)
			Arrays.fill(costs[i], Integer.MAX_VALUE);

		int[] dr = { 0, 0, 1, -1 };
		int[] dc = { 1, -1, 0, 0 };
		q.add(new node(0, 0, 0, -1));
		int answer = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			node now = q.poll();
			if (now.x == board.length - 1 && now.y == board.length - 1) {
				answer = Math.min(answer, now.cost);
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int x = now.x + dr[i];
				int y = now.y + dc[i];
				if (x < 0 || y < 0 || x >= board.length || y >= board.length)
					continue;
				if (board[x][y] == 1)
					continue;
				int cost = 0;
				if (i == now.d || now.d == -1)
					cost = now.cost + 100;
				else
					cost = now.cost + 600;
				if (costs[x][y] < cost)
					continue;
				costs[x][y] = cost;
				q.add(new node(x, y, cost, i));
			}
		}
		return answer;
	}

}

```



## 3. 후기

- 문제가 경로를 찾아가는 문제이지만

  생각해보면 인접한 위치의 최솟값을 구하면 내 위치의 최솟값을 구할 수 있으니

  단순 dp만으로도 해결이 될 것 같기도 하다.

