# 백준 19236 청소년 상어

[문제 링크](https://www.acmicpc.net/problem/19236)

## 1. 설계 로직

상어 시리즈 중 난이도가 조금 있는 문제



꽤나 복잡한 시뮬레이션에 백트레킹이 들어가 있다.



중요 개념들로



저장 해야하는 상태가 물고기의 번호와 방향 2가지이므로

2개의 2중 배열에 저장을 시켰다.



물고기가 이동할 때 번호 순으로 이동하므로

이 번호를 매번 찾지 않기 위해 물고기의 위치를 fishPoint라는 배열에 매번 저장을 해두었다.



상어가 이동 할 때

상어가 물고기를 먹는 경우가 여러 분기로 나뉠 수 있으므로

백트레킹을 활용하였고 각 분기마다 그 때의 상태를 temp에 모두 저장해 두었다. ( 깊은 복사 )



분기의 끝마다 상어가 먹은 번호의 max값을 업데이트 시키고

모든 분기가 끝난후 가장 큰 값이었던 max를 출력하면 된다.



- 시간복잡도

  O(N^2)    (N = map의 한 변)

## 2. 코드

```java
package Gold;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2_19236청소년상어_2 {
	static int map[][] = new int[4][4];
	static int dir[][] = new int[4][4];
	static Point fishPoint[] = new Point[17];
	static int dr[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dc[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int max = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				fishPoint[map[i][j]] = new Point(i, j);
				dir[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int Sum = map[0][0];
		fishPoint[Sum] = new Point(-1, -1);
		map[0][0] = -1;
		go(0, 0, Sum);
		System.out.println(max);
	}

	static void go(int x, int y, int Sum) { // x,y 상어 위치
		// 물고기이동 상어이동
		move(); // 물고기 이동
		int[][] tempMap = copyMap(map);
		int[][] tempDir = copyMap(dir);
		Point[] tempFish = copyFish(fishPoint);
		int row = x;
		int col = y;
		while (true) {// 먹으러 가자
			row = row + dr[dir[x][y]];
			col = col + dc[dir[x][y]];
			if (row < 0 || col < 0 || row >= 4 || col >= 4)
				break;
			if (map[row][col] == 0)
				continue;
			Sum += map[row][col];
			fishPoint[map[row][col]] = new Point(-1,-1);
			map[row][col] = -1;
			map[x][y] = 0;
			go(row, col, Sum);
			map = copyMap(tempMap);
			dir = copyMap(tempDir);
			fishPoint = copyFish(tempFish);
			Sum -= map[row][col];
		}
		max = Math.max(max, Sum);
	}

	static Point[] copyFish(Point[] fishPoint) {
		Point[] copyPoint = new Point[17];
		for (int i = 1; i < 17; i++)
			copyPoint[i] = (Point) fishPoint[i].clone();
		return copyPoint;
	}

	static int[][] copyMap(int[][] map) {
		int[][] copyMap = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		return copyMap;
	}

	static void move() {
		for (int i = 1; i <= 16; i++) {
			Point now = fishPoint[i];
			if (now.x == -1)
				continue;
			int d = dir[now.x][now.y];
			do {
				int row = now.x + dr[d];
				int col = now.y + dc[d];
				if (row < 0 || col < 0 || row >= 4 || col >= 4 || map[row][col] == -1) {
					d = d == 8 ? 1 : d + 1;
					continue;
				}
				if (map[now.x][now.y] != -1)
					fishPoint[map[now.x][now.y]] = new Point(row,col);
				fishPoint[map[row][col]] = new Point(now.x,now.y);
				map[now.x][now.y] = map[row][col] ^ map[now.x][now.y] ^ (map[row][col] = map[now.x][now.y]);
				dir[now.x][now.y] = dir[row][col];
				dir[row][col] = d;
				break;
			} while (d != dir[now.x][now.y]);
		}
	}
}

```



## 3. 후기

- 개념들이 그렇게 어렵지는 않지만 구현이 조금 복잡했던 문제였다.