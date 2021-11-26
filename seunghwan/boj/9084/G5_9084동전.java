package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G5_9084동전 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			br.readLine();
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int money = Integer.parseInt(br.readLine());
			int dp[] = new int[money + 1];
			dp[0] = 1;
			for (int cost : arr)
				for (int i = cost; i < money + 1; i++)
					dp[i] += dp[i - cost];
			System.out.println(dp[money]);
		}
	}
}
