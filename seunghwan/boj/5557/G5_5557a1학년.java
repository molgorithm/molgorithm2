package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G5_5557a1학년 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr [] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		long dp[][] = new long[N][21];
		dp[0][arr[0]]++;
		for(int i = 1 ; i < N -1 ; i++) {
			int num = arr[i];
			for(int j = 0 ; j<21 ; j++ ) {
				if(j-num >= 0)
					dp[i][j]+=dp[i-1][j-num];
				if(j+num <21)
					dp[i][j]+=dp[i-1][j+num];
			}
		}
		System.out.println(dp[N-2][arr[N-1]]);
	}
}
