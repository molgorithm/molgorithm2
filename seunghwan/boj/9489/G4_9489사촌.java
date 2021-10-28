package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_9489사촌 {

	static ArrayList<Integer> arrList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			if (n == 0 && k == 0)
				break;
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if (k == arr[0]) {
				sb.append(0).append("\n");
				continue;
			}
			arrList = new ArrayList<Integer>();
			int cnt = 0;
			int pre = -1;
			int targetIdx = 0;
			for (int i : arr) {
				if (pre + 1 != i) {
					arrList.add(cnt);
					cnt = 1;
					pre = i;
				} else {
					pre = i;
					cnt++;
				}
				if (i == k)
					targetIdx = arrList.size();
			}
			arrList.add(cnt);
			int idx = 1;
			Queue<Integer> que = new LinkedList<Integer>();
			que.add(arrList.get(1));
			while (true) {
				int size = que.poll();
				int cousin = 0;
				while (size-- != 0) {
					idx += 1;
					if (idx >= arrList.size())
						break;
					cousin += arrList.get(idx);
					que.add(arrList.get(idx));
				}
				if (idx >= targetIdx) {
					sb.append(cousin - arrList.get(targetIdx)).append("\n");
					break;
				}
			}
		}
		System.out.println(sb);
	}
}

