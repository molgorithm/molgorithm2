package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class G4_1715카드정렬하기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++)
			pq.add(Integer.parseInt(br.readLine()));
		int answer = 0;
		while (pq.size() != 1) {
			int cnt = pq.poll() + pq.poll();
			pq.add(cnt);
			answer += cnt;
		}
		System.out.println(answer);
	}

}
