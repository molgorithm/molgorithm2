package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G5_2075N번째큰수 {

	static class node implements Comparable<node> {
		int num;
		int idx;

		public node(int num, int idx) {
			super();
			this.num = num;
			this.idx = idx;
		}

		@Override
		public int compareTo(node o) {
			return Integer.compare(o.num, this.num);
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<node> pq = new PriorityQueue<node>();
		int[] rowIdx = new int[N];
		Arrays.fill(rowIdx, N - 1);
		int map[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			pq.add(new node(map[N-1][i], i));
		}
		int num = 0;
		while (N-- != 0) {
			node now = pq.poll();
			num = now.num;
			rowIdx[now.idx]--;
			if (rowIdx[now.idx] > -1)
				pq.add(new node(map[rowIdx[now.idx]][now.idx], now.idx));
		}
		System.out.println(num);
	}

}
