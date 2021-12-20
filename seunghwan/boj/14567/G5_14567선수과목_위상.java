package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_14567선수과목_위상 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer> arr[] = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			arr[i] = new ArrayList<Integer>();
		int parentCnt[] = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(to);
			parentCnt[to]++;
		}
		Queue<Integer> que = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++)
			if (parentCnt[i] == 0)
				que.add(i);
		int answer[] = new int[N + 1];
		int hak = 1;
		while (!que.isEmpty()) {
			int size = que.size();
			while (size-- != 0) {
				int now = que.poll();
				answer[now] = hak;
				for (int child : arr[now])
					if (--parentCnt[child] == 0)
						que.add(child);
			}
			hak++;
		}
		for(int i = 1 ; i <= N ; i++) 
			System.out.print(answer[i]+" ");
		
	}

}
