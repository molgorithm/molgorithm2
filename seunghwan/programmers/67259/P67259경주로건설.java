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

