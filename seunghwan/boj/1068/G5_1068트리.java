package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G5_1068트리 {

	static ArrayList<Integer>[] nodes;
	static int target;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int root = 0;
		nodes = new ArrayList[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			nodes[i] = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (num == -1) {
				root = i;
				continue;
			}
			nodes[num].add(i);
		}
		target = Integer.parseInt(br.readLine());
		if (target != root)
			dfs(root);
		System.out.println(answer);

	}

	static void dfs(int num) {
		int cnt = 0;
		for (int i = 0; i < nodes[num].size(); i++) {
			if (nodes[num].get(i) == target)
				continue;
			cnt++;
			dfs(nodes[num].get(i));
		}
		if (cnt == 0)
			answer++;

	}

}
