package Gold;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_2624동전바꿔주기 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		int dp [][] = new int[k+1][T+1];
		
		Point coin;
		for(int i = 1 ; i < k+1 ; i++) {
			dp[i-1][0] = 1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			coin = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			for(int j = 1 ; j < T+1 ; j++) 
				for(int m = 0 ; m<=coin.y && m*coin.x<=j;m++)
					dp[i][j] += dp[i-1][j-m*coin.x];
		}
		System.out.println(dp[k][T]);
	}
}
