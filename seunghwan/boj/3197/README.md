# 백준 3197 백조의호수

[문제 링크](https://www.acmicpc.net/problem/3197)

## 1. 설계 로직

1. 각 빙판이 녹는데 필요한 시간들을 탐색 후 배열에 저장

2. 백조 한마리부터 bfs를 돌며 다른 백조를 찾을 때까지 탐색

   2-1. bfs를 돌며 빙판을 지나갈 때는 그 빙판이 녹는 day와 지금껏 지나온 빙판의 day중 더 큰 것 저장. 

   2-2. PriorityQue를 이용해 저장한 day가 가장 작은 것부터 탐색.

3. 다른 백조를 만나면 바로 종료 후 저장 된 day 출력

4. 끝.

- 시간복잡도

  O(N^2)    (N = map의 한 변)

## 2. 코드

```java
package Gold;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1_3197백조의호수 {
	static char[][] map;
	static int[][] dayMap;
	static int R, C;
	static Queue<Point> water = new LinkedList<Point>();
	static Point bird1, bird2;

	static class node implements Comparable<node> {
		int x;
		int y;
		int day;

		public node(int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.day = day;
		}

		@Override
		public int compareTo(node o) {
			return this.day - o.day;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		dayMap = new int[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] != 'X')
					water.add(new Point(i, j));
				if (map[i][j] == 'L')
					if (bird1 == null)
						bird1 = new Point(i, j);
					else
						bird2 = new Point(i, j);
			}
		}
		dayday();
		System.out.println(find());

	}

	static int dr[] = { 1, -1, 0, 0 };
	static int dc[] = { 0, 0, 1, -1 };

	static void dayday() {
		int day = 0;
		while (!water.isEmpty()) {
			int size = water.size();
			day++;
			for (int s = 0; s < size; s++) {
				Point now = water.poll();
				for (int i = 0; i < 4; i++) {
					int row = now.x + dr[i];
					int col = now.y + dc[i];
					if (row < 0 || col < 0 || row >= R || col >= C)
						continue;
					if (map[row][col] != 'X')
						continue;
					dayMap[row][col] = day;
					map[row][col] = '.';
					water.add(new Point(row, col));
				}
			}
		}
	}

	static int find() {
		PriorityQueue<node> pq = new PriorityQueue<node>();
		boolean visit[][] = new boolean[R][C];
		pq.add(new node(bird1.x, bird1.y, 0));
		while (!pq.isEmpty()) {
			node now = pq.poll();
			if(now.x == bird2.x && now.y == bird2.y)
				return now.day;
			for (int i = 0; i < 4; i++) {
				int row = now.x + dr[i];
				int col = now.y + dc[i];
				if (row < 0 || col < 0 || row >= R || col >= C)
					continue;
				if (visit[row][col])
					continue;
				visit[row][col] = true;
				pq.add(new node(row, col, Math.max(now.day, dayMap[row][col])));
			}
		}
		return 0;
	}

}
```



## 3. 후기

- 7개월전에 풀었다가 시간초과나고 안풀었는데

  그 때 어떻게 풀었는지는 기억안나지만 이번엔 한번에 풀어냈다 ㅎㅎ