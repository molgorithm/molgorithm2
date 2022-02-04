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
