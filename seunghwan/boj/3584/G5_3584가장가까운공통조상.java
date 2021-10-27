package Gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_3584가장가까운공통조상 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		 
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0 ; tc <T ; tc++) {
			int N = Integer.parseInt(br.readLine());
			int par [] = new int [10001];
			boolean visit[] = new boolean[10001];
			for(int i = 0 ; i < N-1 ; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				par[b] = a;
			}
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			while(true) {
				visit[a] = true;
				if(par[a] == 0)
					break;
				a = par[a];
			}
			while(true) {
				if(visit[b]) {
					sb.append(b).append("\n");
					break;
				}
				b = par[b];
			}
		}
		System.out.println(sb);
	}
}
