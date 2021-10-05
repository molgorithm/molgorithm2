package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G5_7662이중우선순위큐_deque {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			int k = Integer.parseInt(br.readLine());
			LinkedList<Integer> deque = new LinkedList<Integer>();
			while (k-- != 0) {
				st = new StringTokenizer(br.readLine());
				String oper = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				if (oper.charAt(0) == 'I') {
					deque.add(num);
					Collections.sort(deque);
				} else {
					if (deque.isEmpty())
						continue;
					if (num == 1)
						deque.pollLast();
					else
						deque.pollFirst();
				}
			}
			if (deque.isEmpty())
				System.out.println("EMPTY");
			else {
				System.out.println(deque.peekLast()+" "+deque.peekFirst());
			}
		}
	}
}