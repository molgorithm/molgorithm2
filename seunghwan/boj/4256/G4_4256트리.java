package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G4_4256트리 {
	static int[] preOrder;
	static int[] inOrder;
	static int preIdx;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while (T-- != 0) {
			int n = Integer.parseInt(br.readLine());
			sb = new StringBuilder();
			preIdx = 0;
			preOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			inOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			go(0,n-1);
			System.out.println(sb);
		}
	}


	static void go(int s, int e) {
		if(s>e)
			return;
		int num = preOrder[preIdx++];
		int inIdx = -1;
		for (int i = s; i <= e; i++) {
			if (inOrder[i] == num) {
				inIdx = i;
				break;
			}
		}
		go(s, inIdx - 1);
		go(inIdx + 1, e);
		sb.append(num).append(" ");
	}
}