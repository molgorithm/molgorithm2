package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G5_11000강의실배정 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> start = new PriorityQueue<Integer>();
		PriorityQueue<Integer> end = new PriorityQueue<Integer>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			start.add(Integer.parseInt(st.nextToken()));
			end.add(Integer.parseInt(st.nextToken()));
		}
		int classs = 0;
		int max = 0;
		while (!start.isEmpty()) {
			int s = start.poll();
			while (end.peek() <= s) {
				end.poll();
				classs--;
			}
			max = Math.max(max, ++classs);
		}
		System.out.println(max);
	}
}
