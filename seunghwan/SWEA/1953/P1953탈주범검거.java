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
