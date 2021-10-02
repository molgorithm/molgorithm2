# SWEA 1953 탈주범 검거

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq )

## 1. 설계 로직

bfs의 아주 기본같은 문제

 

경과한 시간만큼 갈 수 있는 모든 길의 갯수,

즉 너비로 탐색했을 때 depth가 시간 이하인 길의 갯수를 구하면 된다.

 



![img](https://blog.kakaocdn.net/dn/Vtw1T/btrgdGejKMq/6fu5ElTHyoha7IMor6GmK1/img.png)



다만 특이하게 각 터널의 모양에 따라 이동할 수 있는 길이 다르다는 것과

그 터널에서 이동하려는 곳에도 연결되는 터널이 있어야 이동이 가능하다는 것만 주의 하며 된다.

 

터널의 모양의 경우

먼저 상,하,좌,우 이동을 나타내는 dr[] , dc[] 를 만들고

각 터널의 번호에서 이동가능한 방향을 2차원배열에 미리 저장해주었다.

```java
static int dr[] = new int[] { -1, 1, 0, 0 };
static int dc[] = new int[] { 0, 0, -1, 1 }; // 상하좌우
static int tunnel[][] = new int[][] { {}, { 0, 1, 2, 3 }, { 0, 1 }, { 2, 3 }, { 0, 3 }, { 1, 3 }, { 1, 2 },{ 0, 2 } };
```

가려는 곳이 연결 되었는지는

가는 방향이 상이면 하 좌라면 우로 가는길을 가려는 곳이 가지고 있으면 된다.

상하는 0,1 좌우는 2,3 에 저장해 두었기 때문에

```java
int doYouHave = dir % 2 == 0 ? dir + 1 : dir - 1;
```

짝수면 +1 홀수면 -1로 필요한 방향을 쉽게 구할 수 있다.

 

갈려고 하는 터널이 가진 방향들 중에 필요한 방향이 있는지 체크해주면 된다.



- 시간복잡도

  O(N*M) 완탐

## 2. 코드

```java
package swexpert;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1953탈주범검거 {
	static int dr[] = new int[] { -1, 1, 0, 0 };
	static int dc[] = new int[] { 0, 0, -1, 1 }; // 상하좌우
	static int tunnel[][] = new int[][] { {}, { 0, 1, 2, 3 }, { 0, 1 }, { 2, 3 }, { 0, 3 }, { 1, 3 }, { 1, 2 },
			{ 0, 2 } };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder answer = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int map[][] = new int[N][M];
			boolean visited[][] = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}

			Point p = new Point(R, C);
			visited[R][C] = true;
			Queue<Point> que = new LinkedList<Point>();
			que.add(p);
			int cnt = 1;
			for (int s = 1; s < L; s++) {
				int size = que.size();
				while (size-- != 0) {
					Point now = que.poll();
					for (int dir : tunnel[map[now.x][now.y]]) {
						int row = now.x + dr[dir];
						int col = now.y + dc[dir];
						if (row < 0 || col < 0 || row >= N || col >= M)
							continue;
						if (map[row][col] == 0 || visited[row][col])
							continue;
						int doYouHave = dir % 2 == 0 ? dir + 1 : dir - 1;
						if(!check(doYouHave,map[row][col]))
							continue;
						visited[row][col] = true;
						que.add(new Point(row, col));
						cnt++;
					}
				}
			}
			answer.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(answer);
	}
	
	static boolean check(int doYouHave,int goal) {
		for (int hisDir : tunnel[goal])
			if (hisDir == doYouHave)
				return true;
		return false;
	}

}
```



## 3. 후기

- 예전에 풀었던 문제 다시 풀어본건데

  예전이랑 코드가 거의 비슷하다... 흐긓ㄱ

