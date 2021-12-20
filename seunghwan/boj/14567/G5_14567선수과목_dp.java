package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G5_14567선수과목_dp {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer> parents[] = new ArrayList[N + 1];
		int dp[] = new int [N+1];
		dp[0] = 0;
		for (int i = 1; i <= N; i++) {
			parents[i] = new ArrayList<Integer>();
			parents[i].add(0);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			parents[to].add(from);
		}
		for(int i = 1 ; i <= N ; i++) {
			for(int j : parents[i])
				dp[i] = Math.max(dp[i], dp[j]+1);
			System.out.print(dp[i]+" ");
		}
	}

}
