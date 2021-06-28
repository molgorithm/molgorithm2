import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class P67260동굴탐험 {
	

	public boolean solution(int n, int[][] path, int[][] order) {
		boolean answer = true;

		ArrayList<ArrayList<Integer>> matList = new ArrayList<ArrayList<Integer>>();
		int[] orderList = new int[n];
		for (int i = 0; i < n; i++)
			matList.add(new ArrayList<Integer>());
		for (int[] i : path) {
			matList.get(i[0]).add(i[1]);
			matList.get(i[1]).add(i[0]);
		}

		for (int[] i : order)
			orderList[i[1]] = i[0];
		int next[] = new int[n];
		boolean visit[] = new boolean[n];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(0);
		int cnt = 0;
		while (!q.isEmpty()) {
			int now = q.poll();
			if (!visit[orderList[now]] && now != orderList[now]) {
				next[orderList[now]] = now;
				continue;
			}
			visit[now] = true;
			cnt++;
			if (next[now] != 0)
				q.add(next[now]);
			for (int i : matList.get(now)) {
				if(!visit[i])
					q.add(i);
			}
		}
		answer = cnt == n ? true : false;
		return answer;
	}

}

